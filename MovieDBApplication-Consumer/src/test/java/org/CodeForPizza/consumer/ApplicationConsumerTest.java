package org.CodeForPizza.consumer;


import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;

import org.CodeForPizza.dto.MovieDTO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9095", "port=9095"})
class ApplicationConsumerTest {

    @Autowired
    private ApplicationConsumer applicationConsumer;



    @Test
    void testConsume_Success1() {
        applicationConsumer.consume(new MovieDTO("Mr", "Year"));
        MovieDTO expectedMovieFromDataBase = applicationConsumer.movieFromDataBase;
        assertSame(expectedMovieFromDataBase, applicationConsumer.getMovieFromDataBase());
    }

    @Test
    void testConsume_Success2() {
        MovieDTO movieInfo = new MovieDTO();
        movieInfo.setId(1L);
        applicationConsumer.consume(movieInfo);
        MovieDTO expectedMovieFromDataBase = applicationConsumer.movieFromDataBase;
        assertSame(expectedMovieFromDataBase, applicationConsumer.getMovieFromDataBase());
    }


}