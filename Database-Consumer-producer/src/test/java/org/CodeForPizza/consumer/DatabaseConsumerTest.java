package org.CodeForPizza.consumer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;

import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
class DatabaseConsumerTest {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private DatabaseConsumer databaseConsumer;


    @Test
    void consume_Success() {
        String message = "{\"title\":\"The Matrix\",\"year\":\"1999\"}";
        kafkaTemplate.send("movie", message);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assert(databaseConsumer.movieInfo.get("title").equals("The Matrix"));
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
            databaseConsumer.consume(message);
        });

    }
}