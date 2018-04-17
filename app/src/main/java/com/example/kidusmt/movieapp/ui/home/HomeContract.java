package com.example.kidusmt.movieapp.ui.home;

import com.example.kidusmt.movieapp.base.mvp.BasePresenter;
import com.example.kidusmt.movieapp.base.mvp.BaseView;
import com.example.kidusmt.movieapp.data.model.Movie;

import java.util.List;

/**
 * Created by KidusMT on 1/4/2018.
 */

public interface HomeContract {

    interface View extends BaseView<Presenter> {

        void showMovies(List<Movie> movieList);

        String getCategory();

        void openMovieDetail(Movie movie);
    }

    interface Presenter extends BasePresenter<View> {

        void onMovieClicked(Movie movie);

        void loadMovies();
    }
}
