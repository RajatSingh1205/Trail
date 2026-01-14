package com.example.Trail.controller;

import com.example.Trail.DTO.TmdbSearchResponse;
import com.example.Trail.entity.Movie;
import com.example.Trail.services.MovieServices;
import com.example.Trail.services.TmdbServices;
import com.fasterxml.jackson.annotation.OptBoolean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieServices movieServices;

    @Autowired
    private TmdbServices tmdbService;

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam String query) {
        try {
            TmdbSearchResponse response = tmdbService.searchMovies(query);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addMovie")
    public ResponseEntity<?> addMovie(@RequestBody Movie movie) {
        try {
            Movie saveMovie = movieServices.addMovies(movie);
            return new ResponseEntity<>(saveMovie, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
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
    @GetMapping("/getMovieById/{movieId}")
    public ResponseEntity<?> getMoviesById(@PathVariable Long movieId) {
        try {
            Optional<Movie> movie = movieServices.getMovieById(movieId);
            return new ResponseEntity<>(movie, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getMovieByName/{movieName}")
    public ResponseEntity<?> getMovieByName(@PathVariable String movieName) {
        try {
            Optional<Movie> movie = movieServices.getMovieByName(movieName);
            return new ResponseEntity<>(movie, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
