package com.example.Trail.mapper;

import com.example.Trail.DTO.response.MovieCardResponse;
import com.example.Trail.DTO.tmdb.TmdbMovieDto;
import com.example.Trail.entity.LoggedMovie;
import com.example.Trail.entity.LoggedMovies;

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

    public LoggedMovie mapToLoggedMovie(TmdbMovieDto dto) {
        LoggedMovie movies = new LoggedMovie();
        movies.setTmdbId(dto.getId());
        movies.setTitle(dto.getTitle());
        movies.setPosterPath(dto.getPosterPath());
        return movies;
    }
}
