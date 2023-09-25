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

    /**
     * Method under test: {@link MovieServiceImpl#findById(Long)}
     */
    @Test
    void testFindById() {
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

    /**
     * Method under test: {@link MovieServiceImpl#findById(Long)}
     */
    @Test
    void testFindById2() {
        Optional<Movie> emptyResult = Optional.empty();
        when(movieRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        assertThrows(RuntimeException.class, () -> movieServiceImpl.findById(1L));
        verify(movieRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link MovieServiceImpl#findById(Long)}
     */
    @Test
    void testFindById3() {
        when(movieRepository.findById(Mockito.<Long>any())).thenThrow(new RuntimeException("Movie not found"));
        assertThrows(RuntimeException.class, () -> movieServiceImpl.findById(1L));
        verify(movieRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link MovieServiceImpl#save(MovieDTO)}
     */
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

    /**
     * Method under test: {@link MovieServiceImpl#save(MovieDTO)}
     */
    @Test
    void testSave2() {
        Movie movie = new Movie();
        movie.setId(1L);
        movie.setTitle("Dr");
        movie.setYear("Year");
        when(movieRepository.save(Mockito.<Movie>any())).thenReturn(movie);
        MovieDTO actualSaveResult = movieServiceImpl.save(null);
        assertEquals(1L, actualSaveResult.getId().longValue());
        assertEquals("Year", actualSaveResult.getYear());
        assertEquals("Dr", actualSaveResult.getTitle());
        verify(movieRepository).save(Mockito.<Movie>any());
    }

    /**
     * Method under test: {@link MovieServiceImpl#save(MovieDTO)}
     */
    @Test
    void testSave3() {
        MovieDTO movieDTO = mock(MovieDTO.class);
        when(movieDTO.getId()).thenThrow(new RuntimeException("foo"));
        assertNull(movieServiceImpl.save(movieDTO));
        verify(movieDTO).getId();
    }

    /**
     * Method under test: {@link MovieServiceImpl#deleteById(Long)}
     */
    @Test
    void testDeleteById() {
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

    /**
     * Method under test: {@link MovieServiceImpl#deleteById(Long)}
     */
    @Test
    void testDeleteById2() {
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

    /**
     * Method under test: {@link MovieServiceImpl#deleteById(Long)}
     */
    @Test
    void testDeleteById3() {
        Optional<Movie> emptyResult = Optional.empty();
        when(movieRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        assertThrows(RuntimeException.class, () -> movieServiceImpl.deleteById(1L));
        verify(movieRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link MovieServiceImpl#update(Long, MovieDTO)}
     */
    @Test
    void testUpdate() {
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

    /**
     * Method under test: {@link MovieServiceImpl#update(Long, MovieDTO)}
     */
    @Test
    void testUpdate2() {
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

    /**
     * Method under test: {@link MovieServiceImpl#update(Long, MovieDTO)}
     */
    @Test
    void testUpdate3() {
        Optional<Movie> emptyResult = Optional.empty();
        when(movieRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        assertThrows(RuntimeException.class, () -> movieServiceImpl.update(1L, new MovieDTO("Dr", "Year")));
        verify(movieRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link MovieServiceImpl#update(Long, MovieDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdate4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.CodeForPizza.dto.MovieDTO.getTitle()" because "movieDTO" is null
        //       at org.CodeForPizza.service.movieImpl.MovieServiceImpl.update(MovieServiceImpl.java:61)
        //   See https://diff.blue/R013 to resolve this issue.

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
        movieServiceImpl.update(1L, null);
    }

    /**
     * Method under test: {@link MovieServiceImpl#findAll()}
     */
    @Test
    void testFindAll() {
        when(movieRepository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(movieServiceImpl.findAll().isEmpty());
        verify(movieRepository).findAll();
    }

    /**
     * Method under test: {@link MovieServiceImpl#findAll()}
     */
    @Test
    void testFindAll2() {
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

    /**
     * Method under test: {@link MovieServiceImpl#findAll()}
     */
    @Test
    void testFindAll3() {
        Movie movie = new Movie();
        movie.setId(1L);
        movie.setTitle("Dr");
        movie.setYear("Year");

        Movie movie2 = new Movie();
        movie2.setId(2L);
        movie2.setTitle("Mr");
        movie2.setYear("42");

        ArrayList<Movie> movieList = new ArrayList<>();
        movieList.add(movie2);
        movieList.add(movie);
        when(movieRepository.findAll()).thenReturn(movieList);
        List<MovieDTO> actualFindAllResult = movieServiceImpl.findAll();
        assertEquals(2, actualFindAllResult.size());
        MovieDTO getResult = actualFindAllResult.get(0);
        assertEquals("42", getResult.getYear());
        MovieDTO getResult2 = actualFindAllResult.get(1);
        assertEquals("Year", getResult2.getYear());
        assertEquals("Dr", getResult2.getTitle());
        assertEquals(1L, getResult2.getId().longValue());
        assertEquals("Mr", getResult.getTitle());
        assertEquals(2L, getResult.getId().longValue());
        verify(movieRepository).findAll();
    }

    /**
     * Method under test: {@link MovieServiceImpl#findAll()}
     */
    @Test
    void testFindAll4() {
        when(movieRepository.findAll()).thenThrow(new RuntimeException("foo"));
        assertThrows(RuntimeException.class, () -> movieServiceImpl.findAll());
        verify(movieRepository).findAll();
    }
}

