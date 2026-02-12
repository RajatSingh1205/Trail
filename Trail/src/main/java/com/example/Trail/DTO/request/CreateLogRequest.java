package com.example.Trail.DTO.request;

import lombok.Data;

import java.util.List;

@Data
public class CreateLogRequest {
    private String dateOfLog;
    private List<MovieLogRequest> movies;
}
