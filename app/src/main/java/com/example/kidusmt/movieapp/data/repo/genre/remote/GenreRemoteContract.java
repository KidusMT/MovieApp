package com.example.kidusmt.movieapp.data.repo.genre.remote;


import com.example.kidusmt.movieapp.data.model.Genre;
import com.example.kidusmt.movieapp.data.model.GenreResponse;

import java.util.List;

import io.reactivex.Observable;


/**
 * Created by KidusMT on 1/4/2018.
 */

public interface GenreRemoteContract {

    /**
     * for getting the movies by their categories from the API
     * @param clientId for the movie API
     * @return Observable value of the movie
     */
    Observable<GenreResponse> getGenre(String clientId);
}
