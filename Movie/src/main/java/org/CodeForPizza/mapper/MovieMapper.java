package org.CodeForPizza.mapper;

import org.CodeForPizza.dto.MovieDTO;
import org.CodeForPizza.entity.Movie;



public class MovieMapper {

    public static MovieDTO toMovieDTO(Movie movie) {
        if (movie == null) return null;

        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(movie.getId());
        movieDTO.setTitle(movie.getTitle());
        movieDTO.setYear(movie.getYear());
        return movieDTO;
    }

    public static Movie toMovie(MovieDTO movieDTO) {
        if (movieDTO == null) return null;

        Movie movie = new Movie();
        movie.setId(movieDTO.getId());
        movie.setTitle(movieDTO.getTitle());
        movie.setYear(movieDTO.getYear());
        return movie;
    }
}
