package com.example.kidusmt.movieapp.data.remote.movie;

import com.example.kidusmt.movieapp.data.local.movie.Movie;
import com.example.kidusmt.movieapp.util.Constants;
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

    @Index
    public int _id;

    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("overview")
    private String overview;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("genre_ids")
    @Convert(converter = GenreConverter.class, dbType = String.class)
    private List<Integer> genreIds = new ArrayList<Integer>();
    @Id
    @SerializedName("id")
    private Integer id;
    @SerializedName("original_title")
    private String originalTitle;
    @SerializedName("title")
    private String title;
    @SerializedName("backdrop_path")
    private String backdropPath;
    @SerializedName("vote_average")
    private Double voteAverage;
    @SerializedName("vote_count")
    private Integer voteCount;

    //constructor for the movie model class
    public Movie(String posterPath, String overview, String releaseDate, List<Integer> genreIds,
                 Integer id, String originalTitle, String title,
                 String backdropPath, Double voteAverage, Integer voteCount){
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

            for (int i = 0; i< sgenres.size(); i++) {
                genres.add(Integer.parseInt(sgenres.get(i)));
            }

            return genres;
        }

        @Override
        public String convertToDatabaseValue(List<Integer> entityProperty) {
            String values = "";
            if(entityProperty == null) {System.out.println("null");}

            for (int i = 0; i< entityProperty.size(); i++){
                values += entityProperty.get(i)+",";
            }

            //removing the last comma(,) from the string
            if (values != null && values.length() > 0 && values.charAt(values.length() - 1) == ',') {
                values = values.substring(0, values.length() - 1);
            }
            return values;
        }
    }

    public String getPosterPath() {
        return Constants.TMDB_IMAGE_PATH + posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackdropPath() {
        return Constants.TMDB_IMAGE_PATH + backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }
}
