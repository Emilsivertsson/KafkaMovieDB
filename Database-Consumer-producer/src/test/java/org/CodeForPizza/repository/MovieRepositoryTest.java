package org.CodeForPizza.repository;

import org.CodeForPizza.dto.MovieDTO;
import org.CodeForPizza.entity.Movie;
import org.junit.jupiter.api.*;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    private Movie testMovie;

    private static Movie movie;


    @BeforeEach
    void setUp() {
        System.out.println("setUp");

        movie = new Movie();
        movie.setTitle("A");
        movie.setYear("B");

        testMovie = movieRepository.save(movie);
    }

    @AfterEach
    void tearDown() {
        System.out.println("tearDown");
        if (movieRepository.findById(testMovie.getId()).isPresent()) {
            movieRepository.deleteById(testMovie.getId());
        }

    }

    @Test
    @Order(1)
    void createUser() {
        // kolla att testuser sparats i databasen
        assertEquals(movie.getTitle(), testMovie.getTitle());

    }

    @Test
    @Order(2)
    void UpdateUser(){
        // hämta testuser från databasen
        Movie movieFromDB = movieRepository.findById(testMovie.getId()).get();

        // ändra förnamn och efternamn
        movieFromDB.setTitle("C");
        movieFromDB.setYear("D");
        movieRepository.save(movieFromDB);

        // hämta testuser från databasen igen
        Movie updatedMovieFromDB = movieRepository.findById(testMovie.getId()).get();
        assertEquals("C", updatedMovieFromDB.getTitle());

    }

    @Test
    @Order(3)
    void deleteUser(){
        // kontrollera att user med id 1 finns
        assertNotNull(movieRepository.findById(testMovie.getId()).get());

        // ta bort user med id 1
        movieRepository.deleteById(testMovie.getId());

        // kontrollera att user med id 1 inte finns
        assertFalse(movieRepository.findById(testMovie.getId()).isPresent());
    }
}