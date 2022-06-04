package com.example.showtrack.data.model.api.JSONObjects.seriesandfilms;

public class JSONTitleText {
    String text;

    public JSONTitleText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "JSONTitleText{" +
                "text='" + text + '\'' +
                '}';
    }
}

