package com.example.kidusmt.movieapp.data.remote.cast;

import com.example.kidusmt.movieapp.util.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
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
        return new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
    }

    /**
     * fetches the casts for the particular movie using the movie_id param and clientId param
     * @param movie_id
     * @param clientId
     * @return Observable casts list for the observer
     */
    @Override
    public Observable<List<Cast>> getCast(int movie_id, String clientId) {
        final List<Cast> casts = new ArrayList<>();
        service.getCastList(movie_id, clientId)
                .enqueue(new Callback<CastResponse>() {
                    @Override
                    public void onResponse(Call<CastResponse> call, Response<CastResponse> response) {
                        final List<Cast> download = response.body().getCast();
                        casts.addAll(download);
                    }

                    @Override
                    public void onFailure(Call<CastResponse> call, Throwable t) {
                        //TODO has to display a failure notice for the user
                    }
                });

        return Observable.just(casts);
    }

    //TODO has to  implement the parse method for parsing the json result and make it easy for use
}
