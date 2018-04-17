package com.example.kidusmt.movieapp.data.remote.genre;


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
    Observable<List<Genre>> getGenre(String clientId);
}
