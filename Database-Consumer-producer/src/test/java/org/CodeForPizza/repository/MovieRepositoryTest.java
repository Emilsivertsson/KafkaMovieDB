package org.CodeForPizza.repository;

import org.CodeForPizza.entity.Movie;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9095", "port=9095"})
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
        movie.setYear("2021");

        testMovie = movieRepository.save(movie);
    }

    @AfterEach
    void tearDown() {
        System.out.println("tearDown");
        if (movieRepository.findById((int) testMovie.getId()).isPresent()) {
            movieRepository.deleteById((int) testMovie.getId());
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
        Movie movieFromDB = movieRepository.findById((int) testMovie.getId()).get();

        // ändra förnamn och efternamn
        movieFromDB.setTitle("C");
        movieFromDB.setYear("1999");
        movieRepository.save(movieFromDB);

        // hämta testuser från databasen igen
        Movie updatedUserFromDB = movieRepository.findById((int) testMovie.getId()).get();

        assertEquals(movieFromDB.getTitle(), updatedUserFromDB.getTitle());

    }

    @Test
    @Order(3)
    void deleteUser(){
        // kontrollera att user med id 1 finns
        assertNotNull(movieRepository.findById((int) testMovie.getId()).get());

        // ta bort user med id 1
        movieRepository.deleteById((int) testMovie.getId());

        // kontrollera att user med id 1 inte finns
        assertFalse(movieRepository.findById((int) testMovie.getId()).isPresent());
    }
}