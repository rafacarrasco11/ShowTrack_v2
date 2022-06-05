package com.example.showtrack.data.model.api.JSONObjects.seriesandfilms;

public class JSONPrimaryImage {
    String width;
    String height;
    String url;

    public JSONPrimaryImage(String width, String height, String url) {
        this.width = width;
        this.height = height;
        this.url = url;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "JSONPrimaryImage{" +
                "width='" + width + '\'' +
                ", height='" + height + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
