package com.example.Trail.DTO.response;

import com.example.Trail.DTO.tmdb.CastResult;
import com.example.Trail.DTO.tmdb.CrewResult;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MovieDetailsResponse {
    private Long id;
    private String title;
    private String overview;
    private String releaseDate;
    private Double rating;
    private String posterPath;
    private String backdropPath;
    private boolean adult;
    private List<String> genres;
    private Integer runtime;
    private String originalLanguage;



    private List<TrailerDto> trailers;
    private List<CastResult> cast;
    private List<CrewResult> crew;
}
