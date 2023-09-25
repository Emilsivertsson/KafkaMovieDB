package org.CodeForPizza.consumer;

import org.CodeForPizza.dto.MovieDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;

import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9095", "port=9095"})
class DatabaseConsumerTest {


    @Autowired
    KafkaTemplate<String, MovieDTO> kafkaTemplate;

    @Autowired
    private DatabaseConsumer databaseConsumer;


    @Test
    void consume_Success() {
        MovieDTO message = new MovieDTO();
        message.setTitle("The Matrix");
        kafkaTemplate.send("movie", message);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assert(databaseConsumer.movieToSaveJson.getTitle().equals("The Matrix"));
    }


}


