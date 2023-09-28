package org.CodeForPizza.consumer;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.CodeForPizza.dto.MovieDTO;
import org.CodeForPizza.producer.DatabaseProducer;
import org.CodeForPizza.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;

/**
 * this class implements a kafka consumer that listens to the topic "movie", group "Database".
 * It uses a KafkaListener to listen to the topic, and saves the movie information to the database.
 * The listener is configured in the application.properties file.
 * It also uses a DatabaseProducer to send the message to the topic "returningData".
 */
@Slf4j
@Service
@AllArgsConstructor
@NoArgsConstructor
public class DatabaseConsumer {

    @Autowired
    private DatabaseProducer databaseProducer;

    @Autowired
    private MovieService movieService;

    MovieDTO movieToSaveJson = new MovieDTO();
    MovieDTO movieToReturnJson = new MovieDTO();

    Gson gson = new Gson();

    @KafkaListener(topics = "movie", groupId = "Database")
    public void consume(MovieDTO movieInfo) {
        try {
            log.info("Consumed message: " + movieInfo);
            String movieInfoString = gson.toJson(movieInfo);
            movieToSaveJson = gson.fromJson(movieInfoString, MovieDTO.class);
            produceMessage(saveToDB(movieToSaveJson));
        } catch (Exception e) {
            log.error("Error parsing message: " + movieInfo);
            log.error(e.getMessage());
            throw new NullPointerException("Error parsing message: " + movieInfo);
        }
    }

    private MovieDTO saveToDB(MovieDTO movie) {
        try{
        return movieService.save(movie);
        } catch (Exception e) {
            log.error("Error saving movie to database: " + movie);
            log.error(e.getMessage());
            throw new NullPointerException("Error saving movie to database: " + movie);
        }
    }

    private void produceMessage(MovieDTO movieInfo) {
        try{
        databaseProducer.sendMessage(movieInfo);
        log.info("Producing message: " + movieInfo);
        } catch (Exception e) {
            log.error("Error producing message: " + movieInfo);
            log.error(e.getMessage());
            throw new NullPointerException("Error producing message: " + movieInfo);
        }
    }
}