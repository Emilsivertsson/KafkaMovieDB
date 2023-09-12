package org.CodeForPizza.consumer;

import org.CodeForPizza.writer.FileWrite;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {ConsoleConsumer.class})
@ExtendWith(SpringExtension.class)
class ConsoleConsumerTest {
    @Mock
    private FileWrite fileWrite;

    private ConsoleConsumer consoleConsumer;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        consoleConsumer = new ConsoleConsumer();
    }

    @Test
    void testConsume_success() throws Exception {
        String message = "{\"title\":\"Test Movie\"}";

            JSONParser mockParser = mock(JSONParser.class);
            when(mockParser.parse(message)).thenReturn(new JSONObject());
            consoleConsumer.consume(message);
    }

    @Test
    void testConsume_Fail() throws ParseException {
        String message = "\"title\":\"Test Movie\"";

        JSONParser mockParser = mock(JSONParser.class);
        when(mockParser.parse(message)).thenThrow(NullPointerException.class);

        assertThrows(NullPointerException.class, () -> {
            consoleConsumer.consume(message);
        });

        verify(fileWrite, never()).writeToFile(any(JSONObject.class));
    }
}

