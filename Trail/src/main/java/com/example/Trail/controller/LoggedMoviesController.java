package com.example.Trail.controller;

import com.example.Trail.DTO.request.CreateLogRequest;
import com.example.Trail.DTO.response.LoggedMoviesResponse;
import com.example.Trail.services.LoggedMoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log")
public class LoggedMoviesController {

    @Autowired
    private LoggedMoviesService loggedMoviesService;

    @PostMapping
    public ResponseEntity<?> createLog(@RequestBody CreateLogRequest request){
        try {
            LoggedMoviesResponse response = loggedMoviesService.createLog(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
