package org.CodeForPizza.producer;


import lombok.extern.slf4j.Slf4j;
import org.CodeForPizza.dto.MovieDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * This class implements a kafka producer that sends a message to the topic "returningData".
 * It uses a KafkaTemplate to send the message. The template is configured in the application.properties file.
 */
@Slf4j
@Service
public class DatabaseProducer {

    private final KafkaTemplate<String, MovieDTO> kafkaTemplate;

    @Autowired
    public DatabaseProducer(KafkaTemplate<String, MovieDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(MovieDTO movieInfo) {
        try{
            log.info("Producing message: " + movieInfo);
            kafkaTemplate.send("returningData", movieInfo);
        } catch (Exception e) {
            log.error("Error producing message: " + e.getMessage());
            throw new NullPointerException("Error producing message: " + e.getMessage());
        }
    }


}
