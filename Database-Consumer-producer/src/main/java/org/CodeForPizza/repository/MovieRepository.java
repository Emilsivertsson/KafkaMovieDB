package org.CodeForPizza.repository;

import org.CodeForPizza.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    //this is how you can create custom queries
    //@Query("SELECT m FROM Movie m WHERE m.year = :year")
    //List<Movie> findByYear(@Param("year") String year);
}