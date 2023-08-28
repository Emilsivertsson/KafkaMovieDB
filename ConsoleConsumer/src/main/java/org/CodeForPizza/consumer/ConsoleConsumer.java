package org.CodeForPizza.consumer;

import org.slf4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsoleConsumer {

    private static final Logger Logger = org.slf4j.LoggerFactory.getLogger(ConsoleConsumer.class);

    @KafkaListener(topics = "movie", groupId = "Console")
    public void consume(String message) {
        Logger.info(String.format("Consumed message:" + message));
        System.out.println(message);

    }
}

