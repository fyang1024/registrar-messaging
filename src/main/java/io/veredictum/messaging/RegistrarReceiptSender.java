/*

This software is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
See MIT Licence for further details.
<https://opensource.org/licenses/MIT>.

*/

package io.veredictum.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.veredictum.registrar.RegistrarError;
import io.veredictum.registrar.SimpleReceipt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * It sends either a success receipt or an error to client
 *
 * @author Fei Yang <fei.yang@veredictum.io>
 */
public class RegistrarReceiptSender implements Runnable {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private KafkaTemplate<String, String> kafkaTemplate;
    private String kafkaTopic;
    private Future<TransactionReceipt> receiptFuture;

    RegistrarReceiptSender(KafkaTemplate<String, String> kafkaTemplate, String kafkaTopic, Future<TransactionReceipt> receiptFuture) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaTopic = kafkaTopic;
        this.receiptFuture = receiptFuture;
    }

    @Override
    public void run() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            TransactionReceipt transactionReceipt = receiptFuture.get();
            logger.info("Transaction Receipt: " + objectMapper.writeValueAsString(transactionReceipt));
            SimpleReceipt simpleReceipt = new SimpleReceipt(transactionReceipt);
            kafkaTemplate.send(kafkaTopic, objectMapper.writeValueAsString(simpleReceipt));
        } catch (InterruptedException | ExecutionException | JsonProcessingException e) {
            logger.error(e.getMessage(), e);
            try {
                kafkaTemplate.send(kafkaTopic, objectMapper.writeValueAsString(new RegistrarError(e)));
            } catch (JsonProcessingException e2) {
                logger.error(e.getMessage(), e2);
            }
        }

    }
}
