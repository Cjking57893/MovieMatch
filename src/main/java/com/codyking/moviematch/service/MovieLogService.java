package com.codyking.moviematch.service;

import com.codyking.moviematch.model.MovieLog;
import com.codyking.moviematch.repository.MovieLogRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class MovieLogService {

  private final MovieLogRepository movieLogRepository;

  public MovieLogService(MovieLogRepository movieLogRepository) {
    this.movieLogRepository = movieLogRepository;
  }

  public List<MovieLog> getAllMovieLogs() {
    return movieLogRepository.findAll();
  }

  public MovieLog getMovieLogById(Long id) {
    Optional<MovieLog> movieLog = movieLogRepository.findById(id);
    return movieLog.orElse(null);
  }

  public MovieLog createMovieLog(MovieLog movieLog) {
    return movieLogRepository.save(movieLog);
  }

  public MovieLog updateMovieLog(Long id, MovieLog movieLog) {
    if (!movieLogRepository.existsById(id)) {
      throw new RuntimeException("Movie Log not found");
    }

    movieLog.setId(id);

    return movieLogRepository.save(movieLog);
  }

  public void deleteMovieLog(Long id) {
    if (!movieLogRepository.existsById(id)) {
      throw new RuntimeException("Movie Log not found");
    }

    movieLogRepository.deleteById(id);
  }
}
