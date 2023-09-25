package org.CodeForPizza.producer;

import org.CodeForPizza.dto.MovieDTO;
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
    private KafkaTemplate<String, MovieDTO> kafkaTemplate;

    MovieDTO movieDTO = new MovieDTO();

    @Test
    void sendMessage_Success() {
        when(kafkaTemplate.send(Mockito.any(), Mockito.any())).thenReturn(new CompletableFuture<>());
        databaseProducer.sendMessage(movieDTO);
        verify(kafkaTemplate).send(Mockito.any(), Mockito.any());
    }

    @Test
    void sendMessage_Fail() {
        when(kafkaTemplate.send(Mockito.any(), Mockito.any())).thenThrow(new RuntimeException("An error occurred"));
        RuntimeException exception = assertThrows(RuntimeException.class, () -> databaseProducer.sendMessage(movieDTO));

        assertEquals("Error producing message: An error occurred", exception.getMessage());
        verify(kafkaTemplate).send(Mockito.any(), Mockito.any());
    }


}