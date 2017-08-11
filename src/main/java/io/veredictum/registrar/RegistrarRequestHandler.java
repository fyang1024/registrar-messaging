/*

This software is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
See MIT Licence for further details.
<https://opensource.org/licenses/MIT>.

*/

package io.veredictum.registrar;

import io.veredictum.messaging.RegistrarReceiptSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Bytes8;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.Response;
import org.web3j.protocol.core.methods.request.RawTransaction;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * It converts the {@link ContentRegistrarRequest} to a Smart Contract function call
 * to register content ownership on the Ethereum blockchain,
 * and returns a {@link Future} of {@link TransactionReceipt}
 *
 * @author Fei Yang <fei.yang@veredictum.io>
 */
@Component
public class RegistrarRequestHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private ExecutorService executorService = Executors.newCachedThreadPool();

    @Value("${ethereum.account.password}")
    private String ethereumAccountPassword;

    @Value("${ethereum.account.keyStoreFile}")
    private String ethereumKeyStoreFile;

    @Value("${gas.limit}")
    private BigInteger gasLimit;

    @Value("${gas.price}")
    private BigInteger gasPrice;

    @Value("${contract.address}")
    private String contractAddress;

    @Value("${etherscan.site}")
    private String etherScanSite;

    @Value("${transaction.hash.kafka.topic}")
    private String transactionHashTopic;

    @Value("${block.number.kafka.topic}")
    private String blockNumberTopic;

    @Value("${registrar.error.kafka.topic}")
    private String registrarErrorTopic;

    @Value("${registrar.function.name}")
    private String registrarFunctionName;

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public RegistrarRequestHandler(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void handle(ContentRegistrarRequest request) throws Exception {
        Web3j web3j = Web3j.build(new HttpService());
        Credentials credentials = WalletUtils.loadCredentials(ethereumAccountPassword, ethereumKeyStoreFile);
        List<Type> inputParameters = new ArrayList<>();
        inputParameters.add(convert(request.getAddresses()));
        inputParameters.add(convert(request.getShares()));
        inputParameters.add(new Bytes8(ByteBuffer.allocate(8).putLong(request.getContentId()).array()));
        inputParameters.add(new Bytes32(request.getOriginalFileHash()));
        inputParameters.add(new Bytes32(request.getTranscodedFileHash()));
        Function function = new Function(registrarFunctionName, inputParameters, Collections.emptyList());
        String encodedFunction = FunctionEncoder.encode(function);
        EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(
                credentials.getAddress(), DefaultBlockParameterName.LATEST).sendAsync().get();
        BigInteger nonce = ethGetTransactionCount.getTransactionCount();
        RawTransaction rawTransaction  = RawTransaction.createTransaction(
                nonce,
                gasPrice,
                gasLimit,
                contractAddress,
                encodedFunction
        );
        RawTransactionManager transactionManager = new RawTransactionManager(web3j, credentials);
        EthSendTransaction ethSendTransaction = transactionManager.signAndSend(rawTransaction);
        if(ethSendTransaction.hasError()) {
            Response.Error error = ethSendTransaction.getError();
            logger.error("error code: " + error.getCode());
            logger.error("error message: " + error.getMessage());
            logger.error("error data: " + error.getData());
        } else {
            String transactionHash = ethSendTransaction.getTransactionHash();
            logger.info("Transaction Hash: " + transactionHash);
            CompletableFuture<EthGetTransactionReceipt> futureReceipt = web3j.ethGetTransactionReceipt(transactionHash).sendAsync();
            executorService.submit(
                    new RegistrarReceiptSender(
                            request.getContentId(),
                            kafkaTemplate,
                            etherScanSite,
                            transactionHashTopic,
                            blockNumberTopic,
                            registrarErrorTopic,
                            transactionHash,
                            futureReceipt
                    )
            );
        }

    }

    private DynamicArray<Address> convert(String[] sa) {
        Address[] addresses = new Address[sa.length];
        for (int i = 0; i < addresses.length; i++) {
            addresses[i] = new Address(sa[i]);
        }
        return new DynamicArray<>(addresses);
    }

    private DynamicArray<Uint8> convert(int[] a) {
        Uint8[] shares = new Uint8[a.length];
        for (int i = 0; i < a.length; i++) {
            shares[i] = new Uint8(a[i]);
        }
        return new DynamicArray<>(shares);
    }
}
