package com.example.kidusmt.movieapp.ui.home;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kidusmt.movieapp.R;
import com.example.kidusmt.movieapp.base.view.BaseFragment;
import com.example.kidusmt.movieapp.data.movie.Movie;
import com.example.kidusmt.movieapp.util.App;

import java.util.List;

public class MoviesFragment extends BaseFragment implements HomeContract.View {

    private static final String ARG_CATEGORY = "CATEGORY";
    private RecyclerView recyclerView;
    private MovieAdapter adapter;

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
        recyclerView = root.findViewById(R.id.recycler_view_top_rated);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(
                new App.GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new MovieAdapter(presenter);//movieList is needed
        recyclerView.setAdapter(adapter);

        recyclerView.setAdapter(adapter);
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