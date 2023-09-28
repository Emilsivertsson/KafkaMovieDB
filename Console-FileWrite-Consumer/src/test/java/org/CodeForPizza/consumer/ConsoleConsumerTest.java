package org.CodeForPizza.consumer;

import org.CodeForPizza.dto.MovieDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9096", "port=9096"})
class ConsoleConsumerTest {

    @Autowired
    private KafkaTemplate<String, MovieDTO> kafkaTemplate ;

    @Autowired
    private ConsoleConsumer consoleConsumer = new ConsoleConsumer();

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
        consoleConsumer.fileWriteEnabled = false;
        kafkaTemplate.send("returningData", movieInfo);
        consoleConsumer.consume(movieInfo);
        processedMovie = consoleConsumer.movieToPrint;
        assertEquals(movieInfo.toString(), processedMovie.toString());

    }

    @AfterEach
    void tearDown() {
        kafkaTemplate.flush();
    }

}