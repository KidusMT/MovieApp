package com.example.kidusmt.movieapp.data.remote.movie;

import com.example.kidusmt.movieapp.util.Constants;
import com.example.kidusmt.movieapp.util.Utils;

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
 * Created by KidusMT on 12/28/2017.
 */

public class MovieRemote implements MovieRemoteContract{

    public static MovieService movieService;
    private static Retrofit retrofit = null;

    public MovieRemote(){
        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(createClient())
                    .build();
        }
        movieService = retrofit.create(MovieService.class);
    }

    private OkHttpClient createClient() {
        return new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
    }

    @Override
    public Observable<List<MovieDto>> getMovies(String clientId, String category) {
        final List<MovieDto> movies = new ArrayList<>();
        movieService.getMovies(Constants.API_KEY, category)
                .enqueue(new Callback<MoviesResponse>() {
                    @Override
                    public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                        if(response.isSuccessful()){
                            final List<MovieDto> downloaded = response.body().getResults();
                            movies.addAll(downloaded);
                        }else{

                        }
                    }

                    @Override
                    public void onFailure(Call<MoviesResponse> call, Throwable t) {

                    }
                });
        return Observable.just(movies);
    }
}
