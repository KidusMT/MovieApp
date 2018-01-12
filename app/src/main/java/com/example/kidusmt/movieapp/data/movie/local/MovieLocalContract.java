package com.example.kidusmt.movieapp.data.movie.local;

import com.example.kidusmt.movieapp.data.movie.Movie;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by KidusMT on 1/4/2018.
 */

public interface MovieLocalContract {

    /**
     * Save all the movies on local storage
     *
     * @param movies movies to save
     * @return Observable boolean, true if it was saved, false otherwise
     */
    Observable<Boolean> putAll(List<Movie> movies);

    /**
     * Retrieve a list of movies by the given category
     *
     * @param category The category to search for
     * @return Observable list of movies retrieved
     */
    Observable<List<Movie>> getByCategory(String category);
}
