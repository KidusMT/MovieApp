package com.example.kidusmt.movieapp.data.movie.remote;


import java.util.List;

import io.reactivex.Observable;


/**
 * Created by KidusMT on 1/4/2018.
 */

public interface MovieRemoteContract {


    Observable<List<MovieDto>> getMovies(String clientId, String category);

    /**
     * Retrieve back an observable list of posts.
     *
     * @param clientId clientId used for TheMovieDB API
     */
//    Observable<List<MoviePopular>> getPopularMovie(String clientId);
//
//    Observable<List<MovieTopRated>> getTopRatedMovie(String clientId);
//
//    Observable<List<MovieInTheater>> getInTheaterMovie(String clientId);
//
//    Observable<List<MovieUpComing>> getUpComingMovie(String clientId);

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
