package com.example.kidusmt.movieapp.data.repo.genre;

import com.example.kidusmt.movieapp.data.model.Genre;
import com.example.kidusmt.movieapp.data.repo.genre.local.GenreLocalContract;
import com.example.kidusmt.movieapp.data.repo.genre.remote.GenreRemoteContract;
import com.example.kidusmt.movieapp.util.Constants;

import java.lang.reflect.Constructor;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by KidusMT on 4/17/2018.
 */

public class RepoGenre implements RepoGenreContract {

    GenreRemoteContract remote;
    GenreLocalContract local;

    public RepoGenre(GenreLocalContract local, GenreRemoteContract remote) {
        this.remote = remote;
        this.local = local;
    }

    @Override
    public Observable<List<Genre>> getGenres() {
        if (local.size() == 0) {
            return remote.getGenre(Constants.API_KEY)
                    .flatMap(genreResponse -> local.putAll(genreResponse.getGenres()))
                    .flatMap(aBoolean -> local.getGenre());
        } else if (local.size()> remote.size()) {//if there is an updated genre
            local.removeAll();
            return remote.getGenre(Constants.API_KEY)
                    .flatMap(genreResponse -> local.putAll(genreResponse.getGenres()))
                    .flatMap(aBoolean -> local.getGenre());
        } else {
            return local.getGenre();
        }
    }

    @Override
    public int size() {
        return local.size();
    }
}
