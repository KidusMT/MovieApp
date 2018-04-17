package com.example.kidusmt.movieapp.data.remote.movie;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.objectbox.annotation.Convert;
import io.objectbox.converter.PropertyConverter;

/**
 * Created by KidusMT on 12/28/2017.
 */

public class MoviesResponse {

    @SerializedName("page")
    private int page;

    @SerializedName("results")
    @Convert(converter = MovieListConverter.class, dbType = String.class)
    private List<Movie> results;

    @SerializedName("total_results")
    private int totalResults;

    @SerializedName("total_pages")
    private int totalPages;

    public static class MovieListConverter implements PropertyConverter<List<Movie>, String> {
        Gson gson;
        @Override
        public List<Movie> convertToEntityProperty(String databaseValue) {

            if (databaseValue == null) { return null; }

            JsonParser parser = new JsonParser();
            JsonArray surveyQuestionsJsonArray = (JsonArray) parser.parse(databaseValue);
            List<Movie> movies = new ArrayList<>();
            for(JsonElement element : surveyQuestionsJsonArray) {
                movies.add(gson.fromJson(element, Movie.class));
            }

            return movies;
        }

        @Override
        public String convertToDatabaseValue(List<Movie> entityProperty) {
            if(entityProperty == null) return null;

            return gson.toJson(entityProperty);
        }
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
