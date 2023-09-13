package org.CodeForPizza.consumer;


import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;

import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
class ApplicationConsumerTest {
    
    @Autowired
    private ApplicationConsumer applicationConsumer;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    

    @Test
    void consume_Success() {
        String message = "{\"title\":\"The Matrix\",\"year\":\"1999\"}";
        kafkaTemplate.send("movie", message);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assert(applicationConsumer.getMovieInfo().get("title").equals("The Matrix"));

    }

    @Test
    void consume_Fail() {
        String message = "\"title\":\"The Matrix\",\"yea\"1999\"}";
        kafkaTemplate.send("movie", message);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThrows(NullPointerException.class, () -> {
            applicationConsumer.consume(message);
        });


    }

}