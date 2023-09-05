package org.CodeForPizza.consumer;

import lombok.extern.slf4j.Slf4j;
import org.CodeForPizza.entity.Movie;
import org.CodeForPizza.producer.DatabaseProducer;
import org.CodeForPizza.repository.MovieRepository;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DatabaseConsumer {

    private DatabaseProducer databaseProducer;
    private MovieRepository movieRepository;


    public DatabaseConsumer(DatabaseProducer databaseProducer, MovieRepository movieRepository) {
        this.databaseProducer = databaseProducer;
        this.movieRepository = movieRepository;
    }

    @KafkaListener(topics = "movie", groupId = "Database")
    public void consume(String message) {
        try {
            log.info("Consumed message: " + message);
            JSONParser parser = new JSONParser();
            JSONObject movieInfo = (JSONObject) parser.parse(message);
            Movie movie = new Movie(movieInfo.get("title").toString(), movieInfo.get("year").toString());
            movieRepository.save(movie);

            try{
            databaseProducer.sendMessage(movieInfo.toJSONString());
            } catch (Exception e) {
                log.error("Error producing message: " + movieInfo);
                log.error(e.getMessage());
            }

        } catch (Exception e) {
            log.error("Error parsing message: " + message);
            log.error(e.getMessage());
        }

    }
}