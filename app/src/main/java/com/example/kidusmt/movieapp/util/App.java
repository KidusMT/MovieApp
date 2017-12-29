package com.example.kidusmt.movieapp.util;

import android.app.Application;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import com.facebook.FacebookSdk;
//import com.facebook.appevents.AppEventsLogger;

/**
 * Created by KidusMT on 12/22/2017.
 */

public class App extends Application {

    public final static String API_KEY = "a0757b4d3c350c0a8c4cbf0f561b7edd";
    public static final String TMDB_IMAGE_PATH = "http://image.tmdb.org/t/p/w500";

    public static HashMap<Integer,String> genreIds = new HashMap();

    @Override
    public void onCreate() {
        super.onCreate();
        //Initializing HashMap for GenreIds
        genreIds.put(10759,"Action & Adventure");
        genreIds.put(16, "Animation");
        genreIds.put(35,"Comedy");
        genreIds.put(80, "Crime");
        genreIds.put(99, "Documentary");
        genreIds.put(18, "Drama");
        genreIds.put(10751, "Family");
        genreIds.put(10762, "Kids");
        genreIds.put(9648, "Mystery");
        genreIds.put(10763, "News");
        genreIds.put(10764, "Reality");
        genreIds.put(10765, "Sci-Fi & Fantasy");
        genreIds.put(10766, "Soap");
        genreIds.put(10767, "Talk");
        genreIds.put(10768, "War & Politics");
        genreIds.put(37, "Western");

//        FacebookSdk.sdkInitialize(getApplicationContext());
//        AppEventsLogger.activateApp(this);
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public static String getGenre(List<Integer> genres){
        String genre_string = "";
        for(int x: genres){
            genre_string += genreIds.get(x);
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
