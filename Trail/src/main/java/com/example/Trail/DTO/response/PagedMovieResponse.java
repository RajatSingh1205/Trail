package com.example.Trail.DTO.response;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class PagedMovieResponse {

    private int page;
    private int totalPages;
    private List<MovieCardResponse> movies;
}
