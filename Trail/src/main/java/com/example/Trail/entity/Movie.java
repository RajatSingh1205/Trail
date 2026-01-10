package com.example.Trail.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "movies")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;

    @Column(nullable = false)
    private String movieName;

    @Column(length = 20000)
    private String movieDescription;


    private LocalDate releaseDate;

    private Integer runtimeMinutes;

    private String language;

    private String country;

    private String posterUrl;

    private String trailerUrl;



}
