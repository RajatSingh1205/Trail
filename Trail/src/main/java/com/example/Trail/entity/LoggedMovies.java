package com.example.Trail.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoggedMovies {

    @Id
    private Long logId;

    private String dateOfLog;

    @ElementCollection
    @CollectionTable(
            name = "logged_movies_movies",
            joinColumns = @JoinColumn(name = "logged_movies_log_id")
    )
    private List<LoggedMovie> movies;
}
