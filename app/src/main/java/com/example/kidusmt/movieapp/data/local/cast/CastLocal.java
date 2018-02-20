package com.example.kidusmt.movieapp.data.local.cast;

import com.example.kidusmt.movieapp.data.local.movie.Movie;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by KidusMT on 1/15/2018.
 */

public class CastLocal implements CastLocalContract {

    @Override
    public Observable<Boolean> putAll(List<Movie> movies) {
        return null;
    }

    @Override
    public Observable<List<Movie>> getByCategory(String category) {
        return null;
    }

    @Override
    public Observable<Boolean> deleteAll(String category) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}
