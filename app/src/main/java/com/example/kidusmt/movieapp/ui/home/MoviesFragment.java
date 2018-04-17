package com.example.kidusmt.movieapp.ui.home;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kidusmt.movieapp.R;
import com.example.kidusmt.movieapp.base.view.BaseFragment;
import com.example.kidusmt.movieapp.data.repo.movie.RepoMovie;
import com.example.kidusmt.movieapp.data.repo.movie.local.MovieLocal;
import com.example.kidusmt.movieapp.data.model.Movie;
import com.example.kidusmt.movieapp.data.repo.movie.remote.MovieRemote;
import com.example.kidusmt.movieapp.ui.detail.MovieDetailActivity;
import com.example.kidusmt.movieapp.util.App;
import com.example.kidusmt.movieapp.util.Utils;
import com.pnikosis.materialishprogress.ProgressWheel;

import java.util.ArrayList;
import java.util.List;

import static com.example.kidusmt.movieapp.util.Constants.MOVIE_BACKDROP;
import static com.example.kidusmt.movieapp.util.Constants.MOVIE_ID;
import static com.example.kidusmt.movieapp.util.Constants.MOVIE_REVIEW;

public class MoviesFragment extends BaseFragment implements HomeContract.View {

    private static final String ARG_CATEGORY = "CATEGORY";
    SwipeRefreshLayout swipeRefreshLayout;
    public ProgressWheel progressWheel;
    List<Movie> movies;
    RecyclerView recyclerView;
    MovieAdapter adapter;

    public static MoviesFragment newInstance(String category) {
        MoviesFragment fragment = new MoviesFragment();

        Bundle args = new Bundle();
        args.putString(ARG_CATEGORY, category);
        fragment.setArguments(args);

        return fragment;
    }

    private HomeContract.Presenter presenter;

    private String category;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movies = new ArrayList<>();
        presenter = new MoviesPresenter(new RepoMovie(
                new MovieLocal(App.boxStore),
                new MovieRemote()));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_movie, container, false);
        progressWheel = root.findViewById(R.id.home_progress_wheel);
        swipeRefreshLayout = root.findViewById(R.id.home_swipe_to_refresh);
        Bundle args = getArguments();
        if (args!=null){
            category = args.getString(ARG_CATEGORY);
            if (category == null) throw new NullPointerException("Category is null");
        }

        recyclerView = root.findViewById(R.id.recycler_view_movie);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(
                new App.GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new MovieAdapter(movies, presenter);
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(() -> {
            presenter.loadMovies();
            swipeRefreshLayout.setRefreshing(false);
        });

        return root;
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    @Override
    public void attachPresenter(HomeContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showMovies(List<Movie> movieList) {
        adapter.update(movieList);
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public void openMovieDetail(Movie movie) {
        Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
        intent.putExtra(MOVIE_ID, movie.id);
        intent.putExtra(MOVIE_REVIEW, movie.overview);
        intent.putExtra(MOVIE_BACKDROP, movie.backdropPath);
        startActivity(intent);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.attachView(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void onPause() {
        presenter.detachView();
        super.onPause();
    }

    @Override
    public void close() {
        getActivity().finish();
    }

    @Override
    public void showLoading(String message) {
        progressWheel.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoading() {
        progressWheel.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressWheel.setVisibility(View.GONE);
    }

    @Override
    public void onUnknownError(String error) {
        Utils.toast(getContext(), error);
    }

    @Override
    public void onTimeout() {
        Utils.toast(getContext(), "connection timeout");
    }

    @Override
    public void onNetworkError() {
        Utils.toast(getContext(), "network error");
    }

    @Override
    public boolean isNetworkConnected() {
        return false;
    }

    @Override
    public void onConnectionError() {
        Utils.toast(getContext(), "connection error");
    }
}