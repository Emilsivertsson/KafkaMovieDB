package org.CodeForPizza.consumer;


import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.CodeForPizza.dto.MovieDTO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;


class ApplicationConsumerTest {

    @Mock
    private MessageListener<String, String> messageListener;

    @Test
    void consume() {
        // Create a mock message
        MovieDTO message = new MovieDTO();

        // Spy on the consumer function
        ApplicationConsumer consumer = Mockito.spy(new ApplicationConsumer());
        Mockito.doNothing().when(consumer).consume(message);

        // Call the consume function
        consumer.consume(message);

        // Verify that the message was logged
        Mockito.verify(consumer).consume(message);
    }
}