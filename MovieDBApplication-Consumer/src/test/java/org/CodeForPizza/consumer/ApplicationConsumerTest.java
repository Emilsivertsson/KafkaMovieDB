package org.CodeForPizza.consumer;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.CodeForPizza.dto.MovieDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@SpringBootTest
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9096", "port=9096"})
class ApplicationConsumerTest {

    @Autowired
    private KafkaTemplate<String, MovieDTO> kafkaTemplate ;

    @Autowired
    private ApplicationConsumer applicationConsumer = new ApplicationConsumer();

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
    void consume() {
        kafkaTemplate.send("movie", movieInfo);
        applicationConsumer.consume(movieInfo);
        processedMovie = applicationConsumer.movieFromDataBase;
        assertEquals(movieInfo.toString(), processedMovie.toString());
    }

    @AfterEach
    void tearDown() {
        kafkaTemplate.flush();
    }
}