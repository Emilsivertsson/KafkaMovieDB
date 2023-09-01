package org.CodeForPizza.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.apache.kafka.clients.admin.NewTopic;
import org.junit.jupiter.api.Test;

class KafkaTopicConfigTest {

    @Test
    void testMovieTopicJson() {

        NewTopic actualMovieTopicJsonResult = (new KafkaTopicConfig()).movieTopicJson();
        assertNull(actualMovieTopicJsonResult.configs());
        assertNull(actualMovieTopicJsonResult.replicasAssignments());
        assertEquals("movie", actualMovieTopicJsonResult.name());
    }
}

