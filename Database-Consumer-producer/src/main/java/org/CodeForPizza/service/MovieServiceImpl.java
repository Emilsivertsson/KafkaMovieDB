package org.CodeForPizza.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.CodeForPizza.dto.MovieDTO;
import org.CodeForPizza.entity.Movie;
import org.CodeForPizza.mapper.MovieMapper;
import org.CodeForPizza.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class implements the movie service interface.
 * It uses the movie repository to save the movie to the database.
 */
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Service
public class MovieServiceImpl implements MovieService{

    @Autowired
    private MovieRepository movieRepository;

    @Transactional
    @Override
    public MovieDTO save(MovieDTO movieDTO) {
        try{
            log.info("Saving movie: " + movieDTO);
            Movie movie = MovieMapper.toMovie(movieDTO);
            log.info("mapped movie: " + movie);

            Movie savedMovie = movieRepository.save(movie);
            log.info("saved movie: " + savedMovie);
            return MovieMapper.toMovieDTO(savedMovie);

        } catch (Exception e) {
            log.info("Error saving movie: " + e);
            return null;
        }
    }
}
