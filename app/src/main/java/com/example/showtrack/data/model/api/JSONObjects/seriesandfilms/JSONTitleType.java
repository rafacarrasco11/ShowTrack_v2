package com.example.showtrack.data.model.api.JSONObjects.seriesandfilms;

/**
 * Clase POJO para los objetos JSON que deveulve la API de series y peliculas
 */
public class JSONTitleType {
    String text;
    String id;

    public JSONTitleType(String text, String id) {
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
        return "JSONTitleType{" +
                "text='" + text + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
