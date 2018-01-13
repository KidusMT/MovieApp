package com.example.kidusmt.movieapp.ui.home;

import com.example.kidusmt.movieapp.data.movie.local.MovieLocalContract;
import com.example.kidusmt.movieapp.data.movie.remote.MovieRemoteContract;
import com.example.kidusmt.movieapp.util.App;
import com.example.kidusmt.movieapp.util.Constants;

public class MoviesPresenter implements HomeContract.Presenter {

    private HomeContract.View view;

    private MovieLocalContract local;
    private MovieRemoteContract remote;

    public MoviesPresenter(MovieLocalContract local, MovieRemoteContract remote) {
        this.local = local;
        this.remote = remote;
    }

    @Override
    public void start() {
        final String category = view.getCategory();
        local.getByCategory(category).subscribe(
                movies -> {
                    if (movies.isEmpty()) {
                        remote
                                .getMovies(Constants.API_KEY, category)
                                .map(dtos -> {

                                });
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
}