package org.CodeForPizza.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * This class is used to create a movie object.
 * It is used to save the movie information to the database.
 */
@Getter
@Setter
@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "year")
    private String year;

    public Movie(String title, String year) {
        this.title = title;
        this.year = year;
    }

    public Movie() {

    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", year='" + year + '\'' +
                '}';
    }


}
