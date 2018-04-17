package com.example.kidusmt.movieapp.data.repo.movie;

import com.example.kidusmt.movieapp.data.model.Movie;
import com.example.kidusmt.movieapp.data.repo.movie.local.MovieLocalContract;
import com.example.kidusmt.movieapp.data.repo.movie.remote.MovieRemoteContract;
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
                    .flatMap(moviesResponse -> local.putAll(moviesResponse.getResults(),category))
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
