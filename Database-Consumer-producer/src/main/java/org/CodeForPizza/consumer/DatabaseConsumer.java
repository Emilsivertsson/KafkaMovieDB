package org.CodeForPizza.consumer;


import lombok.extern.slf4j.Slf4j;
import org.CodeForPizza.entity.Movie;
import org.CodeForPizza.producer.DatabaseProducer;
import org.CodeForPizza.repository.MovieRepository;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

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

    JSONObject movieInfo = new JSONObject();

    public DatabaseConsumer(DatabaseProducer databaseProducer, MovieRepository movieRepository) {
        this.databaseProducer = databaseProducer;
        this.movieRepository = movieRepository;
    }

    @KafkaListener(topics = "movie", groupId = "Database")
    public void consume(String message) {
        try {
            log.info("Consumed message: " + message);
            JSONParser parser = new JSONParser();
            movieInfo = (JSONObject) parser.parse(message);
            Movie movie = new Movie(movieInfo.get("title").toString(), movieInfo.get("year").toString());

            saveToDB(movie);
            JSONObject movieToReturnJson = getMovieFromDB(movie);
            produceMessage(movieToReturnJson);

        } catch (Exception e) {
            log.error("Error parsing message: " + message);
            log.error(e.getMessage());
            throw new NullPointerException("Error parsing message: " + message);
        }
    }

    private JSONObject getMovieFromDB(Movie movie) {
        Movie MovieToReturn = movieRepository.findByTitleAndYear(movie.getTitle(), movie.getYear());
        JSONObject movieToReturnJson = new JSONObject();
        movieToReturnJson.put("id", MovieToReturn.getId());
        movieToReturnJson.put("title", MovieToReturn.getTitle());
        movieToReturnJson.put("year", MovieToReturn.getYear());
        return movieToReturnJson;
    }

    private void saveToDB(Movie movie) {
        try{
        movieRepository.save(movie);
        } catch (Exception e) {
            log.error("Error saving movie to database: " + movie);
            log.error(e.getMessage());
            throw new NullPointerException("Error saving movie to database: " + movie);
        }
    }

    private void produceMessage(JSONObject movieInfo) {
        try{
        databaseProducer.sendMessage(movieInfo.toJSONString());
        } catch (Exception e) {
            log.error("Error producing message: " + movieInfo);
            log.error(e.getMessage());
            throw new NullPointerException("Error producing message: " + movieInfo);
        }
    }
}