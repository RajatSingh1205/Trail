package com.example.Trail.services;

import com.example.Trail.DTO.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class TmdbServices {

    private final WebClient webClient;

    @Value("${tmdb.api.key}")
    private String apiKey;

    public TmdbServices(WebClient.Builder webClientBuilder,
                        @Value("${tmdb.base.url}") String baseUrl) {

        this.webClient = webClientBuilder
                .baseUrl(baseUrl)
                .build();
    }



    public TmdbSearchResponse searchMovies(String query) {

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search/movie")
                        .queryParam("api_key", apiKey)
                        .queryParam("query", query)
                        .build())
                .retrieve()
                .bodyToMono(TmdbSearchResponse.class)
                .block();
    }


    public MovieDetailsResponse getMovieDetails(Long movieId) {

        TmdbMovieDetailsDto tmdbResponse =
                webClient.get()
                        .uri(uriBuilder -> uriBuilder
                                .path("/movie/{id}")
                                .queryParam("api_key", apiKey)
                                .queryParam("append_to_response", "videos,credits")
                                .build(movieId))
                        .retrieve()
                        .bodyToMono(TmdbMovieDetailsDto.class)
                        .block();


        List<TrailerDto> trailers =
                tmdbResponse.getVideos().getResults()
                        .stream()
                        .filter(v -> "Trailer".equals(v.getType())
                                && "YouTube".equals(v.getSite()))
                        .limit(2)
                        .map(v -> new TrailerDto(
                                v.getName(),
                                v.getKey()
                        ))
                        .toList();


        List<CastDto> cast =
                tmdbResponse.getCredits().getCast()
                        .stream()
                        .limit(10)
                        .map(c -> new CastDto(
                                c.getName(),
                                c.getCharacter()
                        ))
                        .toList();


        return MovieDetailsResponse.builder()
                .id(tmdbResponse.getId())
                .title(tmdbResponse.getTitle())
                .overview(tmdbResponse.getOverview())
                .releaseDate(tmdbResponse.getReleaseDate())
                .rating(tmdbResponse.getVoteAverage())
                .posterPath(tmdbResponse.getPosterPath())
                .trailers(trailers)
                .cast(cast)
                .build();
    }

    public TopRatedResponse getTopRatedMovies(int page) {
        TmdbSearchResponse tmdbResponse =  webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/movie/top_rated")
                        .queryParam("language", "en-US")
                        .queryParam("page", page)
                        .queryParam("api_key", apiKey)
                        .build())
                .retrieve()
                .bodyToMono(TmdbSearchResponse.class)
                .block();

        List<MovieCardResponse> movies =
                tmdbResponse.getResults()
                        .stream()
                        .map(movie -> new MovieCardResponse(
                                (long) movie.getId(),
                                movie.getTitle(),
                                movie.getPosterPath(),
                                movie.getVoteAverage(),
                                movie.getReleaseDate()
                        ))
                        .toList();

        return TopRatedResponse.builder()
                .page(tmdbResponse.getPage())
                .totalPages(tmdbResponse.getTotalPages())
                .movies(movies)
                .build();
    }
}
