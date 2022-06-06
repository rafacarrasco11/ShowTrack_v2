package com.example.showtrack.data.model.serie;

import android.graphics.drawable.Drawable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.showtrack.data.model.user.User;

import java.time.LocalDate;
import java.util.List;

@Entity(foreignKeys = @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "user_id"))
public class Serie {
    @PrimaryKey(autoGenerate = true)
    int id;

    String imdbID;
    String tittle;
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
    int totalSeasons;

    @Ignore
    List<Season> seasons;

    @ColumnInfo(name = "user_id",index = true)
    public long user;

    public Serie(int id, String imdbID, String tittle, String yearReleased, String ageRestriction, String time, String genre, String language, String plot, String country, String awards, String poster, String director, String writers, String actors, String imdbRating, String type, int totalSeasons, long user) {
        this.id = id;
        this.imdbID = imdbID;
        this.tittle = tittle;
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
        this.totalSeasons = totalSeasons;
        this.user = user;
    }

    @Ignore
    public Serie(String tittle, String yearReleased, String imdbID, String genre,  String poster) {
        this.imdbID = imdbID;
        this.tittle = tittle;
        this.poster = poster;
        this.yearReleased = yearReleased;
        this.genre = genre;
    }

    @Ignore
    public Serie() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTotalSeasons() {
        return totalSeasons;
    }

    public void setTotalSeasons(int totalSeasons) {
        this.totalSeasons = totalSeasons;
    }

    public String getYearReleased() {
        return yearReleased;
    }

    public void setYearReleased(String yearReleased) {
        this.yearReleased = yearReleased;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
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

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public List<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<Season> seasons) {
        this.seasons = seasons;
    }

    public long getUser() {
        return user;
    }

    public void setUser(long user) {
        this.user = user;
    }

    public String makePlot() {
        return "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n" +
                "\n";
    }
}
