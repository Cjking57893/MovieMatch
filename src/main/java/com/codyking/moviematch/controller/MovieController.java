package com.codyking.moviematch.controller;

import info.movito.themoviedbapi.model.core.Genre;
import info.movito.themoviedbapi.model.movies.MovieDb;
import com.codyking.moviematch.dto.MovieSearchRequestDto;
import com.codyking.moviematch.service.TmdbService;
import info.movito.themoviedbapi.model.core.Movie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies/")
public class MovieController {

    private final TmdbService tmdbService;

    public MovieController(TmdbService tmdbService) { this.tmdbService = tmdbService; }

    @GetMapping("/search/{pageNum}")
    public ResponseEntity<?> getMovieSearch(@PathVariable int pageNum, @RequestBody MovieSearchRequestDto movieSearchRequestDto) {
        List<Movie> result = tmdbService.searchMovie(movieSearchRequestDto.getQuery(),
                                                    pageNum,
                                                    movieSearchRequestDto.getLanguage(),
                                                    movieSearchRequestDto.getPrimaryReleaseYear(),
                                                    movieSearchRequestDto.getRegion(),
                                                    movieSearchRequestDto.getYear());
        if (result != null) {
            return ResponseEntity.ok(result);
        }
        else {
            return ResponseEntity.status(503).body("Failed to fetch TMDB data. Please contact administrator and try again later.");
        }
    }

    @GetMapping("/find/{movieId}")
    public ResponseEntity<?> getMovie(@PathVariable int movieId, @RequestParam(required = false, defaultValue = "en-US") String language) {
        MovieDb result = tmdbService.getMovie(movieId, language);
        if (result != null) {
            return ResponseEntity.ok(result);
        }
        else {
            return ResponseEntity.status(503).body("Failed to fetch TMDB data. Please contact administrator and try again later.");
        }
    }

    @GetMapping("/find/genres/{movieId}")
    public ResponseEntity<?> getMovieGenres(@PathVariable int movieId, @RequestParam(required = false, defaultValue = "en-US") String language) {
        List<Genre> result = tmdbService.getMovieGenres(movieId, language);
        if (result != null) {
            return ResponseEntity.ok(result);
        }
        else {
            return ResponseEntity.status(503).body("Failed to fetch TMDB data. Please contact administrator and try again.");
        }
    }

}

