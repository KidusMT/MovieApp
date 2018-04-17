package com.example.kidusmt.movieapp.data.remote.movie;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.objectbox.annotation.Convert;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.Index;
import io.objectbox.converter.PropertyConverter;

/**
 * This is a POJO class which is used by retrofit for automatically parsing json object
 */
@Entity
public class Movie {

    @Id
    public long _id;

    @SerializedName("poster_path")
    public String posterPath;
    @SerializedName("overview")
    public String overview;
    @SerializedName("release_date")
    public String releaseDate;
    @SerializedName("genre_ids")
    @Convert(converter = GenreConverter.class, dbType = String.class)
    public List<Integer> genreIds = new ArrayList<Integer>();
    @Index
    @SerializedName("id")
    public Integer id;
    @SerializedName("original_title")
    public String originalTitle;
    @SerializedName("title")
    public String title;
    @SerializedName("backdrop_path")
    public String backdropPath;
    @SerializedName("vote_average")
    public Double voteAverage;
    @SerializedName("vote_count")
    public Integer voteCount;
    public String category;

    //constructor for the movie model class
    public Movie(String posterPath, String overview, String releaseDate, List<Integer> genreIds,
                 Integer id, String originalTitle, String title,
                 String backdropPath, Double voteAverage, Integer voteCount) {
        this.posterPath = posterPath;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.genreIds = genreIds;
        this.id = id;
        this.originalTitle = originalTitle;
        this.title = title;
        this.backdropPath = backdropPath;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
    }

    public static class GenreConverter implements PropertyConverter<List<Integer>, String> {
        @Override
        public List<Integer> convertToEntityProperty(String databaseValue) {
            List<Integer> genres = new ArrayList<>();
            List<String> sgenres = Arrays.asList(databaseValue.split(","));
            if (databaseValue == null) {
                return null;
            }

            for (int i = 0; i < sgenres.size(); i++) {
                genres.add(Integer.parseInt(sgenres.get(i)));
            }

            return genres;
        }

        @Override
        public String convertToDatabaseValue(List<Integer> entityProperty) {
            String values = "";
            if (entityProperty == null) {
                System.out.println("null");
            }

            for (int i = 0; i < entityProperty.size(); i++) {
                values += entityProperty.get(i) + ",";
            }

            //removing the last comma(,) from the string
            if (values != null && values.length() > 0 && values.charAt(values.length() - 1) == ',') {
                values = values.substring(0, values.length() - 1);
            }
            return values;
        }
    }

}
