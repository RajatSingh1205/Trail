package com.example.Trail.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MovieCardResponse {

    private Long id;
    private String title;
    private String poster;
    private Double rating;
    private String releaseDate;
}
