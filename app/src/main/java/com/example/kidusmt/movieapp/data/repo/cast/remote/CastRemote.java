package com.example.kidusmt.movieapp.data.repo.cast.remote;

import com.example.kidusmt.movieapp.data.model.CastResponse;
import com.example.kidusmt.movieapp.util.Constants;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by KidusMT on 2/6/2018.
 */

public class CastRemote implements CastRemoteContract {

    public static CastService service;

    /**
     * Constructor for the class for initializing retrofit and calling the service using the retrofit
     */
    public CastRemote(){
        final Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .client(createClient())
                .build();
        service = retrofit.create(CastService.class);
    }

    /**
     *  created a customer client for handling the time out to be 30 sec
     * @return OkHttpClient
     */
    private OkHttpClient createClient(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addInterceptor(logging)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
    }

    /**
     * fetches the casts for the particular movie using the movie_id param and clientId param
     * @param movie_id (required)
     * @param clientId (required)
     * @return Observable casts list for the observer
     */
    @Override
    public Observable<CastResponse> getCast(int movie_id, String clientId) {
        return service.getCastList(movie_id, clientId);
    }
}
