package org.CodeForPizza.service;

import org.CodeForPizza.dto.MovieDTO;
import org.springframework.stereotype.Service;

@Service
public interface MovieService {

    MovieDTO save(MovieDTO movieDTO);

    void deleteById(MovieDTO movieDTO);
}
