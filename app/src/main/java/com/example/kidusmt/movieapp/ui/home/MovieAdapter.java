package com.example.kidusmt.movieapp.ui.home;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kidusmt.movieapp.data.Movie;
import com.example.kidusmt.movieapp.R;
import com.example.kidusmt.movieapp.ui.detail.MovieDetailActivity;
import com.example.kidusmt.movieapp.util.App;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by KidusMT on 12/24/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {

    private Context mContext;
    private List<Movie> movieList;

    public MovieAdapter(Context mContext, List<Movie> movieList) {
        this.mContext = mContext;
        this.movieList = movieList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_card, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Movie movie = movieList.get(position);

        holder.movieTitle.setText(movie.getTitle());
        holder.movieRating.setText(movie.getPopularity().toString());
        holder.movieGenre.setText(App.getGenre(movie.getGenreIds()));
        //THIS CAN GET TURNED ON WHEN WE HAVE THERE IS AN API TO CONSUME
        Picasso.with(mContext)
                .load(movie.getPosterPath())
                .placeholder(R.color.colorAccent)
                .into(holder.moviePoster);
        Log.e("----->",movie.getPosterPath());
        holder.moviePoster.setOnClickListener(
                v -> mContext.startActivity(new Intent(mContext,MovieDetailActivity.class)
                        .putExtra("movie_id",movie.getId())
                        .putExtra("movie_review",movie.getOverview())
                        .putExtra("movie_backdrop",movie.getBackdropPath())));

    }

    @Override
    public int getItemCount() {
        return (movieList!=null)?movieList.size():0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView movieTitle,movieGenre,movieRating;
        public ImageView moviePoster;

        public MyViewHolder(View itemView) {
            super(itemView);
            moviePoster = itemView.findViewById(R.id.iv_movie_card_poster);
            movieTitle = itemView.findViewById(R.id.tv_movie_card_title);
            movieRating = itemView.findViewById(R.id.tv_movie_card_rating);
            movieGenre = itemView.findViewById(R.id.tv_movie_card_genre);

        }
    }
}
