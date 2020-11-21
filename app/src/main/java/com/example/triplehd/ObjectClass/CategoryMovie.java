package com.example.triplehd.ObjectClass;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CategoryMovie {
    private ArrayList<Phim> movie = new ArrayList<Phim>() ;

    public CategoryMovie(ArrayList<Phim> movie) {
        this.movie = movie;
    }

    public ArrayList<Phim> getMovie() {
        return movie;
    }

    public void setMovie(ArrayList<Phim> movie) {
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "CategoryMovie{" +
                "movie=" + movie +
                '}';
    }
}
