package com.example.kidusmt.movieapp.data;

import com.example.kidusmt.movieapp.data.local.movie.MovieLocalContract;
import com.example.kidusmt.movieapp.data.remote.movie.Movie;
import com.example.kidusmt.movieapp.data.remote.movie.MovieRemoteContract;
import com.example.kidusmt.movieapp.util.Constants;

import java.util.List;

import io.reactivex.Observable;

public class RepoMovie implements RepoMovieContract {

    MovieRemoteContract remote;
    MovieLocalContract local;

    public RepoMovie(MovieLocalContract local, MovieRemoteContract remote) {
        this.local = local;
        this.remote = remote;
    }

    @Override
    public Observable<List<Movie>> getMovies(String category) {
        if(local.size() == 0){
            return remote.getMovies(Constants.API_KEY,category)
                    .flatMap(moviesResponse -> local.putAll(moviesResponse.getResults()))
                    .flatMap(aBoolean -> local.getByCategory(category));
        }else {
            return local.getByCategory(category);
        }
    }

    @Override
    public int size() {
        return local.size();
    }

}
