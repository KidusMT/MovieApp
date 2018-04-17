package com.example.kidusmt.movieapp.data.repo.movie;

import com.example.kidusmt.movieapp.data.model.Movie;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by KidusMT on 1/4/2018.
 */

public interface RepoMovieContract {

    Observable<List<Movie>> getMovies(String category);

    int size();
}