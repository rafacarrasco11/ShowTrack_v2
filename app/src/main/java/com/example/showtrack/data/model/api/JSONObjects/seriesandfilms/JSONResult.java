package com.example.showtrack.data.model.api.JSONObjects.seriesandfilms;

public class JSONResult {

    String id; // IMDB ID !!!!!!!!
    JSONPrimaryImage primaryImage;
    JSONTitleType titleType;
    JSONTitleText titleText;
    JSONReleaseYear releaseYear;

    public JSONResult(String imdbID, JSONPrimaryImage primaryImage, JSONTitleType titleType, JSONTitleText titleText, JSONReleaseYear releaseYear) {
        this.id = imdbID;
        this.primaryImage = primaryImage;
        this.titleType = titleType;
        this.titleText = titleText;
        this.releaseYear = releaseYear;
    }

    public String getImdbID() {
        return id;
    }

    public void setImdbID(String imdbID) {
        this.id = imdbID;
    }

    public JSONPrimaryImage getPrimaryImage() {
        return primaryImage;
    }

    public void setPrimaryImage(JSONPrimaryImage primaryImage) {
        this.primaryImage = primaryImage;
    }

    public JSONTitleType getTitleType() {
        return titleType;
    }

    public void setTitleType(JSONTitleType titleType) {
        this.titleType = titleType;
    }

    public JSONTitleText getTitleText() {
        return titleText;
    }

    public void setTitleText(JSONTitleText titleText) {
        this.titleText = titleText;
    }

    public JSONReleaseYear getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(JSONReleaseYear releaseYear) {
        this.releaseYear = releaseYear;
    }

    @Override
    public String toString() {
        return "JSONResult{" +
                "imdbID='" + id + '\'' +
                ", primaryImage=" + primaryImage +
                ", titleType=" + titleType +
                ", titleText=" + titleText +
                ", releaseYear=" + releaseYear +
                '}';
    }
}
