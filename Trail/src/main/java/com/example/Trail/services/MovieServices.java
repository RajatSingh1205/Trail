package com.example.Trail.services;

import com.example.Trail.entity.Movie;
import com.example.Trail.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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




}
