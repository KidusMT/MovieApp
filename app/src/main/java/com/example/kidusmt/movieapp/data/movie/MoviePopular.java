package com.example.kidusmt.movieapp.data.movie;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.Index;

/**
 * Created by KidusMT on 1/9/2018.
 */
@Entity
public class MoviePopular {

    @Id
    public long id;

    @Index
    public int _id;

    public String posterPath;

    public String overview;

    public String releaseDate;

    public List<Integer> genreIds = new ArrayList<>();

    public String originalTitle;

    public String title;

    public String backdropPath;

    public Double voteAverage;

    public Integer voteCount;
}