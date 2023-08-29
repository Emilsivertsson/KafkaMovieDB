package org.CodeForPizza.producer;

import lombok.extern.slf4j.Slf4j;
import org.CodeForPizza.entity.Movie;
import org.slf4j.Logger;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Movie movieInfo) {
        try{
        log.info(String.format("Producing message: " + movieInfo.toString()));
        kafkaTemplate.send("movie", movieInfo.toString());
        } catch (Exception e) {
            log.error("Error producing message: " + movieInfo.toString());
        }
    }
}
