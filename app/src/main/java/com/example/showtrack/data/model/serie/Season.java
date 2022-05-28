package com.example.showtrack.data.model.serie;

import java.util.List;

public class Season {
    int id;

    int serieImdbID;
    String serieTittle;
    int seasonNumber;

    List<Episode> episodes;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSerieImdbID() {
        return serieImdbID;
    }

    public void setSerieImdbID(int serieImdbID) {
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

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }
}
