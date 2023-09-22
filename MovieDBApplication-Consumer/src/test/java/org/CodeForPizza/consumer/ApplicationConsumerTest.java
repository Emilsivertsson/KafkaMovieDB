package org.CodeForPizza.consumer;


import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;

import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9095", "port=9095"})
class ApplicationConsumerTest {

    /*
    @Autowired
    private ApplicationConsumer applicationConsumer;

    @Autowired
    private KafkaTemplate<String, Movie> kafkaTemplate;
    

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

     */

}