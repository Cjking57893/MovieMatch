package com.codyking.moviematch.dto;

public class MovieSearchRequestDto {

    private String query;
    private String primaryReleaseYear;
    private String region;
    private String year;

    public MovieSearchRequestDto(String query, String primaryReleaseYear, String region, String year) {
        this.query = query;
        this.primaryReleaseYear = primaryReleaseYear;
        this.region = region;
        this.year = year;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getPrimaryReleaseYear() {
        return primaryReleaseYear;
    }

    public void setPrimaryReleaseYear(String primaryReleaseYear) {
        this.primaryReleaseYear = primaryReleaseYear;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
