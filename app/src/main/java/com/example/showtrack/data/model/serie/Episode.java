package com.example.showtrack.data.model.serie;

public class Episode {
    int episodeNumber;
    String released;
    String imdbID;
    String title;
    String imdbRating;

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
