/*

This software is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
See MIT Licence for further details.
<https://opensource.org/licenses/MIT>.

*/

package io.veredictum.messaging;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.veredictum.registrar.model.ContentRegistrarRequest;
import io.veredictum.registrar.RegistrarRequestHandler;
import io.veredictum.registrar.util.Hasher;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * It listens for {@link ContentRegistrarRequest} sent from client
 *
 * @author Fei Yang <fei.yang@veredictum.io>
 */
@Component
public class RegistrarRequestListener {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final RegistrarRequestHandler requestHandler;

    @Autowired
    public RegistrarRequestListener(RegistrarRequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }

    @KafkaListener(topics = "registrarTopic")
    public void listen(ConsumerRecord<String, String> cr) throws Exception {
        logger.info("Received message: " + cr.value());
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        ContentRegistrarRequest request = mapper.readValue(cr.value(), ContentRegistrarRequest.class);
        // TODO The following dummy hashes will be replaced
        request.setOriginalFileHash(Hasher.hashString("" + request.getContentId()));
        request.setTranscodedFileHash(Hasher.hashBytes(request.getOriginalFileHash()));
        requestHandler.handle(request);
    }


}
