package com.example.Trail.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class LoggedMovie {

    @Column(name = "tmdb_id")
    private int tmdbId;

    @Column(name = "title")
    private String title;

    @Column(name = "poster_path")
    private String posterPath;

    @Column(name = "rating")
    private Integer rating;
}
