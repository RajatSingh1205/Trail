package com.example.Trail.DTO.response;

import com.example.Trail.entity.LoggedMovie;
import lombok.Data;

import java.util.List;

@Data
public class LoggedMoviesResponse {

    private Long logId;
    private String dateOfLog;
    private List<LoggedMovie> movies;
}
