package org.CodeForPizza.producer;


import lombok.extern.slf4j.Slf4j;
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

    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public DatabaseProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String movieInfo) {
        try{
            log.info("Producing message: " + movieInfo);
            kafkaTemplate.send("returningData", movieInfo);
        } catch (Exception e) {
            log.error("Error producing message: " + movieInfo);
        }
    }


}
