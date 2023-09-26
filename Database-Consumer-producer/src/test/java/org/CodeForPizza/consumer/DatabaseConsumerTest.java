package org.CodeForPizza.consumer;

import org.CodeForPizza.dto.MovieDTO;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;



class DatabaseConsumerTest {

    @Mock
    private MessageListener<String, String> messageListener;

    @Test
    void testConsumeSuccess() {

        MovieDTO movieInfo = new MovieDTO();

        DatabaseConsumer consumer = Mockito.spy(new DatabaseConsumer());
        Mockito.doNothing().when(consumer).consume(movieInfo);

        consumer.consume(movieInfo);

        Mockito.verify(consumer).consume(movieInfo);

    }

}



