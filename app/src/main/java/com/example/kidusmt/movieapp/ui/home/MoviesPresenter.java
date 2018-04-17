package com.example.kidusmt.movieapp.ui.home;

import com.example.kidusmt.movieapp.data.model.Movie;
import com.example.kidusmt.movieapp.data.repo.movie.RepoMovieContract;
import com.example.kidusmt.movieapp.util.ActivityState;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class MoviesPresenter implements HomeContract.Presenter {

    private HomeContract.View view;
    private RepoMovieContract repository;
    private ActivityState state;

    public MoviesPresenter(RepoMovieContract repository) {
        this.repository = repository;
        this.state = ActivityState.getInstance();
    }

    @Override
    public void start() {
        if (state.loading()) return;
        loadMovies();
    }

    @Override
    public void attachView(HomeContract.View view) {
        this.view = view;
        view.attachPresenter(this);
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public HomeContract.View getView() {
        return view;
    }

    public void onMovieClicked(Movie movie) {
        view.openMovieDetail(movie);
    }

    @Override
    public void loadMovies() {
        view.showLoading();
        repository.getMovies(view.getCategory())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<List<Movie>>() {
                    @Override
                    public void onNext(List<Movie> movies) {
                        state.setStateCompleted();

                        if (view == null) return;
                        view.hideLoading();
                        view.showMovies(movies);

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
                        view.hideLoading();
                    }

                    @Override
                    public void onComplete() {
                        view.hideLoading();
                    }
                });
    }
}