package com.example.showtrack.data.model.serie;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.showtrack.data.model.user.User;

import java.util.List;

@Entity(foreignKeys = @ForeignKey(entity = Serie.class, parentColumns = "id", childColumns = "serie_id"))
public class Season {
    @PrimaryKey(autoGenerate = true)
    int id;

    String serieImdbID;
    String serieTittle;
    int seasonNumber;

    @ColumnInfo(name = "serie_id", index = true)
    public long serie;

    @Ignore
    List<Episode> episodes;

    public Season(int id, String serieImdbID, String serieTittle, int seasonNumber, long serie) {
        this.id = id;
        this.serieImdbID = serieImdbID;
        this.serieTittle = serieTittle;
        this.seasonNumber = seasonNumber;
        this.serie = serie;
    }

    @Ignore
    public Season( String serieImdbID, String serieTittle, int seasonNumber, List<Episode> episodes) {
        this.serieImdbID = serieImdbID;
        this.serieTittle = serieTittle;
        this.seasonNumber = seasonNumber;
        this.episodes = episodes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSerieImdbID() {
        return serieImdbID;
    }

    public void setSerieImdbID(String serieImdbID) {
        this.serieImdbID = serieImdbID;
    }

    public String getSerieTittle() {
        return serieTittle;
    }

    public void setSerieTittle(String serieTittle) {
        this.serieTittle = serieTittle;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(int seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public long getSerie() {
        return serie;
    }

    public void setSerie(long serie) {
        this.serie = serie;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }
}
