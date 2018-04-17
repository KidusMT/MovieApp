package com.example.kidusmt.movieapp.data.repo.genre.remote;

import com.example.kidusmt.movieapp.data.model.GenreResponse;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by KidusMT on 1/15/2018.
 */

public interface GenreService {

    @GET("genre/movie/list")
    Observable<GenreResponse> getGenreList(@Query("api_key") String apiKey);
}
