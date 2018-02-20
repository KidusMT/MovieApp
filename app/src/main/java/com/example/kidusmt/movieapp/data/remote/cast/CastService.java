package com.example.kidusmt.movieapp.data.remote.cast;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by KidusMT on 1/15/2018.
 */

public interface CastService {

    @GET("movie/{id}/credits")
    Call<CastResponse> getCastList(@Path("id") int id, @Query("api_key") String apiKey);
}
