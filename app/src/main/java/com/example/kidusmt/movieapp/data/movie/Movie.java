package com.example.kidusmt.movieapp.data.movie;

import java.util.List;

import io.objectbox.annotation.Id;
import io.objectbox.annotation.Index;

public class Movie {

    @Id
    public long id;

    @Index
    public int _id;

    public String posterPath;
    public String overview;
    public String releaseDate;

    // TODO: Use converter
//    @Convert(dbType = String.class)
    public List<Integer> genreIds;
    public String originalTitle;
    public String title;
    public String backdropPath;
    public double voteAverage;
    public int voteCount;
    public String category;
}