package org.CodeForPizza.consumer;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;

public class ConsoleConsumer {

    private static final Logger Logger = org.slf4j.LoggerFactory.getLogger(ConsoleConsumer.class);

    @KafkaListener(topics = "movie", groupId = "Console")
    public void consume(String message) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject movie = (JSONObject) parser.parse(message.toString());
        System.out.println(movie.toString());
        Logger.info(String.format("Consumed message:" + message));
    }
}
