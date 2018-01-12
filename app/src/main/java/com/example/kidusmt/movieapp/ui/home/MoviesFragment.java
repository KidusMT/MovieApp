package com.example.kidusmt.movieapp.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kidusmt.movieapp.base.view.BaseFragment;
import com.example.kidusmt.movieapp.data.movie.Movie;

import java.util.List;

/**
 *
 */
public class MoviesFragment extends BaseFragment implements HomeContract.View {

    private static final String ARG_CATEGORY = "CATEGORY";

    public static MoviesFragment newInstance(String category) {
        MoviesFragment fragment = new MoviesFragment();

        Bundle args = new Bundle();
        args.putString(ARG_CATEGORY, category);
        fragment.setArguments(args);



        return fragment;
    }

    private HomeContract.Presenter presenter;

    private String category;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = super.onCreateView(inflater, container, savedInstanceState);

        Bundle args = getArguments();
        category = args.getString(ARG_CATEGORY);
        if (category == null) throw new NullPointerException("Category is null");

        // TODO: Initialize UI components

        return root;
    }

    @Override
    public void attachPresenter(HomeContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showMovies(List<Movie> movieList) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public void openMovieDetail(int position) {

    }

    @Override
    public void refresh() {

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
}