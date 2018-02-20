package com.example.kidusmt.movieapp.data.local.movie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.objectbox.annotation.Convert;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.Index;
import io.objectbox.converter.PropertyConverter;

@Entity
public class Movie {

    @Id
    public long id;

    @Index
    public int _id;

    public String posterPath;
    public String overview;
    public String releaseDate;

    @Convert(converter = GenreConverter.class, dbType = String.class)
    public List<Integer> genreIds;

    public String originalTitle;
    public String title;
    public String backdropPath;
    public double voteAverage;
    public int voteCount;
    public String category;

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
}
