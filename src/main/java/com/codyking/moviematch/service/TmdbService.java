package com.codyking.moviematch.service;

import info.movito.themoviedbapi.*;
import info.movito.themoviedbapi.model.core.Movie;
import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.model.core.TvSeries;
import info.movito.themoviedbapi.model.core.TvSeriesResultsPage;
import info.movito.themoviedbapi.model.movies.MovieDb;
import info.movito.themoviedbapi.tools.TmdbException;
import info.movito.themoviedbapi.tools.builders.discover.DiscoverMovieParamBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class TmdbService {

    private final TmdbApi tmdbApi;

    public TmdbService(@Value("${TMDB_API_KEY}") String apiKey) {
        this.tmdbApi = new TmdbApi(apiKey);
    }

    public List<Movie> searchMovie(String movieName, int pageNum, String primaryReleaseYear, String region, String year) {
        TmdbSearch tmdbSearch = tmdbApi.getSearch();
        try {
            return tmdbSearch.searchMovie(movieName, false, "en-US", null, pageNum, null, null).getResults();
        } catch (TmdbException e) {
            System.out.println(e);
            return null;
        }
    }

    public List<TvSeries> searchTvShow(String tvShowName, int pageNum) {
        TmdbSearch tmdbSearch = tmdbApi.getSearch();
        try {
            return tmdbSearch.searchTv(tvShowName, null, false, "en-US", pageNum, null).getResults();
        } catch (TmdbException e) {
            System.out.println("Error occurred while searching for tv show.");
            return null;
        }
    }

}
