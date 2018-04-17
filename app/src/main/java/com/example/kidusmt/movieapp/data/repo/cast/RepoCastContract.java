package com.example.kidusmt.movieapp.data.repo.cast;

import com.example.kidusmt.movieapp.data.model.Cast;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by KidusMT on 4/17/2018.
 */

public interface RepoCastContract {

    Observable<List<Cast>> getCasts(int movie_id);

    int size();
}
