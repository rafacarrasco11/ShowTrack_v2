package com.example.showtrack.data.model;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import java.io.Serializable;

public class Film implements Serializable {

    Drawable image;

    String name;
    int year;
    String genre;

    public Film(Drawable image, String name, int year) {
        this.image = image;
        this.name = name;
        this.year = year;
    }

    public Film(String genre, int year, String name) {
        this.name = name;
        this.year = year;
        this.genre = genre;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }




}
