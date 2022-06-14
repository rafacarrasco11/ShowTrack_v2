package com.example.showtrack.data.model.api.JSONObjects.seriesandfilms.series;

import java.util.List;

/**
 * Clase POJO para los objetos JSON que deveulve la API de series y peliculas
 */
public class JSONSearchSeason {
    String Title;
    String Season;
    List<JSONEpisode> Episodes;

    public JSONSearchSeason(String title, String season, List<JSONEpisode> episodes) {
        Title = title;
        Season = season;
        Episodes = episodes;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getSeason() {
        return Season;
    }

    public void setSeason(String season) {
        Season = season;
    }

    public List<JSONEpisode> getEpisodes() {
        return Episodes;
    }

    public void setEpisodes(List<JSONEpisode> episodes) {
        Episodes = episodes;
    }

    @Override
    public String toString() {
        return "JSONSearchSeason{" +
                "Title='" + Title + '\'' +
                ", Season='" + Season + '\'' +
                ", Episodes=" + Episodes +
                '}';
    }
}
