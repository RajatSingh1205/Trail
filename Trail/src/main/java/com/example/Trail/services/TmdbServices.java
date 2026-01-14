package com.example.Trail.services;

import com.example.Trail.DTO.TmdbSearchResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

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
                        .queryParam("api_key", apiKey)   // ✅ FIXED
                        .queryParam("query", query)
                        .build())
                .retrieve()
                .bodyToMono(TmdbSearchResponse.class)
                .block();
    }
}
