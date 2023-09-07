package org.CodeForPizza.repository;

import org.CodeForPizza.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This interface extends JpaRepository, which provides the basic CRUD operations.
 * It is used to save the movie information to the database.
 */
@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer>{


}
