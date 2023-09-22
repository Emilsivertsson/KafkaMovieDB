package org.CodeForPizza.producer;

import lombok.extern.slf4j.Slf4j;
import org.CodeForPizza.dto.MovieDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * This class implements a kafka producer that sends a message to the topic "movie".
 * It uses a KafkaTemplate to send the message. The template is configured in the application.properties file.
 */
@Slf4j
@Service
public class KafkaProducer {

    private final KafkaTemplate<String, MovieDTO> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, MovieDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(MovieDTO movieInfo) {
        try{
        log.info("Producing message: " + movieInfo.toString());
        kafkaTemplate.send("movie", movieInfo);
        } catch (Exception e) {
            log.error("Error producing message: " + movieInfo.toString());
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
}
