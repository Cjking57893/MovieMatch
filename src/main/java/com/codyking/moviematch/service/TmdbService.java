package com.codyking.moviematch.service;

import info.movito.themoviedbapi.*;
import info.movito.themoviedbapi.model.core.Genre;
import info.movito.themoviedbapi.model.core.Movie;
import info.movito.themoviedbapi.model.core.TvSeries;
import info.movito.themoviedbapi.model.movies.MovieDb;
import info.movito.themoviedbapi.model.tv.series.TvSeriesDb;
import info.movito.themoviedbapi.tools.TmdbException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TmdbService {

    private final TmdbApi tmdbApi;

    public TmdbService(@Value("${TMDB_API_KEY}") String apiKey) {
        this.tmdbApi = new TmdbApi(apiKey);
    }

    public List<Movie> searchMovie(String movieName, int pageNum, String language, String primaryReleaseYear, String region, String year) {
        TmdbSearch tmdbSearch = tmdbApi.getSearch();
        try {
            return tmdbSearch.searchMovie(movieName, false, language, primaryReleaseYear, pageNum, region, year).getResults();
        } catch (TmdbException e) {
            System.out.println(e);
            return null;
        }
    }

    public MovieDb getMovie(int movieId, String language) {
        TmdbMovies tmdbMovies = tmdbApi.getMovies();
        try {
            return tmdbMovies.getDetails(movieId, language);
        } catch (TmdbException e) {
            System.out.println(e);
            return null;
        }
    }

    public List<Genre> getMovieGenres(int movieId, String language) {
        TmdbMovies tmdbMovies = tmdbApi.getMovies();
        try {
            return tmdbMovies.getDetails(movieId, language).getGenres();
        } catch (TmdbException e) {
            System.out.println(e);
            return null;
        }
    }

    public List<TvSeries> searchTvShow(String tvShowName, int pageNum, int firstAirDateYear, String language, int year) {
        TmdbSearch tmdbSearch = tmdbApi.getSearch();
        try {
            return tmdbSearch.searchTv(tvShowName, firstAirDateYear, false, language, pageNum, null).getResults();
        } catch (TmdbException e) {
            System.out.println(e);
            return null;
        }
    }

    public TvSeriesDb getTvShow(int tvShowId, String language) {
        TmdbTvSeries tmdbTvSeries = tmdbApi.getTvSeries();
        try {
            return tmdbTvSeries.getDetails(tvShowId, language);
        } catch (TmdbException e) {
            System.out.println(e);
            return null;
        }
    }

    public List<Genre> getTvShowGenres(int tvShowId, String language) {
        TmdbTvSeries tmdbTvSeries = tmdbApi.getTvSeries();
        try {
            return tmdbTvSeries.getDetails(tvShowId, language).getGenres();
        } catch (TmdbException e) {
            System.out.println(e);
            return null;
        }
    }

}
