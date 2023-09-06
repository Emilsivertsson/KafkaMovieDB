package org.CodeForPizza.consumer;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.CodeForPizza.MovieDBApplication;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

@Getter
@Slf4j
@Service
public class ApplicationConsumer {

    public ApplicationConsumer( ) {

    }


    @KafkaListener(topics = "movie", groupId = "user")
    public void consume(String message) {
        try {
            log.info("Consumed message: " + message);
            JSONParser parser = new JSONParser();
            JSONObject movieInfo = (JSONObject) parser.parse(message);
            System.out.println("=============================================");
            System.out.println("Movie information saved in the database:");
            System.out.println("Title: " + movieInfo.get("title"));
            System.out.println("Year: " + movieInfo.get("year"));

        } catch (Exception e) {
            log.error("Error parsing message: " + message);
        }
    }

}
