/*

This software is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
See MIT Licence for further details.
<https://opensource.org/licenses/MIT>.

*/

package io.veredictum.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.veredictum.registrar.NoReceiptException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * It sends either a success receipt or an error to client
 *
 * @author Fei Yang <fei.yang@veredictum.io>
 */
public class RegistrarReceiptSender implements Runnable {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private KafkaTemplate<String, String> kafkaTemplate;
    private long contentId;
    private String etherScanSite;
    private String transactionHashTopic;
    private String blockNumberTopic;
    private String registrarErrorTopic;
    private String transactionHash;
    private CompletableFuture<EthGetTransactionReceipt> receipt;

    public RegistrarReceiptSender(
            long contentId,
            KafkaTemplate<String, String> kafkaTemplate,
            String etherScanSite,
            String transactionHashTopic,
            String blockNumberTopic,
            String registrarErrorTopic,
            String transactionHash,
            CompletableFuture<EthGetTransactionReceipt> receipt
    ) {
        this.contentId = contentId;
        this.kafkaTemplate = kafkaTemplate;
        this.etherScanSite = etherScanSite;
        this.transactionHashTopic = transactionHashTopic;
        this.blockNumberTopic = blockNumberTopic;
        this.registrarErrorTopic = registrarErrorTopic;
        this.transactionHash = transactionHash;
        this.receipt = receipt;
    }

    @Override
    public void run() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode transactionHashNode = objectMapper.createObjectNode();
        transactionHashNode.put("contentId", contentId);
        transactionHashNode.put("transactionHash", transactionHash);
        transactionHashNode.put("url", getTransactionHashUrl());
        kafkaTemplate.send(transactionHashTopic, transactionHashNode.toString());
        try {
            EthGetTransactionReceipt ethGetTransactionReceipt = receipt.get();
            Optional<TransactionReceipt> transactionReceipt = ethGetTransactionReceipt.getTransactionReceipt();
            if (transactionReceipt.isPresent()) {
                ObjectNode blockNumberNode = objectMapper.createObjectNode();
                TransactionReceipt receipt = transactionReceipt.get();
                String transactionHash = receipt.getTransactionHash();
                String blockNumberRaw = receipt.getBlockNumberRaw();
                blockNumberNode.put("contentId", contentId);
                blockNumberNode.put("transactionHash", transactionHash);
                blockNumberNode.put("blockNumber", blockNumberRaw);
                blockNumberNode.put("url", getBlockNumberUrl(blockNumberRaw));
                kafkaTemplate.send(blockNumberTopic, blockNumberNode.toString());
            } else {
                throw new NoReceiptException("No transaction receipt present");
            }
        } catch (InterruptedException | ExecutionException | NoReceiptException e) {
            logger.error(e.getMessage(), e);
            ObjectNode registrarErrorNode = objectMapper.createObjectNode();
            registrarErrorNode.put("contentId", contentId);
            registrarErrorNode.put("type", e.getClass().getSimpleName());
            registrarErrorNode.put("errorMessage", e.getMessage());
            kafkaTemplate.send(registrarErrorTopic, registrarErrorNode.toString());
        }

    }

    private String getTransactionHashUrl() {
        return etherScanSite + "/tx/" + transactionHash;
    }

    private String getBlockNumberUrl(String blockNumber) {
        return etherScanSite + "/block/" + blockNumber;
    }
}
