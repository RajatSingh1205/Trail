package com.example.Trail.mapper;

import com.example.Trail.DTO.response.MovieCardResponse;
import com.example.Trail.DTO.tmdb.TmdbMovieDto;

public class MovieMapper {

    public static MovieCardResponse toCard(TmdbMovieDto movie) {

        return new MovieCardResponse(
                (long) movie.getId(),
                movie.getTitle(),
                movie.getPosterPath(),
                movie.getVoteAverage(),
                movie.getReleaseDate()
        );
    }
}
