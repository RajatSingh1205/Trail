package com.example.Trail.DTO.tmdb;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CrewResult {
    private String name;

    @JsonProperty("profile_path")
    private String profilePath;

    private String department;
}
