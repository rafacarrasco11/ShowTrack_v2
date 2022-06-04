package com.example.showtrack.data.model;

import android.graphics.drawable.Drawable;

import java.io.Serializable;
import java.time.LocalDate;

public class Film implements Serializable {

    int id;
    
    String imdbID;
    String tittle;
    String released;
    String yearReleased;
    String ageRestriction;
    String time;
    String genre;
    String language;
    String plot;
    String country;
    String awards;
    String poster;

    String director;
    String writers;
    String actors;

    String imdbRating;
    String type;
    String boxOffice;

    boolean watched;

    public Film(String tittle, String yearReleased, String imdbID, String genre,  String poster) {
        this.imdbID = imdbID;
        this.tittle = tittle;
        this.poster = poster;
        this.yearReleased = yearReleased;
        this.genre = genre;
    }

    public Film(int id, String imdbID, String tittle, String released, String yearReleased, String ageRestriction, String time, String genre, String language, String plot, String country, String awards, String poster, String director, String writers, String actors, String imdbRating, String type, String boxOffice, boolean watched) {
        this.id = id;
        this.imdbID = imdbID;
        this.tittle = tittle;
        this.released = released;
        this.yearReleased = yearReleased;
        this.ageRestriction = ageRestriction;
        this.time = time;
        this.genre = genre;
        this.language = language;
        this.plot = plot;
        this.country = country;
        this.awards = awards;
        this.poster = poster;
        this.director = director;
        this.writers = writers;
        this.actors = actors;
        this.imdbRating = imdbRating;
        this.type = type;
        this.boxOffice = boxOffice;
        this.watched = watched;
    }

    public String getYearReleased() {
        return yearReleased;
    }

    public void setYearReleased(String yearReleased) {
        this.yearReleased = yearReleased;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(String ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getWriters() {
        return writers;
    }

    public void setWriters(String writers) {
        this.writers = writers;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBoxOffice() {
        return boxOffice;
    }

    public void setBoxOffice(String boxOffice) {
        this.boxOffice = boxOffice;
    }

    public boolean isWatched() {
        return watched;
    }

    public void setWatched(boolean watched) {
        this.watched = watched;
    }


}
