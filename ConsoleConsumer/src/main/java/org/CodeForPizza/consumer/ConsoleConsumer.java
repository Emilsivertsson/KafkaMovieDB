package org.CodeForPizza.consumer;

import lombok.extern.slf4j.Slf4j;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class ConsoleConsumer {

    @KafkaListener(topics = "movie", groupId = "Console")
    public void consume(String message) {
        try{
            log.info("Consumed message: " + message);
            JSONParser parser = new JSONParser();
            JSONObject movieInfo = (JSONObject) parser.parse(message);
            System.out.println("Title: " + movieInfo.get("title"));
            System.out.println("Year: " + movieInfo.get("year"));

        } catch (Exception e) {
            log.error("Error parsing message: " + message);
        }
    }
}

