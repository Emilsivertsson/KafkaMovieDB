package org.CodeForPizza.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * This class implements a kafka producer that sends a message to the topic "movie".
 * It uses a KafkaTemplate to send the message. The template is configured in the application.properties file.
 */
@Slf4j
@Service
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String movieInfo) throws Exception {
        try{
        log.info("Producing message: " + movieInfo);
        kafkaTemplate.send("movie", movieInfo);
        } catch (Exception e) {
            log.error("Error producing message: " + movieInfo);
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
}
