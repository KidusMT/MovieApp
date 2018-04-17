package com.example.kidusmt.movieapp.data.remote.movie;

import com.example.kidusmt.movieapp.util.App;
import com.example.kidusmt.movieapp.util.Constants;
import com.example.kidusmt.movieapp.util.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.objectbox.android.AndroidScheduler;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by KidusMT on 12/28/2017.
 */

public class MovieRemote implements MovieRemoteContract {

    public static MovieService movieService;
    private static Retrofit retrofit = null;

    public MovieRemote() {
        if (retrofit == null) {
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
