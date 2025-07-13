package com.codyking.moviematch.controller;

import com.codyking.moviematch.dto.TvShowSearchRequestDto;
import com.codyking.moviematch.service.TmdbService;
import info.movito.themoviedbapi.model.core.TvSeries;
import info.movito.themoviedbapi.model.tv.series.TvSeriesDb;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tvshows/")
public class TvShowController {

    private final TmdbService tmdbService;

    public TvShowController(TmdbService tmdbService) { this.tmdbService = tmdbService; }

    @GetMapping("/search/{pageNum}")
    public ResponseEntity<?> getTvShowSearch(@PathVariable int pageNum, @RequestBody TvShowSearchRequestDto tvShowSearchRequestDto) {
        List<TvSeries> result = tmdbService.searchTvShow(tvShowSearchRequestDto.getQuery(),
                                                        pageNum,
                                                        tvShowSearchRequestDto.getFirstAirDateYear(),
                                                        tvShowSearchRequestDto.getLanguage(),
                                                        tvShowSearchRequestDto.getYear());

        if (result != null) {
            return ResponseEntity.ok(result);
        }
        else {
            return ResponseEntity.status(503).body("Failed to fetch TMDB data. Please contact administrator and try again later.");
        }
    }

    @GetMapping("/find/{tvShowId}")
    public ResponseEntity<?> getTvShow(@PathVariable int tvShowId, @RequestParam(required = false, defaultValue = "en-US") String language) {
        TvSeriesDb result = tmdbService.getTvShow(tvShowId, language);
        if (result != null) {
            return ResponseEntity.ok(result);
        }
        else {
            return ResponseEntity.status(503).body("Failed to fetch TMDB data. Please contact administrator and try again.");
        }
    }

}
