package com.example.kidusmt.movieapp.ui.home;

import com.example.kidusmt.movieapp.base.mvp.BasePresenter;
import com.example.kidusmt.movieapp.base.mvp.BaseView;
import com.example.kidusmt.movieapp.data.local.movie.Movie;

import java.util.List;

/**
 * Created by KidusMT on 1/4/2018.
 */

public interface HomeContract {

    interface View extends BaseView<Presenter> {

        //show movie card on the recyclerView
        void showMovies(List<Movie> movieList);

        void showProgress();
        void hideProgress();

        //what is going on here? why String? as a return object
        String getCategory();

        //open the detail activity for the specific movie card clicked
        void openMovieDetail(int position);

        //refreshes the cards on the recyclerView
         void refresh();
    }

    interface Presenter extends BasePresenter<View>{

        //perform the action of loading the movies for the recyclerView
        //perform the action of a click on the card
        void onMovieClicked(int position);

        //performs the refresh for the page reloading
        void onSwippedDownToRefresh();

        //retrieve movie from the movie db
        void loadMovies();
    }
}
