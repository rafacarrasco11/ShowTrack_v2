package com.example.showtrack.data.model.api.JSONObjects.seriesandfilms.series;

public class JSONEpisode {
    String Title;
    String Released;
    int episode;
    String imdbRating;
    String imdbID;

    public JSONEpisode(String title, String released, int episode, String imdbRating, String imdbID) {
        Title = title;
        Released = released;
        this.episode = episode;
        this.imdbRating = imdbRating;
        this.imdbID = imdbID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getReleased() {
        return Released;
    }

    public void setReleased(String released) {
        Released = released;
    }

    public int getEpisode() {
        return episode;
    }

    public void setEpisode(int episode) {
        this.episode = episode;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    @Override
    public String toString() {
        return "JSONEpisode{" +
                "Title='" + Title + '\'' +
                ", Released='" + Released + '\'' +
                ", episode=" + episode +
                ", imdbRating='" + imdbRating + '\'' +
                ", imdbID='" + imdbID + '\'' +
                '}';
    }
}
