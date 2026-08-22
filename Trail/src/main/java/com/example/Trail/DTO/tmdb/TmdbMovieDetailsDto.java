package com.example.Trail.DTO.tmdb;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

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

    @JsonProperty("backdrop_path")
    private String backdropPath;

    private boolean adult;

    private List<Genre> genres;

    private Videos videos;
    private Credits credits;

    private Integer runtime;
    @JsonProperty("original_language")
    private String originalLanguage;
}
