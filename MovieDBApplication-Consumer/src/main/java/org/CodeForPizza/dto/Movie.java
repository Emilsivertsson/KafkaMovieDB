package org.CodeForPizza.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    private Long id;
    private String title;
    private String year;

    public Movie(String title, String year) {
    }
}
