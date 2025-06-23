package com.codyking.moviematch.controller;

import com.codyking.moviematch.model.MovieLog;
import com.codyking.moviematch.service.MovieLogService;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movielogs")
public class MovieLogController {

  private final MovieLogService movieLogService;

  public MovieLogController(MovieLogService movieLogService) {
    this.movieLogService = movieLogService;
  }

  @GetMapping
  public ResponseEntity<List<MovieLog>> getAllMovieLogs() {
    return ResponseEntity.ok(movieLogService.getAllMovieLogs());
  }

  @GetMapping("/{id}")
  public ResponseEntity<MovieLog> getMovieLog(@PathVariable Long id) {
    MovieLog movieLog = movieLogService.getMovieLogById(id);

    if (movieLog != null) {
      return ResponseEntity.ok(movieLog);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping
  public ResponseEntity<MovieLog> createMovieLog(@RequestBody MovieLog movieLog) {
    try {
      MovieLog savedMovieLog = movieLogService.createMovieLog(movieLog);

      URI location = URI.create("/api/movielogs/" + savedMovieLog.getId());
      return ResponseEntity.created(location).body(savedMovieLog);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<MovieLog> updateMovieLog(
      @PathVariable Long id, @RequestBody MovieLog movieLog) {
    try {
      MovieLog updatedMovieLog = movieLogService.updateMovieLog(id, movieLog);

      return ResponseEntity.ok(updatedMovieLog);
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteMovieLog(@PathVariable Long id) {
    try {
      movieLogService.deleteMovieLog(id);
      return ResponseEntity.noContent().build();
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }
}
