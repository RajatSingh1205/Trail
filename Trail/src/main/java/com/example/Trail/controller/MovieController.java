package com.example.Trail.controller;

import com.example.Trail.entity.Movie;
import com.example.Trail.services.MovieServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieServices movieServices;

    @PostMapping("/addMovie")
    public ResponseEntity<?> addMovie(@RequestBody Movie movie) {
        try {
            Movie saveMovie = movieServices.addMovies(movie);
            return new ResponseEntity<>(saveMovie, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAllMovies")
    public ResponseEntity<?> getAllMovies() {
        try {
            List<Movie> movies = movieServices.getAllMovies();
            return new ResponseEntity<>(movies, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
