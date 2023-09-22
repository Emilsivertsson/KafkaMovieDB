package org.CodeForPizza.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MovieDTO {

    private Long id;
    private String title;
    private String year;


    public MovieDTO(String title, String year) {
        this.title = title;
        this.year = year;
    }
}

