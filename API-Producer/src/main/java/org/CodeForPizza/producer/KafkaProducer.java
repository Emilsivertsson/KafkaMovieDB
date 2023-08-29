package org.CodeForPizza.producer;

import org.CodeForPizza.entity.Movie;
import org.slf4j.Logger;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class KafkaProducer {

    private static final Logger Logger = org.slf4j.LoggerFactory.getLogger(KafkaProducer.class);

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Movie movieInfo) {
        try{
        Logger.info(String.format("Producing message: " + movieInfo.toString()));
        kafkaTemplate.send("movie", movieInfo.toString());
        } catch (Exception e) {
            Logger.error("Error producing message: " + movieInfo.toString());
        }
    }
}
