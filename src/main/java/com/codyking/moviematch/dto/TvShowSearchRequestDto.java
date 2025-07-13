package com.codyking.moviematch.dto;

public class TvShowSearchRequestDto {

    private String query;
    private int firstAirDateYear = 0;
    private String language = "en-US";
    private int year = 0;

    public TvShowSearchRequestDto(String query, int firstAirDateYear, String language, int year) {
        this.query = query;
        this.firstAirDateYear = firstAirDateYear;
        this.language = language;
        this.year = year;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public int getFirstAirDateYear() {
        return firstAirDateYear;
    }

    public void setFirstAirDateYear(int firstAirDateYear) {
        this.firstAirDateYear = firstAirDateYear;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
