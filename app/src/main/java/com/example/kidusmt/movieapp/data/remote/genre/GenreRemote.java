package com.example.kidusmt.movieapp.data.remote.genre;

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
 * Created by KidusMT on 12/28/2017.
 */

public class GenreRemote implements GenreRemoteContract {

    public static GenreService genreService;
    private static Retrofit retrofit = null;

    public GenreRemote(){
        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(createClient())
                    .build();
        }
        genreService = retrofit.create(GenreService.class);
    }

    private OkHttpClient createClient() {
        return new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
    }

    @Override
    public Observable<List<Genre>> getGenre(String clientId) {
        final List<Genre> genres = new ArrayList<>();
        genreService.getGenreList(Constants.API_KEY)
                .enqueue(new Callback<GenreResponse>() {
                    @Override
                    public void onResponse(Call<GenreResponse> call, Response<GenreResponse> response) {
                        final List<Genre> download = response.body().getGenres();
                        genres.addAll(download);
                    }

                    @Override
                    public void onFailure(Call<GenreResponse> call, Throwable t) {
                        //TODO has to display error
                    }
                });
        return Observable.just(genres);
    }
}
