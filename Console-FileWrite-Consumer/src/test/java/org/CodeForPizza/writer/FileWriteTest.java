package org.CodeForPizza.writer;

import org.CodeForPizza.dto.MovieDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FileWriteTest {

    @Test
    void testWriteToFileFail() {
        FileWrite fileWrite = new FileWrite();
        assertThrows(RuntimeException.class, () -> fileWrite.writeToFile(new MovieDTO("Dr", "Year")));
    }


}