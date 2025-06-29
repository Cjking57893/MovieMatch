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
    public ResponseEntity<List<Movie>> getMovieSearch(@PathVariable int pageNum, @RequestBody MovieSearchRequestDto movieSearchRequestDto) {
        List<Movie> result = tmdbService.searchMovie(movieSearchRequestDto.getQuery(),
                                                    pageNum,
                                                    movieSearchRequestDto.getPrimaryReleaseYear(),
                                                    movieSearchRequestDto.getRegion(),
                                                    movieSearchRequestDto.getYear());
        return ResponseEntity.ok(result);
    }

}

