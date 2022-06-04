package com.example.showtrack.data.model.serie;

import java.util.List;

public class Season {
    int id;

    String serieImdbID;
    String serieTittle;
    int seasonNumber;

    List<Episode> episodes;

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

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }
}
