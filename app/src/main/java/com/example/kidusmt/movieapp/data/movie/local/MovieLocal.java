package com.example.kidusmt.movieapp.data.movie.local;

import com.example.kidusmt.movieapp.data.movie.MovieInTheater;
import com.example.kidusmt.movieapp.data.movie.MoviePopular;
import com.example.kidusmt.movieapp.data.movie.MovieTopRated;
import com.example.kidusmt.movieapp.data.movie.MovieUpComing;

import java.util.List;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.reactivex.Observable;

/**
 * Created by KidusMT on 1/4/2018.
 */

public class MovieLocal implements MovieLocalContract {

    private Box<MoviePopular> popularBox;
    private Box<MovieUpComing> upComingBox;
    private Box<MovieInTheater> inTheaterBox;
    private Box<MovieTopRated> topRatedBox;

    public MovieLocal (BoxStore store){
        this.popularBox  = store.boxFor(MoviePopular.class);
        this.upComingBox = store.boxFor(MovieUpComing.class);
        this.inTheaterBox = store.boxFor(MovieInTheater.class);
        this.topRatedBox = store.boxFor(MovieTopRated.class);

    }

    @Override
    public Observable<List<MoviePopular>> getPopularMovies() {
        List<MoviePopular> populars;
        populars = popularBox.getAll();
        return Observable.just(populars);
    }

    @Override
    public Observable<List<MovieTopRated>> getTopRatedMovies() {
        List<MovieTopRated> topRated;
        topRated = topRatedBox.getAll();
        return Observable.just(topRated);
    }

    @Override
    public Observable<List<MovieUpComing>> getUpComingMovie() {
        List<MovieUpComing> upComings;
        upComings = upComingBox.getAll();
        return Observable.just(upComings);
    }

    @Override
    public Observable<List<MovieInTheater>> getInTheater() {
        List<MovieInTheater> inTheaters;
        inTheaters = inTheaterBox.getAll();
        return Observable.just(inTheaters);
    }

    @Override
    public Observable<Boolean> savePopularMovie(List<MoviePopular> populars) {
        for(int i = 0 ; i < populars.size(); i++){
            MoviePopular newPopular = populars.get(i);

            MoviePopular found = popularBox.query().equal(Post_.id, newPopular.id).build().findFirst();
            if(found == null){
                popularBox.put(newPopular);
            }else{
                newPopular._id =found._id;

                popularBox.put(newPopular);
            }
        }
        return Observable.just(true);
    }

    @Override
    public Observable<Boolean> saveTopRateMovie(List<MovieTopRated> topRateds) {
        for(int i = 0 ; i < topRateds.size(); i++){
            MovieTopRated newTopRated = topRateds.get(i);

            MovieTopRated found = topRatedBox.query().equal(Post_.id, newTopRated.id).build().findFirst();
            if(found == null){
                topRatedBox.put(newTopRated);
            }else{
                newTopRated._id =found._id;

                topRatedBox.put(newTopRated);
            }
        }
        return Observable.just(true);
    }

    @Override
    public Observable<Boolean> saveUpComingMovie(List<MovieUpComing> upComings) {
        for(int i = 0 ; i < upComings.size(); i++){
            MovieUpComing newUpComings = upComings.get(i);

            MovieUpComing found = upComingBox.query().equal(Post_.id, newUpComings.id).build().findFirst();
            if(found == null){
                upComingBox.put(newUpComings);
            }else{
                newUpComings._id =found._id;

                upComingBox.put(newUpComings);
            }
        }
        return Observable.just(true);
    }

    @Override
    public Observable<Boolean> saveInTheaterMovie(List<MovieInTheater> inTheaters) {
        for(int i = 0 ; i < inTheaters.size(); i++){
            MovieInTheater newInTheaters = inTheaters.get(i);

            MovieInTheater found = inTheaterBox.query().equal(Post_.id, newInTheaters.id).build().findFirst();
            if(found == null){
                inTheaterBox.put(newInTheaters);
            }else{
                newInTheaters._id =found._id;

                inTheaterBox.put(newInTheaters);
            }
        }
        return Observable.just(true);
    }

    @Override
    public Observable<Boolean> updatePopularMovie(MoviePopular moviePopular) {
        popularBox.put(moviePopular);
        return Observable.just(true);
    }

    @Override
    public Observable<Boolean> updateInTheaterMovie(MovieInTheater movieInTheater) {
        inTheaterBox.put(movieInTheater);
        return Observable.just(true);
    }

    @Override
    public Observable<Boolean> updateUpComingMovie(MovieUpComing movieUpComing) {
        upComingBox.put(movieUpComing);
        return Observable.just(true);
    }

    @Override
    public Observable<Boolean> updateTopRatedMovie(MovieTopRated movieTopRated) {
        topRatedBox.put(movieTopRated);
        return Observable.just(true);
    }

    @Override
    public int popularSize() {
        return popularBox.getAll().size();
    }

    @Override
    public int inTheaterSize() {
        return inTheaterBox.getAll().size();
    }

    @Override
    public int topRatedSize() {
        return topRatedBox.getAll().size();
    }

    @Override
    public int upComingSize() {
        return upComingBox.getAll().size();
    }
}
