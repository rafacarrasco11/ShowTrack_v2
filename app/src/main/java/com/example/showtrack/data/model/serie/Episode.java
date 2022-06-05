package com.example.showtrack.data.model.serie;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.showtrack.data.model.user.User;

@Entity(foreignKeys = @ForeignKey(entity = Season.class, parentColumns = "id", childColumns = "season_id"))
public class Episode {
    @PrimaryKey(autoGenerate = true)
    int id;

    int episodeNumber;
    String released;
    String imdbID;
    String title;
    String imdbRating;

    @ColumnInfo(name = "season_id", index = true)
    public long season;

    public Episode(int id, int episodeNumber, String released, String imdbID, String title, String imdbRating, long season) {
        this.id = id;
        this.episodeNumber = episodeNumber;
        this.released = released;
        this.imdbID = imdbID;
        this.title = title;
        this.imdbRating = imdbRating;
        this.season = season;
    }

    @Ignore
    public Episode(int episodeNumber, String released, String imdbID, String title, String imdbRating) {
        this.episodeNumber = episodeNumber;
        this.released = released;
        this.imdbID = imdbID;
        this.title = title;
        this.imdbRating = imdbRating;
    }

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
