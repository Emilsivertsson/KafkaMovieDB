package org.CodeForPizza.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * This class is used to create the movie topic with 3 partitions and 3 replicas.
 */
@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic movieTopicJson() {
        return TopicBuilder.name("movie")
                .partitions(3)
                .replicas(3)
                .build();
    }

}
