package org.CodeForPizza.consumer;

import org.CodeForPizza.entity.Movie;
import org.CodeForPizza.producer.DatabaseProducer;
import org.CodeForPizza.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {DatabaseConsumer.class})
@ExtendWith(SpringExtension.class)
class DatabaseConsumerTest {
    @Autowired
    private DatabaseConsumer databaseConsumer;

    @MockBean
    private MovieRepository movieRepository;

}