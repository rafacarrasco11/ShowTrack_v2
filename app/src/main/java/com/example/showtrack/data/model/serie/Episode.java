package com.example.showtrack.data.model.serie;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.showtrack.data.model.user.User;

import java.io.Serializable;

/**
 * Clase POJO para la entidad de un Episodio
 *
 * Esta entidad aparece en la pantalla de una serie, en forma de lista al fondo de la pantalla.
 *
 * Podemos observar las anotaciones para la base de datos ROOM, ya que esta almacena objetos de Episodios. Se identifica mediante el id del  usuario ue lo añade.
 * Cuando el usuario aáde el episodio en la aplicacion significa que lo ha visto.
 */
@Entity(foreignKeys =  @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "user_id"))
public class Episode implements Serializable {
    @PrimaryKey
    @NonNull
    String id;

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
    int episodeNumber;

    String director;
    String writers;
    String actors;

    String imdbRating;

    @Ignore
    Serie serie;

    @ColumnInfo(name = "user_id", index = true)
    public long user_id;

    public Episode(@NonNull String id, String imdbID, String tittle, String yearReleased, String ageRestriction, String time, String genre, String language, String plot, String country, String awards, String poster, int episodeNumber, String director, String writers, String actors, String imdbRating, Serie serie, long user_id) {
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
        this.episodeNumber = episodeNumber;
        this.director = director;
        this.writers = writers;
        this.actors = actors;
        this.imdbRating = imdbRating;
        this.serie = serie;
        this.user_id = user_id;
    }

    public Episode() {
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
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

    public String getYearReleased() {
        return yearReleased;
    }

    public void setYearReleased(String yearReleased) {
        this.yearReleased = yearReleased;
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

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(int episodeNumber) {
        this.episodeNumber = episodeNumber;
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

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Episode{" +
                "id='" + id + '\'' +
                ", imdbID='" + imdbID + '\'' +
                ", tittle='" + tittle + '\'' +
                ", yearReleased='" + yearReleased + '\'' +
                ", ageRestriction='" + ageRestriction + '\'' +
                ", time='" + time + '\'' +
                ", genre='" + genre + '\'' +
                ", language='" + language + '\'' +
                ", plot='" + plot + '\'' +
                ", country='" + country + '\'' +
                ", awards='" + awards + '\'' +
                ", poster='" + poster + '\'' +
                ", episodeNumber=" + episodeNumber +
                ", director='" + director + '\'' +
                ", writers='" + writers + '\'' +
                ", actors='" + actors + '\'' +
                ", imdbRating='" + imdbRating + '\'' +
                ", serie=" + serie +
                ", user_id=" + user_id +
                '}';
    }
}
