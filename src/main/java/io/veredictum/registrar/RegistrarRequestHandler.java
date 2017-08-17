/*

This software is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
See MIT Licence for further details.
<https://opensource.org/licenses/MIT>.

*/

package io.veredictum.registrar;

import io.veredictum.messaging.RegistrarReceiptSender;
import io.veredictum.registrar.model.ContentRegistrarRequest;
import io.veredictum.registrar.service.RegistrarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.web3j.protocol.core.Response;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

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

    private RegistrarService registrarService;
    private RegistrarReceiptSender registrarReceiptSender;

    @Autowired
    public RegistrarRequestHandler(RegistrarService registrarService, RegistrarReceiptSender registrarReceiptSender) {
        this.registrarService = registrarService;
        this.registrarReceiptSender = registrarReceiptSender;
    }

    public void handle(ContentRegistrarRequest request) throws Exception {
        EthSendTransaction ethSendTransaction = registrarService.sendRequest(request);
        if (ethSendTransaction.hasError()) {
            Response.Error error = ethSendTransaction.getError();
            logger.error("error code: " + error.getCode());
            logger.error("error message: " + error.getMessage());
            logger.error("error data: " + error.getData());
            registrarReceiptSender.sendError(request.getContentId(), new TransactionException(error));
        } else {
            String transactionHash = ethSendTransaction.getTransactionHash();
            registrarReceiptSender.sendTransactionHash(request.getContentId(), transactionHash);
            executorService.submit(() -> {
                TransactionReceipt receipt = registrarService.getTransactionReceipt(transactionHash);
                registrarReceiptSender.sendBlockNumber(request.getContentId(), receipt);
                return receipt;
            });
        }

    }
}
