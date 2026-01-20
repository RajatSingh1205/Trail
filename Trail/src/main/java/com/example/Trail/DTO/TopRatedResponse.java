package com.example.Trail.DTO;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class TopRatedResponse {

    private int page;
    private int totalPages;
    private List<MovieCardResponse> movies;
}
