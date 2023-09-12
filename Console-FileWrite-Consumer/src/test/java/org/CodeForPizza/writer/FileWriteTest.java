package org.CodeForPizza.writer;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import java.io.FileWriter;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class FileWriteTest {

    @Mock
    private FileWriter mockWriter;

    private FileWrite fileWrite;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        fileWrite = new FileWrite(mockWriter);
    }

    @Test
    void writeToFile_Success() throws Exception {
        // Arrange
        JSONObject movieInfo = new JSONObject();
        movieInfo.put("name", "The Shawshank Redemption");
        movieInfo.put("year", 1994);

        //TODO: Write a test that verifies that the writeToFile method writes the movieInfo to the file.

    }

    @Test
    void writeToFile_Fail() throws Exception {
        JSONObject movieInfo = new JSONObject();
        movieInfo.put("title", "The Matrix");
        movieInfo.put("year", "1999");

        assertThrows(Exception.class, () -> {
            Mockito.doThrow(new RuntimeException()).when(mockWriter).write(movieInfo.toJSONString() + ",\n");
            fileWrite.writeToFile(movieInfo);
        });



    }
}