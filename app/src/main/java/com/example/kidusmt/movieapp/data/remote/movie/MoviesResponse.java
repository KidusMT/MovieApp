package com.example.kidusmt.movieapp.data.remote.movie;

import com.example.kidusmt.movieapp.data.remote.movie.MovieDto;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by KidusMT on 12/28/2017.
 */

public class MoviesResponse {

    @SerializedName("page")
    private int page;

    @SerializedName("results")
    private List<MovieDto> results;

    @SerializedName("total_results")
    private int totalResults;

    @SerializedName("total_pages")
    private int totalPages;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<MovieDto> getResults() {
        return results;
    }

    public void setResults(List<MovieDto> results) {
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
