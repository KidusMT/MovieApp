package com.example.kidusmt.movieapp.data.movie;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by KidusMT on 1/4/2018.
 */

public interface RepoMovieContract {

    Observable<List<MoviePopular>> getPopularMovies();

    Observable<List<MovieTopRated>> getTopRatedMovies();

    Observable<List<MovieUpComing>> getUpComingMovies();

    Observable<List<MovieInTheater>> getInTheaterMovies();

    Observable<Boolean> updatePopularMovies(MoviePopular moviePopular);

    Observable<Boolean> updateTopRatedMovie(MovieTopRated movieTopRated);

    Observable<Boolean> updateUpComingMovie(MovieUpComing movieUpComing);

    Observable<Boolean> updateInTheater(MovieInTheater movieInTheater);

    int popularSize();

    int topRatedSize();

    int upComingSize();

    int inTheaterSize();
}