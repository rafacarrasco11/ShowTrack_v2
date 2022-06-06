package com.example.showtrack.data.model.serie;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.showtrack.data.model.user.User;

@Entity(foreignKeys = {@ForeignKey(entity = Season.class, parentColumns = "id", childColumns = "season_id"), @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "user_id")})
public class Episode {
    @PrimaryKey(autoGenerate = true)
    int id;

    int episodeNumber;
    String released;
    String imdbID;
    String title;
    String imdbRating;

    @ColumnInfo(name = "season_id", index = true)
    public long season_id;

    @ColumnInfo(name = "user_id", index = true)
    public long user_id;


    public Episode(int id, int episodeNumber, String released, String imdbID, String title, String imdbRating, long season_id, long user_id) {
        this.id = id;
        this.episodeNumber = episodeNumber;
        this.released = released;
        this.imdbID = imdbID;
        this.title = title;
        this.imdbRating = imdbRating;
        this.season_id = season_id;
        this.user_id = user_id;
    }

    @Ignore
    public Episode(int episodeNumber, String released, String imdbID, String title, String imdbRating) {
        this.episodeNumber = episodeNumber;
        this.released = released;
        this.imdbID = imdbID;
        this.title = title;
        this.imdbRating = imdbRating;
    }

    @Ignore
    public Episode() {}

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(int episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getSeason_id() {
        return season_id;
    }

    public void setSeason_id(long season_id) {
        this.season_id = season_id;
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
                "episodeNumber=" + episodeNumber +
                ", released='" + released + '\'' +
                ", midbID='" + imdbID + '\'' +
                ", title='" + title + '\'' +
                ", imdbRating='" + imdbRating + '\'' +
                '}';
    }
}
