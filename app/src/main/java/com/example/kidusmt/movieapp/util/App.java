package com.example.kidusmt.movieapp.util;

import android.app.Application;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.kidusmt.movieapp.data.Genre;
import com.example.kidusmt.movieapp.data.GenreResponse;
import com.example.kidusmt.movieapp.data.Movie;
import com.example.kidusmt.movieapp.data.MoviesResponse;
import com.example.kidusmt.movieapp.data.rest.ApiClient;
import com.example.kidusmt.movieapp.ui.home.MovieAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//import com.facebook.FacebookSdk;
//import com.facebook.appevents.AppEventsLogger;

/**
 * Created by KidusMT on 12/22/2017.
 */

public class App extends Application {

    public final static String API_KEY = "a0757b4d3c350c0a8c4cbf0f561b7edd";
    public static final String TMDB_IMAGE_PATH = "http://image.tmdb.org/t/p/w500";

    public static HashMap<Integer,String> genreIds = new HashMap();

    Call<GenreResponse> callGenreRated;

    @Override
    public void onCreate() {
        super.onCreate();

        callGenreRated = ApiClient.getApiService().getGenreList(App.API_KEY);

        callGenreRated.enqueue(new Callback<GenreResponse>() {
            @Override
            public void onResponse(Call<GenreResponse> call, Response<GenreResponse> response) {
                List<Genre> genreList = response.body().getGenres();

                for(Genre genre: genreList){
                    genreIds.put(genre.getId(),genre.getName());
                }
            }

            @Override
            public void onFailure(Call<GenreResponse> call, Throwable t) {
               //TODO find a way to tell users that it does not have a genre name yet
            }
        });

    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public static String getGenre(List<Integer> genres){
        String genre_string = "";
        for(int x: genres){
            genre_string += genreIds.get(x)+", ";
        }

        return genre_string;
    }

    public static class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {
        private int spanCount;
        private int spacing;

        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }

    }

}
