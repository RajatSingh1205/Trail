package com.example.Trail.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TmdbMovieDetailsDto {

    private Long id;
    private String title;
    private String overview;

    @JsonProperty("release_date")
    private String releaseDate;

    @JsonProperty("vote_average")
    private Double voteAverage;

    @JsonProperty("poster_path")
    private String posterPath;

    private Videos videos;
    private Credits credits;
}
