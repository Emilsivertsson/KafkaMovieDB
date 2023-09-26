package org.CodeForPizza.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

import org.CodeForPizza.dto.MovieDTO;
import org.CodeForPizza.entity.Movie;
import org.CodeForPizza.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {MovieServiceImpl.class})
@ExtendWith(SpringExtension.class)
class MovieServiceImplTest {
    @MockBean
    private MovieRepository movieRepository;

    @Autowired
    private MovieServiceImpl movieServiceImpl;

    @Test
    void testSave() {
        Movie movie = new Movie();
        movie.setId(1L);
        movie.setTitle("Dr");
        movie.setYear("Year");
        when(movieRepository.save(Mockito.<Movie>any())).thenReturn(movie);
        MovieDTO actualSaveResult = movieServiceImpl.save(new MovieDTO("Dr", "Year"));
        assertEquals(1L, actualSaveResult.getId().longValue());
        assertEquals("Year", actualSaveResult.getYear());
        assertEquals("Dr", actualSaveResult.getTitle());
        verify(movieRepository).save(Mockito.<Movie>any());
    }

    @Test
    void testSaveFail() {
        MovieDTO movieDTO = mock(MovieDTO.class);
        when(movieDTO.getId()).thenThrow(new RuntimeException("foo"));
        assertNull(movieServiceImpl.save(movieDTO));
        verify(movieDTO).getId();
    }


}

