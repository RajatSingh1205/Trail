package com.example.Trail.services;

import com.example.Trail.entity.Movie;
import com.example.Trail.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServices {

    @Autowired
    private MovieRepository movieRepository;

    public Movie addMovies(Movie movie) {
        return movieRepository.save(movie);
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Optional<Movie> getMovieById(Long movieId) {
        return movieRepository.findById(movieId);
    }

    public Optional<Movie> getMovieByName(String movieName) {
        return movieRepository.findMovieByMovieName(movieName);
    }




}
