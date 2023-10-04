package org.CodeForPizza.dto;

import lombok.*;
/**
 * This class is a data transfer object for the movie entity.
 * It is used to transfer data between the modules via kafka and the API
 */

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
