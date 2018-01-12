package com.example.kidusmt.movieapp.data.movie.local;

import com.example.kidusmt.movieapp.data.movie.MovieInTheater;
import com.example.kidusmt.movieapp.data.movie.MoviePopular;
import com.example.kidusmt.movieapp.data.movie.MovieTopRated;
import com.example.kidusmt.movieapp.data.movie.MovieUpComing;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by KidusMT on 1/4/2018.
 */

public interface MovieLocalContract {

    /**
     * These methods are for retrieving different movie lists from local storage
     * @return
     */
    Observable<List<MoviePopular>> getPopularMovies();

    Observable<List<MovieTopRated>> getTopRatedMovies();

    Observable<List<MovieUpComing>> getUpComingMovie();

    Observable<List<MovieInTheater>> getInTheater();

    /**
     * These methods are for saving different MovieCategories types
     * @return
     */
    Observable<Boolean> savePopularMovie(List<MoviePopular> populars);

    Observable<Boolean> saveTopRateMovie(List<MovieTopRated> topRateds);

    Observable<Boolean> saveUpComingMovie(List<MovieUpComing> upComings);

    Observable<Boolean> saveInTheaterMovie(List<MovieInTheater> inTheaters);

    /**
     * These methods are for updating the UI according to the updates found
     * @return
     */
    Observable<Boolean> updatePopularMovie(MoviePopular moviePopular);

    Observable<Boolean> updateInTheaterMovie(MovieInTheater movieInTheater);

    Observable<Boolean> updateUpComingMovie(MovieUpComing movieUpComing);

    Observable<Boolean> updateTopRatedMovie(MovieTopRated movieTopRated);

    /**
     * Retrieves the amount of movie saved on the device.
     */
    int popularSize();

    int inTheaterSize();

    int topRatedSize();

    int upComingSize();
}
