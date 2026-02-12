package com.example.Trail.services;


import com.example.Trail.DTO.request.CreateLogRequest;
import com.example.Trail.DTO.tmdb.TmdbMovieDto;
import com.example.Trail.entity.LoggedMovie;
import com.example.Trail.entity.LoggedMovies;
import com.example.Trail.repository.LoggedMoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Trail.DTO.response.LoggedMoviesResponse;

import java.util.List;

@Service
public class LoggedMoviesService {

    @Autowired
    private LoggedMoviesRepository loggedMoviesRepository;

    @Autowired
    private TmdbServices tmdbServices;

     public LoggedMoviesResponse createLog(CreateLogRequest logRequest) {
         List<LoggedMovie> movies = logRequest.getMovies()
                 .stream()
                 .map(movieRequest -> {

                     TmdbMovieDto tmdbMovie =
                             tmdbServices.searchMovies(movieRequest.getTitle())
                                     .getResults()
                                     .get(0);

                     LoggedMovie movie = new LoggedMovie();
                     movie.setTmdbId(tmdbMovie.getId());
                     movie.setTitle(tmdbMovie.getTitle());
                     movie.setPosterPath(tmdbMovie.getPosterPath());
                     movie.setRating(movieRequest.getRating());

                     return movie;
                 }).toList();

         LoggedMovies log = new LoggedMovies();
         log.setLogId(System.currentTimeMillis());
         log.setDateOfLog(logRequest.getDateOfLog());
         log.setMovies(movies);

         LoggedMovies savedLog = loggedMoviesRepository.save(log);

         LoggedMoviesResponse response = new LoggedMoviesResponse();
         response.setLogId(savedLog.getLogId());
         response.setDateOfLog(savedLog.getDateOfLog());
         response.setMovies(savedLog.getMovies());

         return response;
     }
}
