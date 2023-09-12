package org.CodeForPizza.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.apache.kafka.clients.admin.NewTopic;
import org.junit.jupiter.api.Test;

class KafkaTopicConfigTest {

    @Test
    void testMovieTopicJson() {

        NewTopic actualResult = (new KafkaTopicConfig()).movieTopicJson();
        assertNull(actualResult.configs());
        assertNull(actualResult.replicasAssignments());
        assertEquals("movie", actualResult.name());
    }
}

