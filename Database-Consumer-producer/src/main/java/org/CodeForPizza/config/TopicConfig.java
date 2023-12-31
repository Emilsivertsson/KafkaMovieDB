package org.CodeForPizza.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * This class creates the returningData topic with 3 partitions and 3 replicas.
 */
@Configuration
public class TopicConfig {

    @Bean
    public NewTopic returningDataTopic() {
        return TopicBuilder.name("returningData")
                .partitions(3)
                .replicas(3)
                .build();
    }
}
