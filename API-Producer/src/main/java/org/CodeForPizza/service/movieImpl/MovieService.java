package org.CodeForPizza.service.movieImpl;

import org.CodeForPizza.dto.MovieDTO;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public interface MovieService {


    MovieDTO findById(Long id);

    MovieDTO save(MovieDTO movieDTO);

    void deleteById(Long id);

    MovieDTO update(Long id, MovieDTO movieDTO);

    List<MovieDTO> findAll();
}
