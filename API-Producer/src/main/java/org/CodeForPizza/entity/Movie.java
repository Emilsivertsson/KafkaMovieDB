package org.CodeForPizza.entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Movie {

    private String title;
    private String year;

    public Movie(String title, String year) {
        this.title = title;
        this.year = year;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", year='" + year + '\'' +
                '}';
    }
}
