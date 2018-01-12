package com.example.kidusmt.movieapp.ui.home;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kidusmt.movieapp.R;
import com.example.kidusmt.movieapp.data.movie.Movie;
import com.example.kidusmt.movieapp.ui.detail.MovieDetailActivity;
import com.example.kidusmt.movieapp.util.App;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by KidusMT on 1/9/2018.
 */

public class MovieViewHolder extends RecyclerView.ViewHolder {

    public TextView movieTitle,movieGenre,movieRating;
    public ImageView moviePoster;

    public MovieViewHolder(View itemView, final HomeContract.Presenter presenter) {
        super(itemView);
        moviePoster = itemView.findViewById(R.id.iv_movie_card_poster);
        movieTitle = itemView.findViewById(R.id.tv_movie_card_title);
        movieRating = itemView.findViewById(R.id.tv_movie_card_rating);
        movieGenre = itemView.findViewById(R.id.tv_movie_card_genre);

        //Takes care of the onclick for more detail
        moviePoster.setOnClickListener(v->presenter.onMovieClicked(getAdapterPosition()));
    }

    public void update(List<Movie> posts) {
        Movie movie = posts.get(getAdapterPosition());

        movieTitle.setText(movie.getTitle());
        movieRating.setText(movie.getVoteAverage().toString());
        movieGenre.setText(App.getGenre(movie.getGenreIds()));
        //THIS CAN GET TURNED ON WHEN WE HAVE THERE IS AN API TO CONSUME
        Picasso.with(moviePoster.getContext())
                .load(movie.getPosterPath())
                .placeholder(R.color.colorAccent)
                .into(moviePoster);
//        Log.e("----->",movie.getPosterPath());
        moviePoster.setOnClickListener(
                v -> moviePoster.getContext().startActivity(new Intent(moviePoster.getContext(),
                        MovieDetailActivity.class)
                        .putExtra("movie_id",movie.getId())
                        .putExtra("movie_review",movie.getOverview())
                        .putExtra("movie_backdrop",movie.getBackdropPath())));
    }
}
