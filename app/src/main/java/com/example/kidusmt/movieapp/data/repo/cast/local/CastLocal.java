package com.example.kidusmt.movieapp.data.repo.cast.local;


import com.example.kidusmt.movieapp.data.model.Cast;

import java.util.List;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.reactivex.Observable;

/**
 * Created by KidusMT on 1/15/2018.
 */

public class CastLocal implements CastLocalContract {
    private Box<Cast> box;

    public CastLocal(BoxStore store){
        box = store.boxFor(Cast.class);
    }

    @Override
    public Observable<Boolean> putAll(List<Cast> casts) {
        box.put(casts);
        return Observable.just(true);
    }

    @Override
    public Observable<List<Cast>> getByMovieId(int movie_id) {
        List<Cast> casts = box.find(Cast_.movie_id, movie_id);
        return Observable.just(casts);
    }

    @Override
    public int size() {
        return box.getAll().size();
    }
}
