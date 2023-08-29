package org.CodeForPizza.consumer;

import lombok.extern.slf4j.Slf4j;
import org.CodeForPizza.entity.Movie;
import org.CodeForPizza.repository.MovieRepository;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DatabaseConsumer {

    private MovieRepository movieRepository;

    @Autowired
    public DatabaseConsumer(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @KafkaListener(topics = "movie", groupId = "Database")
    public void consume(String message)  {
        try {
            log.info("Consumed message: " + message);

            String title = extractValue(message, "title");
            String year = extractValue(message, "year");

            Movie movie = new Movie();
            movie.setTitle(title);
            movie.setYear(year);
            movieRepository.save(movie);
        } catch (Exception e) {
            log.error("Error parsing message: " + message);
        }
    }

    // Helper method to extract values from payload string
    private String extractValue(String payload, String field) {
        try{
        int startIndex = payload.indexOf(field + "='") + field.length() + 2;
        int endIndex = payload.indexOf("'", startIndex);
        return payload.substring(startIndex, endIndex);
        } catch (Exception e) {
            log.error("Error parsing message: " + payload);
            return "";
        }
    }
}