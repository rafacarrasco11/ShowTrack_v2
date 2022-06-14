package com.example.showtrack.data.model.api.JSONObjects.seriesandfilms;

/**
 * Clase POJO para los objetos JSON que deveulve la API de series y peliculas
 */
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

