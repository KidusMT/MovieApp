package com.example.kidusmt.movieapp.data.repo.cast.remote;

import com.example.kidusmt.movieapp.data.model.CastResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by KidusMT on 1/15/2018.
 */

public interface CastService {

    @GET("movie/{id}/credits")
    Observable<CastResponse> getCastList(@Path("id") int id, @Query("api_key") String apiKey);
}
