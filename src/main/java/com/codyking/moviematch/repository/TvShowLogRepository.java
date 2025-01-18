package com.codyking.moviematch.repository;

import com.codyking.moviematch.model.TvShowLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// JPA Repository for TvShowLog entities, providing standard CRUD operations.
@Repository
public interface TvShowLogRepository extends JpaRepository<TvShowLog, Long> {

}
