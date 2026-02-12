package com.example.Trail.repository;

import com.example.Trail.entity.LoggedMovies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoggedMoviesRepository extends JpaRepository<LoggedMovies, Long> {
}
