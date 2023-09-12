package org.CodeForPizza.producer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {DatabaseProducer.class})
@ExtendWith(SpringExtension.class)
class DatabaseProducerTest {

    @Autowired
    private DatabaseProducer databaseProducer;

    @MockBean
    private KafkaTemplate<String, String> kafkaTemplate;

    @Test
    void sendMessage_Success() {
        when(kafkaTemplate.send(Mockito.<String>any(), Mockito.<String>any())).thenReturn(new CompletableFuture<>());
        databaseProducer.sendMessage("Movie Info");
        verify(kafkaTemplate).send(Mockito.<String>any(), Mockito.<String>any());
    }

    @Test
    void sendMessage_Fail() {
        when(kafkaTemplate.send(Mockito.<String>any(), Mockito.<String>any())).thenThrow(new RuntimeException("An error occurred"));
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            databaseProducer.sendMessage("Movie Info");
        });

        assertEquals("An error occurred", exception.getMessage());
        verify(kafkaTemplate).send(Mockito.<String>any(), Mockito.<String>any());
        //TODO finish this test
    }
}