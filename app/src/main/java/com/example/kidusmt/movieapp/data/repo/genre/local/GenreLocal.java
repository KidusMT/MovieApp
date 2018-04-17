package com.example.kidusmt.movieapp.data.repo.genre.local;

import com.example.kidusmt.movieapp.data.model.Genre;

import java.util.List;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.reactivex.Observable;

/**
 *
 */
public class GenreLocal implements GenreLocalContract {

    private Box<Genre> box;

    public GenreLocal(BoxStore store) {
        box = store.boxFor(Genre.class);
    }

    @Override
    public Observable<Boolean> putAll(List<Genre> genres) {
        box.put(genres);
        return Observable.just(true);
    }

    @Override
    public Observable<List<Genre>> getGenre() {
        List<Genre> genres = box.getAll();
        return Observable.just(genres);
    }

    @Override
    public Observable<Boolean> removeAll() {
        box.removeAll();
        return Observable.just(true);
    }


    @Override
    public int size() {
        return box.getAll().size();
    }
}
