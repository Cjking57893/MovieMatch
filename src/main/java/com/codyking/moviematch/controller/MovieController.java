package com.codyking.moviematch.controller;

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

    @GetMapping("/{pageNum}")
    public ResponseEntity<?> getMovieSearch(@PathVariable int pageNum, @RequestBody MovieSearchRequestDto movieSearchRequestDto) {
        List<Movie> result = tmdbService.searchMovie(movieSearchRequestDto.getQuery(),
                                                    pageNum,
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

}

