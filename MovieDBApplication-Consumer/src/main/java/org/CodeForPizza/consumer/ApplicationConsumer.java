package org.CodeForPizza.consumer;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


/**
 * This class implements a kafka consumer that listens to the topic "movie", group "user".
 * It uses a KafkaListener to listen to the topic.
 * it prints out the movie information to the console.
 */
@Getter
@Slf4j
@Service
public class ApplicationConsumer {

    JSONObject movieInfo = new JSONObject();

    public ApplicationConsumer( ) {

    }


    @KafkaListener(topics = "movie", groupId = "user")
    public void consume(String message) {
        try {
            log.info("Consumed message: " + message);
            JSONParser parser = new JSONParser();
            movieInfo = (JSONObject) parser.parse(message);
            System.out.println("=============================================");
            System.out.println("Movie information saved in the database:");
            System.out.println("Title: " + movieInfo.get("title"));
            System.out.println("Year: " + movieInfo.get("year"));

        } catch (Exception e) {
            log.error("Error parsing message: " + message);
            throw new NullPointerException("Error parsing message: " + message);
        }
    }


}
