package com.example.kidusmt.movieapp.data;

import com.example.kidusmt.movieapp.data.remote.movie.Movie;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by KidusMT on 1/4/2018.
 */

public interface RepoMovieContract {

    Observable<List<Movie>> getMovies(String category);

    Observable<Boolean> updateMovies(Movie movieDto);

    int size();
}