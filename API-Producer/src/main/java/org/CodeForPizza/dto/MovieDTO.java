package org.CodeForPizza.dto;

import jakarta.persistence.Column;
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
}
