package org.CodeForPizza.consumer;

import org.CodeForPizza.dto.MovieDTO;
import org.CodeForPizza.service.MovieService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.kafka.core.KafkaTemplate;

import org.springframework.kafka.test.context.EmbeddedKafka;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9095", "port=9095"})
class DatabaseConsumerTest {

    @Autowired
    MovieService movieService;

    @Autowired
    private KafkaTemplate<String, MovieDTO> kafkaTemplate ;

    @Autowired
    private DatabaseConsumer databaseConsumer = new DatabaseConsumer();

    MovieDTO movieInfo;
    MovieDTO processedMovie;

    @BeforeEach
    void setUp() {
        movieInfo = new MovieDTO();
        movieInfo.setId(1L);
        movieInfo.setTitle("TestTitle");
        movieInfo.setYear("2021");
    }

    @Test
    void testConsumeSuccess() {
        kafkaTemplate.send("movie", movieInfo);
        databaseConsumer.consume(movieInfo);
        processedMovie = databaseConsumer.movieToSaveJson;
        assertEquals(movieInfo.toString(), processedMovie.toString());
    }


    @AfterEach
    void tearDown() {
        kafkaTemplate.flush();
        movieService.deleteById(processedMovie);
    }
}



