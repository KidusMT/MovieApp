package com.example.kidusmt.movieapp.ui.home;

import com.example.kidusmt.movieapp.base.mvp.BasePresenter;
import com.example.kidusmt.movieapp.base.mvp.BaseView;
import com.example.kidusmt.movieapp.data.movie.MovieInTheater;
import com.example.kidusmt.movieapp.data.movie.MoviePopular;
import com.example.kidusmt.movieapp.data.movie.MovieTopRated;
import com.example.kidusmt.movieapp.data.movie.MovieUpComing;

import java.util.List;

/**
 * Created by KidusMT on 1/4/2018.
 */

public interface HomeContract {

    interface View extends BaseView<Presenter> {

        //show movie card on the recyclerView
//        void showMovies(List<Movie> movieList);

        void showPopularMovies(List<MoviePopular> moviePopulars);
        void showUpComingMovies(List<MovieUpComing> movieUpComings);
        void showInTheaterMovies(List<MovieInTheater> movieInTheaters);
        void showTopRatedMovies(List<MovieTopRated> movieTopRated);

        //open the detail activity for the specific movie card clicked
        void openMovieDetail(int position);

        //refreshes the cards on the recyclerView
         void refresh();
    }

    interface Presenter extends BasePresenter<View>{

        //perform the action of loading the movies for the recyclerView
        void loadMovies(String whichMovie);

        //perform the action of a click on the card
        void onMovieClicked(int position);

        //performs the refresh for the page reloading
        void onSwippedDownToRefresh();

    }
}
