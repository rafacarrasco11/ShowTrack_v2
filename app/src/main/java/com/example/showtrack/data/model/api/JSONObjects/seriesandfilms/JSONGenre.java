package com.example.showtrack.data.model.api.JSONObjects.seriesandfilms;

public class JSONGenre {
    String text;
    String id;

    public JSONGenre(String text, String id) {
        this.text = text;
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "JSONGenre{" +
                "text='" + text + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
