package com.example.kidusmt.movieapp.data.repo.cast;

import com.example.kidusmt.movieapp.data.model.Cast;
import com.example.kidusmt.movieapp.data.repo.cast.local.CastLocalContract;
import com.example.kidusmt.movieapp.data.repo.cast.remote.CastRemoteContract;
import com.example.kidusmt.movieapp.util.Constants;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by KidusMT on 4/17/2018.
 */

public class RepoCast implements RepoCastContract {

    CastLocalContract local;
    CastRemoteContract remote;

    public RepoCast(CastLocalContract local, CastRemoteContract remote){
        this.local = local;
        this.remote = remote;
    }

    @Override
    public Observable<List<Cast>> getCasts(int movie_id) {
        if (local.size() == 0){
            return remote.getCast(movie_id, Constants.API_KEY)
                    .flatMap(castResponse -> local.putAll(castResponse.getCast()))
                    .flatMap(aBoolean -> local.getByMovieId(movie_id));
        }else{
            return local.getByMovieId(movie_id);
        }
    }

    @Override
    public int size() {
        return local.size();
    }
}
