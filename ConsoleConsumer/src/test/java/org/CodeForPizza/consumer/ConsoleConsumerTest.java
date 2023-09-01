package org.CodeForPizza.consumer;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@ContextConfiguration(classes = {ConsoleConsumer.class})
@ExtendWith(SpringExtension.class)
class ConsoleConsumerTest {
    @Autowired
    private ConsoleConsumer consoleConsumer;

    @Mock
    private JSONParser jsonParser;

    @Test
    void testConsume() throws ParseException {

    }

}

