package org.CodeForPizza.consumer;

import lombok.extern.slf4j.Slf4j;

import org.CodeForPizza.dto.MovieDTO;
import org.CodeForPizza.writer.FileWrite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;


/**
 * This class implements a kafka consumer that listens to the topic "returningData", group "Console".
 * It uses a KafkaListener to listen to the topic.
 * The listener is configured in the application.properties file.
 */
@Slf4j
@Service
public class ConsoleConsumer {

    @Autowired
    private final FileWrite fileWrite = new FileWrite();


    MovieDTO movieToPrint = new MovieDTO();

    @KafkaListener(topics = "returningData", groupId = "Console")
    public void consume(MovieDTO movieInfo) {
        try{
            log.info("Consumed message: " + movieInfo);
            movieToPrint = movieInfo;
            System.out.println("Movie information received from Database.");

            writeToFile(movieInfo);

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new NullPointerException("Error parsing message: " + movieInfo);
        }
    }

    private void writeToFile(MovieDTO movieInfo) {
        fileWrite.writeToFile(movieInfo);
    }

}

