package com.example.kidusmt.movieapp.ui.home;

import com.example.kidusmt.movieapp.data.movie.Movie;
import com.example.kidusmt.movieapp.data.movie.MovieInTheater;
import com.example.kidusmt.movieapp.data.movie.MoviePopular;
import com.example.kidusmt.movieapp.data.movie.MovieTopRated;
import com.example.kidusmt.movieapp.data.movie.MovieUpComing;
import com.example.kidusmt.movieapp.data.movie.RepoMovieContract;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by KidusMT on 1/4/2018.
 */

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View view;
    private RepoMovieContract repository;

    public HomePresenter(RepoMovieContract repository){
        this.repository = repository;
    }

    @Override
    public void loadMovies(String whichMovie) {
//        view.showProgressWheel();

        switch(whichMovie){
            case "popular":
                repository
                        .getPopularMovies()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new DisposableObserver<List<MoviePopular>>() {
                            @Override
                            public void onNext(List<MoviePopular> populars) {
                                if(view == null) return;
//                                view.hideProgressWheel();
                                view.showPopularMovies(populars);

//                                state.reset();
                            }

                            @Override
                            public void onError(Throwable e) {
//                                state.setError(e);
//                                if(view == null) return;
//                                view.showLoadingFailedError();
                                e.printStackTrace();
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            case "upComing":
                repository
                        .getUpComingMovies()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new DisposableObserver<List<MovieUpComing>>() {
                            @Override
                            public void onNext(List<MovieUpComing> upComings) {
                                if(view == null) return;
//                                view.hideProgressWheel();
                                view.showUpComingMovies(upComings);

//                                state.reset();
                            }

                            @Override
                            public void onError(Throwable e) {
//                                state.setError(e);
//                                if(view == null) return;
//                                view.showLoadingFailedError();
                                e.printStackTrace();
                            }

                            @Override
                            public void onComplete() {

                            }
                        });

            case "inTheater":
                repository
                        .getInTheaterMovies()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new DisposableObserver<List<MovieInTheater>>() {
                            @Override
                            public void onNext(List<MovieInTheater> inTheaters) {
                                if(view == null) return;
//                                view.hideProgressWheel();
                                view.showInTheaterMovies(inTheaters);

//                                state.reset();
                            }

                            @Override
                            public void onError(Throwable e) {
//                                state.setError(e);
//                                if(view == null) return;
//                                view.showLoadingFailedError();
                                e.printStackTrace();
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            case "topRated":
                repository
                        .getTopRatedMovies()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new DisposableObserver<List<MovieTopRated>>() {
                            @Override
                            public void onNext(List<MovieTopRated> topRated) {
                                if(view == null) return;
//                                view.hideProgressWheel();
                                view.showTopRatedMovies(topRated);

//                                state.reset();
                            }

                            @Override
                            public void onError(Throwable e) {
//                                state.setError(e);
//                                if(view == null) return;
//                                view.showLoadingFailedError();
                                e.printStackTrace();
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
        }

    }

    @Override
    public void onMovieClicked(int position) {
        view.openMovieDetail(position);
    }

    @Override
    public void onSwippedDownToRefresh() {
        //TODO have to implement the swipeDown to refresh layout first
    }

    @Override
    public void start() {
        //if(state.loading()) return;

//        loadMovies();
    }

    @Override
    public void attachView(HomeContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public HomeContract.View getView() {
        return view;
    }
}
