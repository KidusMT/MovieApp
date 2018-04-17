package com.example.kidusmt.movieapp.ui.splash;

import com.example.kidusmt.movieapp.data.model.Genre;
import com.example.kidusmt.movieapp.data.repo.genre.RepoGenre;
import com.example.kidusmt.movieapp.util.ActivityState;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

/**
 * Created by KidusMT on 4/17/2018.
 */

public class SplashPresenter implements SplashContract.Presenter{

    RepoGenre repository;
    ActivityState state;
    private SplashContract.View view;

    public static HashMap<Integer,String> genreIds = new HashMap();

    public SplashPresenter(RepoGenre repository){
        this.repository = repository;
        state = ActivityState.getInstance();
    }

    @Override
    public void start() {
        if (state.loading())return;
        loadGenres();
    }

    @Override
    public void attachView(SplashContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public SplashContract.View getView() {
        return null;
    }

    @Override
    public void loadGenres() {
        repository.getGenres()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<List<Genre>>() {
                    @Override
                    public void onNext(List<Genre> genres) {
                        state.setStateCompleted();
                        for(Genre genre: genres){
                            genreIds.put(genre.getId(),genre.getName());
                        }
                        state.reset();
                    }

                    @Override
                    public void onError(Throwable e) {
                        state.setStateError(e);
                        if (e instanceof SocketTimeoutException) {
                            view.onTimeout();
                        } else if (e instanceof IOException) {
                            view.onNetworkError();
                        } else if (e instanceof HttpException) {
                            int code = ((HttpException) e).response().code();
                            if (code >= 400 && code < 404) {
                                view.onUnknownError("Unauthorized access! Login again.");
                            } else {
                                ResponseBody responseBody = ((HttpException) e).response().errorBody();
                                if (responseBody != null) {
                                    view.onUnknownError(responseBody.toString());
                                }
                            }
                        } else {
                            view.onUnknownError(e.getMessage());
                        }
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    public static String getGenre(List<Integer> genres){
        String genre_string = "";
        for(int x: genres){
            genre_string += genreIds.get(x)+", ";
        }

        return genre_string;
    }
}
