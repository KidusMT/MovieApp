package com.example.kidusmt.movieapp.ui.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kidusmt.movieapp.data.movie.Movie;
import com.example.kidusmt.movieapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KidusMT on 12/24/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private List<Movie> movieList;
    private HomeContract.Presenter presenter;

    public MovieAdapter(HomeContract.Presenter presenter) {
       this.presenter = presenter;
        movieList = new ArrayList<>();
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_card, parent, false);
        return new MovieViewHolder(itemView, presenter);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.update(movieList);
    }

    //for updating and putting the recyclerView on the fragments
    public void update(List<Movie> data) {
        movieList.clear();
        movieList.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return (movieList!=null)?movieList.size():0;
    }

}
