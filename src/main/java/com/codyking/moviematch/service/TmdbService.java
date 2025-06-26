package com.codyking.moviematch.service;

import info.movito.themoviedbapi.*;
import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.model.movies.MovieDb;
import info.movito.themoviedbapi.tools.TmdbException;
import info.movito.themoviedbapi.tools.builders.discover.DiscoverMovieParamBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class TmdbService {

    @Value("${TMDB_API_KEY}")
    private String apiKey;

    TmdbApi tmdbApi = new TmdbApi(apiKey);

    public MovieResultsPage searchMovie(String movieName, int pageNum) {
        TmdbSearch tmdbSearch = tmdbApi.getSearch();
        try {
            return tmdbSearch.searchMovie(movieName, false, "en-US", "", pageNum, "", "");
        } catch (TmdbException e) {
            System.out.println("Error occurred while searching for movie");
            return null;
        }
    }

}
