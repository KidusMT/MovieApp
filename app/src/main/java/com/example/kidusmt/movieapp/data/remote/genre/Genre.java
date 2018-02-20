package com.example.kidusmt.movieapp.data.remote.genre;

import com.google.gson.annotations.SerializedName;

/**
 * Created by KidusMT on 1/3/2018.
 */

public class Genre {

    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
