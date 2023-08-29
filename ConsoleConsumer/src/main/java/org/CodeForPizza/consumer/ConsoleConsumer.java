package org.CodeForPizza.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ConsoleConsumer {

    @KafkaListener(topics = "movie", groupId = "Console")
    public void consume(String message) {
        try{
        log.info(String.format("Consumed message:" + message));
        System.out.println(message);
        } catch (Exception e) {
            log.error("Error parsing message: " + message);
        }
    }
}

