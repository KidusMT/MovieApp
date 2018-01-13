package com.example.kidusmt.movieapp.data.movie;

import com.example.kidusmt.movieapp.data.movie.local.MovieLocalContract;
import com.example.kidusmt.movieapp.data.movie.remote.MovieDto;
import com.example.kidusmt.movieapp.data.movie.remote.MovieRemoteContract;
import com.example.kidusmt.movieapp.util.App;
import com.example.kidusmt.movieapp.util.Constants;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * Created by KidusMT on 1/4/2018.
 */

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
                    .flatMap(new Function<List<MovieDto>, ObservableSource<?>>() {
                        @Override
                        public ObservableSource<?> apply(List<MovieDto> movieDtos) throws Exception {
                            return local.getByCategory(category);
                        }
                    })
                    .flatMap(new Function<Object, ObservableSource<?>>() {
                        @Override
                        public ObservableSource<?> apply(Object o) throws Exception {
                            return (ObservableSource<?>) local;
                        }
                    })
        }
    }

    @Override
    public Observable<Boolean> updateMovies(MovieDto movieDto) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }


//    @Override
//    public Observable<List<MoviePopular>> getPopularMovies() {
//        if (local.popularSize() == 0) {
//            return remote.getPopularMovie(App.API_KEY)
//                    .flatMap((Function<List<MoviePopular>, ObservableSource<?>>)
//                            populars -> local.savePopularMovie(populars))
//
//                    .flatMap(o -> local.getPopularMovies());
//        } else {
//            return local.getPopularMovies();
//        }
//    }
//
//    @Override
//    public Observable<List<MovieTopRated>> getTopRatedMovies() {
//        if (local.topRatedSize() == 0) {
//            return remote.getTopRatedMovie(App.API_KEY)
//                    .flatMap((Function<List<MovieTopRated>, ObservableSource<?>>)
//                            topRated -> local.saveTopRateMovie(topRated))
//
//                    .flatMap(o -> local.getTopRatedMovies());
//        } else {
//            return local.getTopRatedMovies();
//        }
//    }
//
//    @Override
//    public Observable<List<MovieUpComing>> getUpComingMovies() {
//        if (local.upComingSize() == 0) {
//            return remote.getUpComingMovie(App.API_KEY)
//                    .flatMap((Function<List<MovieUpComing>, ObservableSource<?>>)
//                            upComings -> local.saveUpComingMovie(upComings))
//
//                    .flatMap(o -> local.getUpComingMovie());
//        } else {
//            return local.getUpComingMovie();
//        }
//    }
//
//    @Override
//    public Observable<List<MovieInTheater>> getInComingMovies() {
//        if (local.inTheaterSize() == 0) {
//            return remote.getInTheaterMovie(App.API_KEY)
//                    .flatMap((Function<List<MovieInTheater>, ObservableSource<?>>)
//                            inTheaters -> local.saveInTheaterMovie(inTheaters))
//
//                    .flatMap(o -> local.getInTheater());
//        } else {
//            return local.getInTheater();
//        }
//    }
//
//    @Override
//    public Observable<Boolean> updatePopularMovies(MoviePopular moviePopular) {
//        return local.updatePopularMovie(moviePopular);
//    }
//
//    @Override
//    public Observable<Boolean> updateTopRatedMovie(MovieTopRated movieTopRated) {
//        return local.updateTopRatedMovie(movieTopRated);
//    }
//
//    @Override
//    public Observable<Boolean> updateUpComingMovie(MovieUpComing movieUpComing) {
//        return local.updateUpComingMovie(movieUpComing);
//    }
//
//    @Override
//    public Observable<Boolean> updateInTheater(MovieInTheater movieInTheater) {
//        return local.updateInTheaterMovie(movieInTheater);
//    }
//
//    @Override
//    public int popularSize() {
//        return local.popularSize();
//    }
//
//    @Override
//    public int topRatedSize() {
//        return local.topRatedSize();
//    }
//
//    @Override
//    public int upComingSize() {
//        return local.upComingSize();
//    }
//
//    @Override
//    public int inTheaterSize() {
//        return local.inTheaterSize();
//    }

}
