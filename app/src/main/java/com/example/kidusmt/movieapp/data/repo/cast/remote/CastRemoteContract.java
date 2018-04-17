package com.example.kidusmt.movieapp.data.repo.cast.remote;

import com.example.kidusmt.movieapp.data.model.CastResponse;

import io.reactivex.Observable;

/**
 * Created by KidusMT on 2/6/2018.
 */

public interface CastRemoteContract {

    Observable<CastResponse> getCast(int movie_id, String clientId);
}
