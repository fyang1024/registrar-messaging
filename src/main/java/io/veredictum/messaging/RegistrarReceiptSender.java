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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.math.BigInteger;

/**
 * It sends either a success receipt or an error to client
 *
 * @author Fei Yang <fei.yang@veredictum.io>
 */

@Component
public class RegistrarReceiptSender {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${etherscan.site}")
    private String etherScanSite;

    @Value("${transaction.hash.kafka.topic}")
    private String transactionHashTopic;

    @Value("${block.number.kafka.topic}")
    private String blockNumberTopic;

    @Value("${registrar.error.kafka.topic}")
    private String registrarErrorTopic;

    public void sendTransactionHash(long contentId, String transactionHash) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode transactionHashNode = objectMapper.createObjectNode();
        transactionHashNode.put("contentId", contentId);
        transactionHashNode.put("transactionHash", transactionHash);
        transactionHashNode.put("url", getTransactionHashUrl(transactionHash));
        kafkaTemplate.send(transactionHashTopic, transactionHashNode.toString());
        logger.info("Sent: " + transactionHashNode + " to: " + transactionHashTopic);
    }

    public void sendBlockNumber(long contentId, TransactionReceipt receipt) {
        ObjectNode blockNumberNode = new ObjectMapper().createObjectNode();
        String transactionHash = receipt.getTransactionHash();
        BigInteger blockNumber = receipt.getBlockNumber();
        blockNumberNode.put("contentId", contentId);
        blockNumberNode.put("transactionHash", transactionHash);
        blockNumberNode.put("blockNumber", blockNumber.toString());
        blockNumberNode.put("url", getBlockNumberUrl(blockNumber));
        kafkaTemplate.send(blockNumberTopic, blockNumberNode.toString());
        logger.info("Sent: " + blockNumberNode + " to: " + blockNumberTopic);
    }

    public void sendError(long contentId, Exception e) {
        ObjectNode registrarErrorNode = new ObjectMapper().createObjectNode();
        registrarErrorNode.put("contentId", contentId);
        registrarErrorNode.put("type", e.getClass().getSimpleName());
        registrarErrorNode.put("errorMessage", e.getMessage());
        kafkaTemplate.send(registrarErrorTopic, registrarErrorNode.toString());
        logger.info("Sent: " + registrarErrorNode + " to: " + registrarErrorTopic);
    }

    private String getTransactionHashUrl(String transactionHash) {
        return etherScanSite + "/tx/" + transactionHash;
    }

    private String getBlockNumberUrl(BigInteger blockNumber) {
        return etherScanSite + "/block/" + blockNumber;
    }
}
