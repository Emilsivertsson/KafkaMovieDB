package org.CodeForPizza.service.movieImpl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.CodeForPizza.dto.MovieDTO;
import org.CodeForPizza.entity.Movie;
import org.CodeForPizza.mapper.MovieMapper;
import org.CodeForPizza.repository.MovieRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@AutoConfiguration
@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public MovieDTO findById(Long id) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Movie not found"));
        return MovieMapper.toMovieDTO(movie);
    }

    @Override
    public MovieDTO save(MovieDTO movieDTO) {
        log.info("Saving movie: " + movieDTO);
        Movie movie = MovieMapper.toMovie(movieDTO);
        log.info("mapped movie: " + movie);
        Movie savedMovie = movieRepository.save(movie);
        log.info("saved movie: " + savedMovie);
        return MovieMapper.toMovieDTO(savedMovie);
    }

    @Override
    public void deleteById(Long id) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Movie not found"));
        movieRepository.deleteById(movie.getId());
    }

    @Override
    public MovieDTO update(Long id,MovieDTO movieDTO) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Movie not found"));
        movie.setTitle(movieDTO.getTitle());
        movie.setYear(movieDTO.getYear());
        Movie updatedMovie = movieRepository.save(movie);
        return MovieMapper.toMovieDTO(updatedMovie);
    }

    @Override
    public List<MovieDTO> findAll() {
        List<Movie> movies = movieRepository.findAll();
        return movies.stream().map(MovieMapper::toMovieDTO).collect(Collectors.toList());
    }
}
