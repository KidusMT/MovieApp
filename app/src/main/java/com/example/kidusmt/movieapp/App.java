package com.example.kidusmt.movieapp;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.kidusmt.movieapp.data.model.Genre;
import com.example.kidusmt.movieapp.data.model.MyObjectBox;
import com.example.kidusmt.movieapp.data.repo.genre.RepoGenre;
import com.example.kidusmt.movieapp.data.repo.genre.local.GenreLocal;
import com.example.kidusmt.movieapp.data.repo.genre.remote.GenreRemote;

import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.List;

import io.objectbox.BoxStore;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

/**
 * Created by KidusMT on 12/22/2017.
 */

public class App extends Application {

    public static BoxStore boxStore;
    @SuppressLint("StaticFieldLeak")
    public static Context context;
    public static HashMap<Integer, String> genreIds = new HashMap();
    RepoGenre repository;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        //Initializes ObjectBox for the first time when application runs
        if (boxStore == null) boxStore = MyObjectBox.builder().androidContext(this).build();

        repository = new RepoGenre(new GenreLocal(boxStore), new GenreRemote());

        repository.getGenres()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<List<Genre>>() {
                    @Override
                    public void onNext(List<Genre> genres) {
                        for (Genre genre : genres) {
                            genreIds.put(genre.getId(), genre.getName());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
//                        if (((HttpException) e).code() == HttpURLConnection.HTTP_BAD_REQUEST) {
//                            ResponseBody responseBody = ((HttpException) e).response().errorBody();
//                            if (responseBody != null) {
//                                Toast.makeText(App.this, responseBody.toString(), Toast.LENGTH_SHORT).show();
//                            }
                            e.printStackTrace();
//                        }
                    }

                    @Override
                    public void onComplete() {
                    }
                });

    }

    public static String getGenre(List<Integer> genres) {
        String genre_string = "";
        for (int x : genres) {
            genre_string += genreIds.get(x) + ", ";
        }

        return genre_string;
    }

    public static Context getContext() {
        return context;
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
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
