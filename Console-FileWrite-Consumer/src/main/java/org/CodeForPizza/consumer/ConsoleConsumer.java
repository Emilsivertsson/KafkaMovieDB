package org.CodeForPizza.consumer;

import lombok.extern.slf4j.Slf4j;

import org.CodeForPizza.writer.FileWrite;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


/**
 * This class implements a kafka consumer that listens to the topic "returningData", group "Console".
 * It uses a KafkaListener to listen to the topic.
 * The listener is configured in the application.properties file.
 */
@Slf4j
@Service
public class ConsoleConsumer {

    JSONObject movieInfo = new JSONObject();

    private FileWrite fileWrite = new FileWrite();

    @KafkaListener(topics = "returningData", groupId = "Console")
    public void consume(String message) throws Exception {
        try{
            log.info("Consumed message: " + message);
            JSONParser parser = new JSONParser();
            movieInfo = (JSONObject) parser.parse(message);
            System.out.println("Movie information received from Database.");
            fileWrite.writeToFile(movieInfo);

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new NullPointerException("Error parsing message: " + message);
        }
    }


}

