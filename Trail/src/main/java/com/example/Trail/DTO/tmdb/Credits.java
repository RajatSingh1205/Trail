package com.example.Trail.DTO.tmdb;

import lombok.Data;
import java.util.List;

@Data
public class Credits {
    private List<CastResult> cast;
    private List<CrewResult> crew;
}
