package com.example.kidusmt.movieapp.data.local.movie;

import com.example.kidusmt.movieapp.data.remote.movie.Movie;
import com.example.kidusmt.movieapp.data.remote.movie.Movie_;

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
        box.put(movies);
        return Observable.just(true);
    }

    @Override
    public Observable<List<Movie>> getByCategory(String category) {
        List<Movie> movies = box.find(Movie_.category, category);
        return Observable.just(movies);
    }


    @Override
    public int size() {
        return box.getAll().size();
    }
}
