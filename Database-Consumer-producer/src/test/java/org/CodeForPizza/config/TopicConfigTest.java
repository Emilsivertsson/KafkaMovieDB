package org.CodeForPizza.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TopicConfigTest {

    @Test
    void returningDataTopic_Success() {
        TopicConfig topicConfig = new TopicConfig();
        NewTopic newTopic = topicConfig.returningDataTopic();
        assertEquals("returningData", newTopic.name());
        assertEquals(3, newTopic.numPartitions());
        assertEquals(3, newTopic.replicationFactor());
    }
}