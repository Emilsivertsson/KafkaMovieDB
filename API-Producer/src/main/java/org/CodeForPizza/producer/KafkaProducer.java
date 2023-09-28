package org.CodeForPizza.producer;

import lombok.extern.slf4j.Slf4j;
import org.CodeForPizza.dto.MovieDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * This class implements a kafka producer that sends a message to the topic "movie".
 * it uses a KafkaTemplate to send the message to the topic.
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
