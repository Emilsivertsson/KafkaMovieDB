package org.CodeForPizza.consumer;

import org.CodeForPizza.dto.MovieDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.kafka.listener.MessageListener;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleConsumerTest {
    @Mock
    private MessageListener<String, String> messageListener;

    @Test
    void testConsumeSuccess() {

        MovieDTO movieInfo = new MovieDTO();

        ConsoleConsumer consumer = Mockito.spy(new ConsoleConsumer());
        Mockito.doNothing().when(consumer).consume(movieInfo);
        consumer.consume(movieInfo);
        Mockito.verify(consumer).consume(movieInfo);

    }

}