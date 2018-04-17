package com.example.kidusmt.movieapp.data.repo.genre.local;


import com.example.kidusmt.movieapp.data.model.Genre;
import com.example.kidusmt.movieapp.data.model.Movie;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by KidusMT on 1/4/2018.
 */

public interface GenreLocalContract {

    /**
     * Save all the movies on local storage
     *
     * @param movies movies to save
     * @return Observable boolean, true if it was saved, false otherwise
     */
    Observable<Boolean> putAll(List<Genre> movies);

    /**
     * Retrieve a list of movies by the given category
     * @return Observable list of movies retrieved
     */
    Observable<List<Genre>> getGenre();

    Observable<Boolean> removeAll();


    /**
     * Retrieves the amount of posts saved on the device.
     */
    int size();
}
