package com.example.Trail.services;

import com.example.Trail.DTO.response.MovieCardResponse;
import com.example.Trail.DTO.response.MovieDetailsResponse;
import com.example.Trail.DTO.response.PagedMovieResponse;
import com.example.Trail.DTO.response.TrailerDto;
import com.example.Trail.DTO.tmdb.CastResult;
import com.example.Trail.DTO.tmdb.TmdbMovieDetailsDto;
import com.example.Trail.DTO.tmdb.TmdbSearchResponse;
import com.example.Trail.mapper.MovieMapper;
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


        List<CastResult> cast =
                tmdbResponse.getCredits().getCast()
                        .stream()
                        .limit(10)
                        .map(c -> new CastResult(
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

    public PagedMovieResponse getTopRatedMovies(int page) {
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
                        .map(MovieMapper::toCard)
                        .toList();

        return PagedMovieResponse.builder()
                .page(tmdbResponse.getPage())
                .totalPages(tmdbResponse.getTotalPages())
                .movies(movies)
                .build();
    }

    public PagedMovieResponse getUpComingMovies(int page) {
        TmdbSearchResponse tmdbResponse =
                webClient.get()
                        .uri(uriBuilder -> uriBuilder
                                .path("/movie/upcoming")
                                .queryParam("api_key", apiKey)
                                .queryParam("language", "en-US")
                                .queryParam("page", page)
                                .build())
                        .retrieve()
                        .bodyToMono(TmdbSearchResponse.class)
                        .block();

        List<MovieCardResponse> movies =
                tmdbResponse.getResults()
                        .stream()
                        .map(MovieMapper::toCard)
                        .toList();

        return PagedMovieResponse.builder()
                .page(tmdbResponse.getPage())
                .totalPages(tmdbResponse.getTotalPages())
                .movies(movies)
                .build();
    }

    public PagedMovieResponse getPopularMovies(int page) {
        TmdbSearchResponse tmdbResponse =
                webClient.get()
                        .uri(uriBuilder -> uriBuilder
                                .path("/movie/popular")
                                .queryParam("api_key", apiKey)
                                .queryParam("language", "en-US")
                                .queryParam("page", page)
                                .build())
                        .retrieve()
                        .bodyToMono(TmdbSearchResponse.class)
                        .block();

        List<MovieCardResponse> movies =
                tmdbResponse.getResults()
                        .stream()
                        .map(MovieMapper::toCard)
                        .toList();

        return PagedMovieResponse.builder()
                .page(tmdbResponse.getPage())
                .totalPages(tmdbResponse.getTotalPages())
                .movies(movies)
                .build();
    }
}
