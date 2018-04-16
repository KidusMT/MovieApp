package com.example.kidusmt.movieapp.ui.home;

import com.example.kidusmt.movieapp.data.RepoMovieContract;
import com.example.kidusmt.movieapp.data.local.movie.Movie;
import com.example.kidusmt.movieapp.data.local.movie.MovieLocalContract;
import com.example.kidusmt.movieapp.data.remote.movie.MovieRemoteContract;
import com.example.kidusmt.movieapp.util.ActivityState;
import com.example.kidusmt.movieapp.util.Constants;

import org.json.JSONException;
import org.json.JSONObject;

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

    private MovieLocalContract local;
    private MovieRemoteContract remote;
    private RepoMovieContract repository;
    private ActivityState state;

    public MoviesPresenter(MovieLocalContract local, MovieRemoteContract remote) {
        this.local = local;
        this.remote = remote;
        this.state = ActivityState.getInstance();
    }

    @Override
    public void start() {
        final String category = view.getCategory();
        local.getByCategory(category)
            .subscribe(
                movies -> {
                    if (movies.isEmpty()) {
                        remote.getMovies(Constants.API_KEY, category)
                                .map(dtos -> local.getByCategory(category));
                    }
                }
            );
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

    public void onMovieClicked(int position) {

    }

    @Override
    public void onSwippedDownToRefresh() {

    }

    @Override
    public void loadMovies() {
        local.getByCategory(view.getCategory())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<List<Movie>>() {
                    @Override
                    public void onNext(List<Movie> movies) {
                        view.showMovies(movies);
                    }

                    @Override
                    public void onError(Throwable e) {
                        state.setStateError(e);

                        if (view == null) return;
//                        view.showLoading();

                        if (e instanceof SocketTimeoutException) {
//                            view.onTimeout();
                        } else if (e instanceof IOException) {
//                            view.onNetworkError();
                        } else if (e instanceof HttpException) {
                            int code = ((HttpException) e).response().code();
                            if (code >= 400 && code < 404) {
                                //TODO should implement this in a better way for all status code
//                                view.onUnknownError("Unauthorized! Login again.");
                            } else {
                                ResponseBody responseBody = ((HttpException) e).response().errorBody();
                                try {//should display the correct error message form the http protocol
                                    if (responseBody != null) {
                                        JSONObject jObjError = new JSONObject(responseBody.toString());
//                                        view.onUnknownError(jObjError.toString());
                                    }
                                } catch (JSONException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        } else {
//                            view.onUnknownError(e.getMessage());
                        }
                        e.printStackTrace();
//                        view.hideLoading();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}