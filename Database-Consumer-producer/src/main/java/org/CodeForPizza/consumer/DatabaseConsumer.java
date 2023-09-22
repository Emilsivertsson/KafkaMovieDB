package org.CodeForPizza.consumer;


import lombok.extern.slf4j.Slf4j;
import org.CodeForPizza.dto.MovieDTO;
import org.CodeForPizza.entity.Movie;
import org.CodeForPizza.producer.DatabaseProducer;
import org.CodeForPizza.repository.MovieRepository;
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
public class DatabaseConsumer {

    private DatabaseProducer databaseProducer;
    private MovieRepository movieRepository;

    MovieDTO movieToReturnJson = new MovieDTO();

    Gson gson = new Gson();

    public DatabaseConsumer(DatabaseProducer databaseProducer, MovieRepository movieRepository) {
        this.databaseProducer = databaseProducer;
        this.movieRepository = movieRepository;
    }

    @KafkaListener(topics = "movie", groupId = "Database")
    public void consume(MovieDTO movieInfo) {
        try {
            log.info("Consumed message: " + movieInfo);
            String movieInfoString = gson.toJson(movieInfo);
            Movie movie = gson.fromJson(movieInfoString, Movie.class);

            movieToReturnJson = mapFromEntityToDTO(saveToDB(movie));
            produceMessage(movieToReturnJson);

        } catch (Exception e) {
            log.error("Error parsing message: " + movieInfo);
            log.error(e.getMessage());
            throw new NullPointerException("Error parsing message: " + movieInfo);
        }
    }

    private MovieDTO mapFromEntityToDTO(Movie movie) {
        try{
            movieToReturnJson.setId(movie.getId());
            movieToReturnJson.setTitle(movie.getTitle());
            movieToReturnJson.setYear(movie.getYear());
            return movieToReturnJson;
        } catch (Exception e) {
            log.error("Error mapping from entity to DTO: " + movie);
            log.error(e.getMessage());
            throw new NullPointerException("Error mapping from entity to DTO: " + movie);
        }
    }

    private Movie saveToDB(Movie movie) {
        try{
        return movieRepository.save(movie);
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