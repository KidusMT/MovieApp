package com.example.kidusmt.movieapp.data.remote.genre;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by KidusMT on 12/28/2017.
 */

public class GenreResponse {

    @SerializedName("genres")
    private List<Genre> genres = null;

    public List<Genre> getGenres() {
        return genres;
    }

}
