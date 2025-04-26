package com.codyking.moviematch.repository;

import com.codyking.moviematch.model.TvShowLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TvShowLogRepository extends JpaRepository<TvShowLog, Long> {

}
