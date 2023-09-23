package org.CodeForPizza.consumer;

import org.CodeForPizza.writer.FileWrite;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9095", "port=9095"})
class ConsoleConsumerTest {

    /*
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ConsoleConsumer consoleConsumer;

    @Test
    void testConsume1Success() {
        String message = "{\"title\":\"The Matrix\",\"year\":\"1999\"}";
        kafkaTemplate.send("returningData", message);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assert(consoleConsumer.movieInfo.get("title").equals("The Matrix"));
    }

    @Test
    void testConsume2Fail() {
        String message = "\"title\":\"The Matrix\",\"yea\"1999\"}";
        kafkaTemplate.send("returningData", message);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThrows(NullPointerException.class, () -> consoleConsumer.consume(message));
    }

     */
}

