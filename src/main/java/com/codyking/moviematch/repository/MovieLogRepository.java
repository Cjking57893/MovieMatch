package com.codyking.moviematch.repository;

import com.codyking.moviematch.model.MovieLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// JPA Repository for MovieLog entities, providing standard CRUD operations.
@Repository
public interface MovieLogRepository extends JpaRepository<MovieLog, Long> {

}
