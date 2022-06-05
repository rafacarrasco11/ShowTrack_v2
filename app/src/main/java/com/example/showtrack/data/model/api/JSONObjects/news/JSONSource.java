package com.example.showtrack.data.model.api.JSONObjects.news;

public class JSONSource {
    String id;
    String name;

    public JSONSource(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "JSONSource{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
