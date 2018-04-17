package com.example.kidusmt.movieapp.ui.detail;

import com.example.kidusmt.movieapp.data.model.Cast;
import com.example.kidusmt.movieapp.data.repo.cast.RepoCastContract;
import com.example.kidusmt.movieapp.util.ActivityState;
import com.example.kidusmt.movieapp.App;
import com.example.kidusmt.movieapp.util.Utils;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

/**
 * Created by KidusMT on 1/4/2018.
 */

public class MovieDetailPresenter implements MovieDetailContract.Presenter {

    private MovieDetailContract.View view;
    private ActivityState state;
    private RepoCastContract repository;
    private int movie_id;

    public MovieDetailPresenter(RepoCastContract repository, int movie_id) {
        state = ActivityState.getInstance();
        this.repository = repository;
        this.movie_id = movie_id;
    }

    @Override
    public void loadDetail() {
        if (movie_id != 0) {
            repository.getCasts(movie_id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DisposableObserver<List<Cast>>() {
                        @Override
                        public void onNext(List<Cast> casts) {
                            state.setStateCompleted();

                            if (view == null) return;
                            view.hideLoading();
                            view.showDetail(casts);

                            state.reset();
                        }

                        @Override
                        public void onError(Throwable e) {
                            state.setStateError(e);

                            if (view == null) return;
                            view.showLoading();

                            if (e instanceof SocketTimeoutException) {
                                view.onTimeout();
                            } else if (e instanceof IOException) {
                                view.onNetworkError();
                            } else if (e instanceof HttpException) {
                                int code = ((HttpException) e).response().code();
                                if (code >= 400 && code < 404) {
                                    view.onUnknownError("Unauthorized! Login again.");
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
                            view.hideLoading();
                        }

                        @Override
                        public void onComplete() {
                            view.hideLoading();
                        }
                    });
        }else{
            Utils.toast(App.getContext(), "no image has been selected");
        }
    }

    @Override
    public void onCardClicked(String imgPath) {
        view.openImage(imgPath);
    }

    @Override
    public void start() {
        if (state.loading()) return;
        loadDetail();
    }

    @Override
    public void attachView(MovieDetailContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public MovieDetailContract.View getView() {
        return this.view;
    }
}
