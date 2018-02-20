package com.example.kidusmt.movieapp.data.remote.cast;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by KidusMT on 2/6/2018.
 */

public interface CastRemoteContract {

    Observable<List<Cast>> getCast(int movie_id, String clientId);
}
