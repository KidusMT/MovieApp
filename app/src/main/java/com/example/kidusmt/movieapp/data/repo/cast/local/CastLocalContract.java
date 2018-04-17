package com.example.kidusmt.movieapp.data.repo.cast.local;


import com.example.kidusmt.movieapp.data.model.Cast;
import com.example.kidusmt.movieapp.data.model.Movie;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by KidusMT on 1/4/2018.
 */

public interface CastLocalContract {

    /**
     * Save all the movies on local storage
     *
     * @param casts movies to save
     * @return Observable boolean, true if it was saved, false otherwise
     */
    Observable<Boolean> putAll(List<Cast> casts);

    /**
     * Retrieve a list of movies by the given category
     * @return Observable list of movies retrieved
     */
    Observable<List<Cast>> getByMovieId(int movie_id);


    /**
     * Retrieves the amount of posts saved on the device.
     */
    int size();
}
