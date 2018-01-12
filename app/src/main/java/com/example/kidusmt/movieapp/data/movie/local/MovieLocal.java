package com.example.kidusmt.movieapp.data.movie.local;

import com.example.kidusmt.movieapp.data.movie.Movie;
import com.example.kidusmt.movieapp.data.movie.MovieInTheater;
import com.example.kidusmt.movieapp.data.movie.MoviePopular;
import com.example.kidusmt.movieapp.data.movie.MovieTopRated;
import com.example.kidusmt.movieapp.data.movie.MovieUpComing;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.query.Query;
import io.reactivex.Observable;

/**
 *
 */
public class MovieLocal implements MovieLocalContract {

    private Box<Movie> box;

    public MovieLocal(BoxStore store) {
        box = store.boxFor(Movie.class);
    }

    @Override
    public Observable<Boolean> putAll(List<Movie> movies) {
        // TODO: Remove movies by category
        box.put(movies);
        return Observable.just(true);
    }

    @Override
    public Observable<List<Movie>> getByCategory(String category) {
//        List<Movie> movies = box.find(Movie_.category, category);
        List<Movie> movies = new ArrayList<>();
        return Observable.just(movies);
    }

    @Override
    public Observable<Boolean> deleteAll(String category) {
//        List<Movie> movies = box.find(Movie_.category, category);
//        List<Movie> movies = new ArrayList<>();
        Query<Movie> query = box.query()
                .equal(Movie_.category, category)
                .build();
        query.remove();
        return Observable.just(true);
    }
}
