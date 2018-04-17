package com.example.kidusmt.movieapp.data.remote.movie;


import io.reactivex.Observable;


/**
 * Created by KidusMT on 1/4/2018.
 */

public interface MovieRemoteContract {

    /**
     * for getting the movies by their categories from the API
     * @param clientId for the movie API
     * @param category for different categories of the movie
     * @return Observable value of the movie
     */
    Observable<MoviesResponse> getMovies(String clientId, String category);
}
