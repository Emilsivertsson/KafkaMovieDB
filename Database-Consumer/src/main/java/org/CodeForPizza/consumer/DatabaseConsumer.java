package org.CodeForPizza.consumer;

import org.CodeForPizza.entity.Movie;
import org.CodeForPizza.repository.MovieRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class DatabaseConsumer {

    private static final Logger Logger = org.slf4j.LoggerFactory.getLogger(DatabaseConsumer.class);

    @Autowired
    private MovieRepository movieRepository;

    public DatabaseConsumer(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @KafkaListener(topics = "movie", groupId = "Database")
    public void consume(String message) {
        Logger.info(String.format("Consumed message:" + message));
        Movie movie = new Movie();
        movie.setMovieInfo(message);
        movieRepository.save(movie);

    }
}
