package com.example.Trail.DTO.tmdb;

import lombok.Data;
import java.util.List;

@Data
public class Videos {
    private List<VideoResult> results;
}
