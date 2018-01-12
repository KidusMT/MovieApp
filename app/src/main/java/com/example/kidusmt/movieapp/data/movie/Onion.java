package com.example.kidusmt.movieapp.data.movie;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.Index;
import io.objectbox.relation.ToMany;

/**
 * Onion model
 */
@Entity
public class Onion {

    @Id
    public long id;

    @Index
    public String _id;

    public String color;
    public double weight;
}