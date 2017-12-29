package com.example.kidusmt.movieapp.data.rest;

import com.example.kidusmt.movieapp.data.CastResponse;
import com.example.kidusmt.movieapp.data.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by KidusMT on 12/28/2017.
 */

public interface ApiService {

    @GET("movie/popular")
    Call<MoviesResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/upcoming")
    Call<MoviesResponse> getUpcomingMovies(@Query("api_key") String apiKey);

    @GET("movie/top_rated")
    Call<MoviesResponse> getNowPlayingMovies(@Query("api_key") String apiKey);

    //TODO check this out later - not clear with what the end point is pointing at
    @GET("movie/{id}")//the selected movie id is going to pass here to get the specific detail
    Call<MoviesResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("movie/{id}/credits")//movie/{movie_id}/credits
    Call<CastResponse> getCastList(@Path("id") int id, @Query("api_key") String apiKey);

}

