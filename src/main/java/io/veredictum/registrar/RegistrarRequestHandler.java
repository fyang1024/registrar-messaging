/*
TODO add license
 */
package io.veredictum.registrar;

import io.veredictum.generated.ContentAssetRegistrar;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Bytes6;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;

import java.math.BigInteger;
import java.util.concurrent.Future;

/**
 * It converts the {@link RegistrarRequest} to a Smart Contract function call
 * to register content ownership on the Ethereum blockchain,
 * and returns a {@link Future} of {@link TransactionReceipt}
 *
 * @author Fei Yang <fei.yang@veredictum.io>
 */
@Component
public class RegistrarRequestHandler {

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

    public Future<TransactionReceipt> handle(RegistrarRequest request) throws Exception {
        Web3j web3j = Web3j.build(new HttpService());
        Credentials credentials = WalletUtils.loadCredentials(ethereumAccountPassword, ethereumKeyStoreFile);
        ContentAssetRegistrar contentAssetRegistrar = ContentAssetRegistrar.load(contractAddress, web3j, credentials, gasPrice, gasLimit);
        DynamicArray<Address> addressDynamicArray = convert(request.getAddresses());
        DynamicArray<Uint8> shareDynamicArray = convert(request.getShares());
        return contentAssetRegistrar.registerContent(addressDynamicArray, shareDynamicArray,
                new Bytes6(request.getContentId().getBytes()),
                new Bytes32(request.getOriginalFileHash()),
                new Bytes32(request.getTranscodedFileHash()));
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
