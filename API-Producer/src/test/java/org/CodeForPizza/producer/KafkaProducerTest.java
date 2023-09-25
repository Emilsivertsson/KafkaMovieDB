package org.CodeForPizza.producer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.concurrent.CompletableFuture;

import org.CodeForPizza.dto.MovieDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {KafkaProducer.class})
@ExtendWith(SpringExtension.class)
class KafkaProducerTest {
    @Autowired
    private KafkaProducer kafkaProducer;

    @MockBean
    private KafkaTemplate<String, MovieDTO> kafkaTemplate;

    @Mock
    private MovieDTO movieDTO;



    @Test
    void testSendMessage_Success() {

        when(kafkaTemplate.send(Mockito.<String>any(), Mockito.<MovieDTO>any())).thenReturn(CompletableFuture.completedFuture(null));
        kafkaProducer.sendMessage(movieDTO);
        verify(kafkaTemplate).send(Mockito.<String>any(), Mockito.<MovieDTO>any());
    }

    @Test
    void testSendMessage_Fail() throws Exception {
        when(kafkaTemplate.send(Mockito.<String>any(), Mockito.<MovieDTO>any())).thenThrow(new RuntimeException("An error occurred"));
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            kafkaProducer.sendMessage(movieDTO);
        });

        assertEquals("An error occurred", exception.getMessage());
        verify(kafkaTemplate).send(Mockito.<String>any(), Mockito.<MovieDTO>any());
    }


}

