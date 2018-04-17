package com.example.kidusmt.movieapp.ui.home;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kidusmt.movieapp.R;
import com.example.kidusmt.movieapp.data.model.Movie;
import com.example.kidusmt.movieapp.ui.detail.MovieDetailActivity;
import com.example.kidusmt.movieapp.ui.splash.SplashActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by KidusMT on 1/9/2018.
 */

public class MovieViewHolder extends RecyclerView.ViewHolder {

    public TextView movieTitle, movieGenre, movieRating;
    public ImageView moviePoster;

    public MovieViewHolder(View itemView) {
        super(itemView);
        moviePoster = itemView.findViewById(R.id.iv_movie_card_poster);
        movieTitle = itemView.findViewById(R.id.tv_movie_card_title);
        movieRating = itemView.findViewById(R.id.tv_movie_card_rating);
        movieGenre = itemView.findViewById(R.id.tv_movie_card_genre);
    }

    public void update(List<Movie> posts) {
        Movie movie = posts.get(getAdapterPosition());

        movieTitle.setText(movie.title);
        //TODO check is the String.format() method is correct
        movieRating.setText(String.format("%s", movie.voteAverage));
        movieGenre.setText(SplashActivity.getGenre(movie.genreIds));
        //THIS CAN GET TURNED ON WHEN WE HAVE THERE IS AN API TO CONSUME
        Picasso.with(moviePoster.getContext())
                .load(movie.posterPath)
                .placeholder(R.color.colorAccent)
                .into(moviePoster);
//        Log.e("----->",movie.getPosterPath());
        moviePoster.setOnClickListener(
                v -> moviePoster.getContext().startActivity(new Intent(moviePoster.getContext(),
                        MovieDetailActivity.class)
                        //TODO check weather its _id or id ??
                        .putExtra("movie_id", movie._id)
                        .putExtra("movie_review", movie.overview)
                        .putExtra("movie_backdrop", movie.backdropPath)));
    }
}
