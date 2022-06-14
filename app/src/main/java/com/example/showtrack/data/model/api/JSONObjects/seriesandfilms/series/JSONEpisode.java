package com.example.showtrack.data.model.api.JSONObjects.seriesandfilms.series;

import androidx.room.Ignore;

/**
 * Clase POJO para los objetos JSON que deveulve la API de series y peliculas
 */
public class JSONEpisode {
    String imdbID;
    String Title;
    String YearReleased;
    String AgeRestriction;
    String Time;
    String Genre;
    String Language;
    String Plot;
    String Country;
    String Awards;
    String Poster;
    String Runtime;
    int Episode;

    String Director;
    String Writers;
    String Actors;

    String imdbRating;

    public JSONEpisode(String imdbID, String title, String yearReleased, String ageRestriction, String time, String genre, String language, String plot, String country, String awards, String poster, int episode, String director, String writers, String actors, String imdbRating) {
        this.imdbID = imdbID;
        Title = title;
        YearReleased = yearReleased;
        AgeRestriction = ageRestriction;
        Time = time;
        Genre = genre;
        Language = language;
        Plot = plot;
        Country = country;
        Awards = awards;
        Poster = poster;
        Episode = episode;
        Director = director;
        Writers = writers;
        Actors = actors;
        this.imdbRating = imdbRating;
    }

    @Ignore
    public JSONEpisode() {
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getYearReleased() {
        return YearReleased;
    }

    public void setYearReleased(String yearReleased) {
        YearReleased = yearReleased;
    }

    public String getAgeRestriction() {
        return AgeRestriction;
    }

    public void setAgeRestriction(String ageRestriction) {
        AgeRestriction = ageRestriction;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public String getPlot() {
        return Plot;
    }

    public void setPlot(String plot) {
        Plot = plot;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getAwards() {
        return Awards;
    }

    public void setAwards(String awards) {
        Awards = awards;
    }

    public String getPoster() {
        return Poster;
    }

    public void setPoster(String poster) {
        Poster = poster;
    }

    public String getRuntime() {
        return Runtime;
    }

    public void setRuntime(String runtime) {
        Runtime = runtime;
    }

    public int getEpisode() {
        return Episode;
    }

    public void setEpisode(int episode) {
        Episode = episode;
    }

    public String getDirector() {
        return Director;
    }

    public void setDirector(String director) {
        Director = director;
    }

    public String getWriters() {
        return Writers;
    }

    public void setWriters(String writers) {
        Writers = writers;
    }

    public String getActors() {
        return Actors;
    }

    public void setActors(String actors) {
        Actors = actors;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    @Override
    public String toString() {
        return "JSONEpisode{" +
                "imdbID='" + imdbID + '\'' +
                ", Title='" + Title + '\'' +
                ", YearReleased='" + YearReleased + '\'' +
                ", AgeRestriction='" + AgeRestriction + '\'' +
                ", Time='" + Time + '\'' +
                ", Genre='" + Genre + '\'' +
                ", Language='" + Language + '\'' +
                ", Plot='" + Plot + '\'' +
                ", Country='" + Country + '\'' +
                ", Awards='" + Awards + '\'' +
                ", Poster='" + Poster + '\'' +
                ", Runtime='" + Runtime + '\'' +
                ", Episode=" + Episode +
                ", Director='" + Director + '\'' +
                ", Writers='" + Writers + '\'' +
                ", Actors='" + Actors + '\'' +
                ", imdbRating='" + imdbRating + '\'' +
                '}';
    }
}
