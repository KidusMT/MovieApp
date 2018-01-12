package com.example.kidusmt.movieapp.data.movie.remote;


import com.example.kidusmt.movieapp.data.movie.Movie;
import com.example.kidusmt.movieapp.data.movie.MovieUpComing;
import com.example.kidusmt.movieapp.data.movie.MovieInTheater;
import com.example.kidusmt.movieapp.data.movie.MoviePopular;
import com.example.kidusmt.movieapp.data.movie.MovieTopRated;
import com.google.gson.JsonObject;


import java.util.List;

import io.reactivex.Observable;

/**
 * Created by KidusMT on 1/4/2018.
 */

public interface MovieRemoteContract {

    /**
     * Retrieve back an observable list of posts.
     *
     * @param clientId clientId used for TheMovieDB API
     */
    Observable<List<MoviePopular>> getPopularMovie(String clientId);

    Observable<List<MovieTopRated>> getTopRatedMovie(String clientId);

    Observable<List<MovieInTheater>> getInTheaterMovie(String clientId);

    Observable<List<MovieUpComing>> getUpComingMovie(String clientId);

//
//    MoviePopular parsePopular(JsonObject object) throws Exception;
//
//    MovieUpComing parseUpComing(JsonObject object) throws Exception;
//
//    MovieInTheater parseInTheater(JsonObject object) throws Exception;
//
//    MovieTopRated parseTopRated(JsonObject object) throws Exception;
//
}
