package com.codyking.moviematch.repository;

import com.codyking.moviematch.model.MovieLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieLogRepository extends JpaRepository<MovieLog, Long> {}
