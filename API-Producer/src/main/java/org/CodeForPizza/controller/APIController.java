package org.CodeForPizza.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.CodeForPizza.dto.MovieDTO;
import org.CodeForPizza.producer.KafkaProducer;
import org.CodeForPizza.service.movieImpl.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to create the REST API. it has one endpoint that receives a MovieDTO object and sends it to Kafka producer.
 * the other are used to preform CRUD operations on the database.
 */
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("/api/v1/movie")
public class APIController {

    @Autowired
    private KafkaProducer kafkaProducer;

    @Autowired
    private MovieService movieService;

    /**
     * http://localhost:8080/api/v1/movie/save
     *  This method is used to send a MovieDTO object to Kafka producer.
     */
    @PostMapping("/save")
    public ResponseEntity<String> publish(@RequestBody() MovieDTO movie) {
        try {
            log.info("Received message: " + movie);
            kafkaProducer.sendMessage(movie);
            return ResponseEntity.ok().body("Message sent to Topic");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error sending message to Topic");
        }
    }

    /**
     * this method is used in development to test the API without Kafka
     * http://localhost:8080/api/v1/movie/saveapi
     */

    @PostMapping("/saveapi")
    public ResponseEntity<MovieDTO> publishApi(@RequestBody() MovieDTO movieDTO) {
        try {
            log.info("Received message: " + movieDTO);
            MovieDTO savedMovie = movieService.save(movieDTO);
            return ResponseEntity.ok(savedMovie);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * http://localhost:8080/api/v1/movie/all
     * This method is used to get all movies from the database.
     */
    @GetMapping("/all")
    public ResponseEntity<List<MovieDTO>> getAllMovies() {
        try {
            log.info("get all movies");
            List<MovieDTO> movies = movieService.findAll();
            return ResponseEntity.ok(movies);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ArrayList<>());
        }
    }

    /**
     * http://localhost:8080/api/v1/movie/{id}
     * This method is used to get a movie by id from the database.
     */
    @GetMapping("/{id}")
        public ResponseEntity<MovieDTO> getMovieById(@PathVariable("id") long id) {
            try {
                MovieDTO movie = movieService.findById(id);
                return ResponseEntity.ok(movie);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(new MovieDTO());
            }
        }

    /**
     * http://localhost:8080/api/v1/movie/update/{id}
     * This method is used to update a movie by id in the database.
     */
    @PutMapping("/update/{id}")
        public ResponseEntity<MovieDTO> updateMovie(@PathVariable("id") long id,
                                                    @RequestBody MovieDTO movieDTO) {
            try {
                MovieDTO updatedMovie = movieService.update(id, movieDTO);
                return ResponseEntity.ok(updatedMovie);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(new MovieDTO());
            }
        }

    /**
     * http://localhost:8080/api/v1/movie/delete/{id}
     * This method is used to delete a movie by id from the database.
     */
    @DeleteMapping("/delete/{id}")
        public ResponseEntity<String> deleteMovie(@PathVariable("id") long id) {
            try{
                movieService.deleteById(id);
                return ResponseEntity.ok().body("Movie deleted successfully");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Error deleting movie");
            }
        }

}
