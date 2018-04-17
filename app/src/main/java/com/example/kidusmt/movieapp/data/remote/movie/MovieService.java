package com.example.kidusmt.movieapp.data.remote.movie;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by KidusMT on 12/28/2017.
 */

public interface MovieService {

    @GET("movie/{category}")
    Observable<MoviesResponse> getMovies(@Path("category") String category, @Query("api_key") String apiKey);

    @GET("movie/{id}")//the selected movie id is going to pass here to get the specific detail
    Observable<MoviesResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);
}

