//package com.example.kidusmt.movieapp.ui.home;
//
//import com.example.kidusmt.movieapp.data.RepoMovieContract;
//import com.example.kidusmt.movieapp.data.local.movie.Movie;
//
//import java.util.List;
//
//import io.reactivex.android.schedulers.AndroidSchedulers;
//import io.reactivex.observers.DisposableObserver;
//import io.reactivex.schedulers.Schedulers;
//
///**
// * Created by KidusMT on 1/4/2018.
// */
//
//public class HomePresenter implements HomeContract.Presenter {
//
//    private HomeContract.View view;
//    private RepoMovieContract repository;
////    private HomeState state; TODO have to implement state for progress bar display
//
//    public HomePresenter(RepoMovieContract repository){
//        this.repository = repository;
//    }
//
//    @Override
//    public void onMovieClicked(int position) {
//        view.openMovieDetail(position);
//    }
//
//    @Override
//    public void onSwippedDownToRefresh() {
//        //TODO have to implement the swipeDown to refresh layout first
//    }
//
//    @Override
//    public void start() {
//        //if(state.loading()) return;
//
//        loadMovies();
//    }
//
//    @Override
//    public void loadMovies() {
////        view.showProgress();
//
//        repository
//                .getMovies(view.getCategory())//TODO check if this is correct
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new DisposableObserver<List<Movie>>() {
//                    @Override
//                    public void onNext(List<Movie> movies) {
//                        if (view == null) return;
////                        view.hideProgress();
//                        view.showMovies(movies);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//    }
//
//    @Override
//    public void attachView(HomeContract.View view) {
//        this.view = view;
//    }
//
//    @Override
//    public void detachView() {
//        view = null;
//    }
//
//    @Override
//    public HomeContract.View getView() {
//        return view;
//    }
//}
