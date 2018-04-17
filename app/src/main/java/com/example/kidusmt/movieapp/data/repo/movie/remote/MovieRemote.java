package com.example.kidusmt.movieapp.data.repo.movie.remote;

import com.example.kidusmt.movieapp.data.model.MoviesResponse;
import com.example.kidusmt.movieapp.util.Constants;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by KidusMT on 12/28/2017.
 */

public class MovieRemote implements MovieRemoteContract {

    private static MovieService movieService;
    private static Retrofit retrofit = null;

    public MovieRemote() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(Constants.BASE_URL)
                    .client(createClient())
                    .build();
        }
        movieService = retrofit.create(MovieService.class);
    }

    private OkHttpClient createClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addInterceptor(logging)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
    }

    @Override
    public Observable<MoviesResponse> getMovies(String clientId, String category) {
        return movieService.getMovies(category, clientId);
    }
}
