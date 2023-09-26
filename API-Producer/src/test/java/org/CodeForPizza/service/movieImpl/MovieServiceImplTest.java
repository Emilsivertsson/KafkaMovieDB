package org.CodeForPizza.service.movieImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.CodeForPizza.dto.MovieDTO;

import org.CodeForPizza.entity.Movie;
import org.CodeForPizza.repository.MovieRepository;
import org.junit.jupiter.api.Disabled;
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
    void testFindByIdSuccess() {
        Movie movie = new Movie();
        movie.setId(1L);
        movie.setTitle("Dr");
        movie.setYear("Year");
        Optional<Movie> ofResult = Optional.of(movie);
        when(movieRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        MovieDTO actualFindByIdResult = movieServiceImpl.findById(1L);
        assertEquals(1L, actualFindByIdResult.getId().longValue());
        assertEquals("Year", actualFindByIdResult.getYear());
        assertEquals("Dr", actualFindByIdResult.getTitle());
        verify(movieRepository).findById(Mockito.<Long>any());
    }


    @Test
    void testFindByIdFailIfEmpty() {
        Optional<Movie> emptyResult = Optional.empty();
        when(movieRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        assertThrows(RuntimeException.class, () -> movieServiceImpl.findById(1L));
        verify(movieRepository).findById(Mockito.<Long>any());
    }

    @Test
    void testFindByIDFail() {
        when(movieRepository.findById(Mockito.<Long>any())).thenThrow(new RuntimeException("Movie not found"));
        assertThrows(RuntimeException.class, () -> movieServiceImpl.findById(1L));
        verify(movieRepository).findById(Mockito.<Long>any());
    }

    @Test
    void testSaveSuccess() {
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

    @Test
    void testDeleteByIdSuccess() {
        Movie movie = new Movie();
        movie.setId(1L);
        movie.setTitle("Dr");
        movie.setYear("Year");
        Optional<Movie> ofResult = Optional.of(movie);
        doNothing().when(movieRepository).deleteById(Mockito.<Long>any());
        when(movieRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        movieServiceImpl.deleteById(1L);
        verify(movieRepository).findById(Mockito.<Long>any());
        verify(movieRepository).deleteById(Mockito.<Long>any());
    }


    @Test
    void testDeleteByIdFail() {
        Movie movie = new Movie();
        movie.setId(1L);
        movie.setTitle("Dr");
        movie.setYear("Year");
        Optional<Movie> ofResult = Optional.of(movie);
        doThrow(new RuntimeException("foo")).when(movieRepository).deleteById(Mockito.<Long>any());
        when(movieRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(RuntimeException.class, () -> movieServiceImpl.deleteById(1L));
        verify(movieRepository).findById(Mockito.<Long>any());
        verify(movieRepository).deleteById(Mockito.<Long>any());
    }


    @Test
    void testDeleteByIdFailIfEmpty() {
        Optional<Movie> emptyResult = Optional.empty();
        when(movieRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        assertThrows(RuntimeException.class, () -> movieServiceImpl.deleteById(1L));
        verify(movieRepository).findById(Mockito.<Long>any());
    }


    @Test
    void testUpdateSuccess() {
        Movie movie = new Movie();
        movie.setId(1L);
        movie.setTitle("Dr");
        movie.setYear("Year");
        Optional<Movie> ofResult = Optional.of(movie);

        Movie movie2 = new Movie();
        movie2.setId(1L);
        movie2.setTitle("Dr");
        movie2.setYear("Year");
        when(movieRepository.save(Mockito.<Movie>any())).thenReturn(movie2);
        when(movieRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        MovieDTO actualUpdateResult = movieServiceImpl.update(1L, new MovieDTO("Dr", "Year"));
        assertEquals(1L, actualUpdateResult.getId().longValue());
        assertEquals("Year", actualUpdateResult.getYear());
        assertEquals("Dr", actualUpdateResult.getTitle());
        verify(movieRepository).save(Mockito.<Movie>any());
        verify(movieRepository).findById(Mockito.<Long>any());
    }


    @Test
    void testUpdateFail() {
        Movie movie = new Movie();
        movie.setId(1L);
        movie.setTitle("Dr");
        movie.setYear("Year");
        Optional<Movie> ofResult = Optional.of(movie);
        when(movieRepository.save(Mockito.<Movie>any())).thenThrow(new RuntimeException("foo"));
        when(movieRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(RuntimeException.class, () -> movieServiceImpl.update(1L, new MovieDTO("Dr", "Year")));
        verify(movieRepository).save(Mockito.<Movie>any());
        verify(movieRepository).findById(Mockito.<Long>any());
    }


    @Test
    void testUpdateFailIfEmpty() {
        Optional<Movie> emptyResult = Optional.empty();
        when(movieRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        assertThrows(RuntimeException.class, () -> movieServiceImpl.update(1L, new MovieDTO("Dr", "Year")));
        verify(movieRepository).findById(Mockito.<Long>any());
    }




    @Test
    void testFindAllFailIfArrayIsEmpty() {
        when(movieRepository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(movieServiceImpl.findAll().isEmpty());
        verify(movieRepository).findAll();
    }


    @Test
    void testFindAllSuccess() {
        Movie movie = new Movie();
        movie.setId(1L);
        movie.setTitle("Dr");
        movie.setYear("Year");

        ArrayList<Movie> movieList = new ArrayList<>();
        movieList.add(movie);
        when(movieRepository.findAll()).thenReturn(movieList);
        List<MovieDTO> actualFindAllResult = movieServiceImpl.findAll();
        assertEquals(1, actualFindAllResult.size());
        MovieDTO getResult = actualFindAllResult.get(0);
        assertEquals(1L, getResult.getId().longValue());
        assertEquals("Year", getResult.getYear());
        assertEquals("Dr", getResult.getTitle());
        verify(movieRepository).findAll();
    }

    @Test
    void testFindAllFail() {
        when(movieRepository.findAll()).thenThrow(new RuntimeException("foo"));
        assertThrows(RuntimeException.class, () -> movieServiceImpl.findAll());
        verify(movieRepository).findAll();
    }
}

