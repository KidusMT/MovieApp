package com.example.kidusmt.movieapp.util;

public final class Constants {
    public static final String CATEGORY_POPULAR = "Popular",
                    CATEGORY_TOP_RATED = "Top Rated",
                    CATEGORY_UPCOMING = "Upcoming",
                    CATEGORY_IN_THEATER = "In Theater";

    public static final String FCATEGORY_POPULAR = "popular",
            FCATEGORY_TOP_RATED = "top_rated",
            FCATEGORY_UPCOMING = "upcoming",
            FCATEGORY_IN_THEATER = "in_theater";

    public final static String API_KEY = "a0757b4d3c350c0a8c4cbf0f561b7edd";
    public static final String TMDB_IMAGE_PATH = "http://image.tmdb.org/t/p/w500";

    //The base URL for shemeta
    public static final String BASE_URL = "https://api.themoviedb.org/3/";

    //ACTIVITY STATE CONSTANTS
    public static final int STATE_IDLE = 0;
    public static final int STATE_LOADING = 1;
    public static final int STATE_COMPLETED = 2;
    public static final int STATE_ERROR = 2;

    public static final String MOVIE_ID = "movie_id";
    public static final String MOVIE_REVIEW = "movie_review";

    public static final String IMAGE_SELECTED = "image_selected";

}