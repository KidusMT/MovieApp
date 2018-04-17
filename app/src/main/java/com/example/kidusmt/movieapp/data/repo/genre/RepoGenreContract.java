package com.example.kidusmt.movieapp.data.repo.genre;

import com.example.kidusmt.movieapp.data.model.Genre;

import java.util.List;

import io.reactivex.Observable;


/**
 * Created by KidusMT on 4/17/2018.
 */

public interface RepoGenreContract {
    Observable<List<Genre>> getGenres();

    int size();
}
