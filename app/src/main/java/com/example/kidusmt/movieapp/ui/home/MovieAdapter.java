package com.example.kidusmt.movieapp.ui.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kidusmt.movieapp.R;
import com.example.kidusmt.movieapp.data.remote.movie.Movie;

import java.util.List;

/**
 * Created by KidusMT on 12/24/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private List<Movie> movieList;
    private HomeContract.Presenter presenter;

    public MovieAdapter(List<Movie> movieList, HomeContract.Presenter presenter) {
        this.presenter = presenter;
        this.movieList = movieList;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_card, parent, false);
        return new MovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.update(movieList);
        holder.moviePoster.setOnClickListener(v -> presenter.onMovieClicked(movieList.get(holder.getAdapterPosition())));
    }

    //for updating and putting the recyclerView on the fragments
    public void update(List<Movie> data) {
        movieList.clear();
        movieList.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return (movieList != null) ? movieList.size() : 0;
    }

}
