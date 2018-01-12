package com.example.kidusmt.movieapp.data.movie.remote;

import com.example.kidusmt.movieapp.data.movie.MovieUpComing;
import com.example.kidusmt.movieapp.data.movie.MovieInTheater;
import com.example.kidusmt.movieapp.data.movie.MoviePopular;
import com.example.kidusmt.movieapp.data.movie.MovieTopRated;
import com.example.kidusmt.movieapp.data.movie.MoviesResponse;
import com.example.kidusmt.movieapp.util.App;

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
    public static String BASE_URL = "http://api.themoviedb.org/3/";
    private static Retrofit retrofit = null;

    public MovieRemote(){
        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
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

    /**
     * Get API Service
     *
     * @return API Service
     */

    @Override
    public Observable<List<MoviePopular>> getPopularMovie(String clientId) {
//        return movieService.getPopularMovies(App.API_KEY);
//                .enqueue(new Callback<MoviesResponse>() {
//                    @Override
//                    public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
//                        final List<MoviePopular> populars = new ArrayList<>();
//                        populars = response.body().getResults();
//                        return
//                    }
//
//                    @Override
//                    public void onFailure(Call<MoviesResponse> call, Throwable t) {
//                        //TODO have to handle the failure
//                    }
//                });
        return null;
    }

    @Override
    public Observable<List<MovieTopRated>> getTopRatedMovie(String clientId) {
        //TODO the same implementation as the above one
        return null;
    }

    @Override
    public Observable<List<MovieInTheater>> getInTheaterMovie(String clientId) {
        return null;
    }

    @Override
    public Observable<List<MovieUpComing>> getUpComingMovie(String clientId) {
        return null;
    }
}
