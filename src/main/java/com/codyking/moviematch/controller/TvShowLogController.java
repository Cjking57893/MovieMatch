package com.codyking.moviematch.controller;

import com.codyking.moviematch.model.TvShowLog;
import com.codyking.moviematch.service.TvShowLogService;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tvshowlogs")
public class TvShowLogController {

  private final TvShowLogService tvShowLogService;

  public TvShowLogController(TvShowLogService tvShowLogService) {
    this.tvShowLogService = tvShowLogService;
  }

  @GetMapping
  public ResponseEntity<List<TvShowLog>> getAllTvShowLogs() {
    return ResponseEntity.ok(tvShowLogService.getAllTvShowLogs());
  }

  @GetMapping("/{id}")
  public ResponseEntity<TvShowLog> getTvShowLog(@PathVariable Long id) {
    TvShowLog tvShowLog = tvShowLogService.getTvShowLogById(id);

    if (tvShowLog != null) {
      return ResponseEntity.ok(tvShowLog);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping
  public ResponseEntity<TvShowLog> createTvShowLog(@RequestBody TvShowLog tvShowLog) {
    try {
      TvShowLog savedTvShowLog = tvShowLogService.createTvShowLog(tvShowLog);

      URI location = URI.create("/api/tvshowlogs/" + savedTvShowLog.getId());
      return ResponseEntity.created(location).body(savedTvShowLog);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<TvShowLog> updateTvShowLog(
      @PathVariable Long id, @RequestBody TvShowLog tvShowLog) {
    try {
      TvShowLog updatedTvShowLog = tvShowLogService.updateTvShowLog(id, tvShowLog);

      return ResponseEntity.ok(updatedTvShowLog);
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteMovieLog(@PathVariable Long id) {
    try {
      tvShowLogService.deleteTvShowLog(id);
      return ResponseEntity.noContent().build();
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }
}
