package org.CodeForPizza.consumer;

import org.CodeForPizza.entity.Movie;
import org.CodeForPizza.repository.MovieRepository;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class DatabaseConsumer {

    private static final Logger Logger = org.slf4j.LoggerFactory.getLogger(DatabaseConsumer.class);



    private MovieRepository movieRepository;

    @Autowired
    public DatabaseConsumer(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @KafkaListener(topics = "movie", groupId = "Database")
    public void consume(String message)  {
        try {
            Logger.info("Consumed message: " + message);

            String title = extractValue(message, "title");
            String year = extractValue(message, "year");

            Movie movie = new Movie();
            movie.setTitle(title);
            movie.setYear(year);
            movieRepository.save(movie);
        } catch (Exception e) {
            Logger.error("Error parsing message: " + message);
        }
    }

    // Helper method to extract values from payload string
    private String extractValue(String payload, String field) {
        try{
        int startIndex = payload.indexOf(field + "='") + field.length() + 2;
        int endIndex = payload.indexOf("'", startIndex);
        return payload.substring(startIndex, endIndex);
        } catch (Exception e) {
            Logger.error("Error parsing message: " + payload);
            return "";
        }
    }
}