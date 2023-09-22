package org.CodeForPizza.consumer;

import lombok.extern.slf4j.Slf4j;

import org.CodeForPizza.dto.MovieDTO;
import org.CodeForPizza.writer.FileWrite;
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

    MovieDTO movie = new MovieDTO();

    Gson gson = new Gson();

    private FileWrite fileWrite = new FileWrite();

    @KafkaListener(topics = "returningData", groupId = "Console")
    public void consume(MovieDTO movieInfo) throws Exception {
        try{
            log.info("Consumed message: " + movieInfo);
            String movieInfoString = gson.toJson(movieInfo);
            movie = gson.fromJson(movieInfoString, MovieDTO.class);

            System.out.println("Movie information received from Database.");
            fileWrite.writeToFile(movieInfo);

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new NullPointerException("Error parsing message: " + movieInfo);
        }
    }


}

