package org.CodeForPizza.output;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.CodeForPizza.dto.MovieDTO;
import org.junit.jupiter.api.Test;

class OutputTest {
    @Test
    void testMovieInformation() {
        assertEquals("Id: null Title: Dr Year: Year", Output.movieInformation(new MovieDTO("Dr", "Year")));
    }
}

