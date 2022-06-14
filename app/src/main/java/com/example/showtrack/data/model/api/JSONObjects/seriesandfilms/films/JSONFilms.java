package com.example.showtrack.data.model.api.JSONObjects.seriesandfilms.films;

import com.example.showtrack.data.model.api.JSONObjects.seriesandfilms.JSONResult;

import java.util.List;

/**
 * Clase POJO para los objetos JSON que deveulve la API de series y peliculas
 */
public class JSONFilms {
    String page;
    String next;
    String entries;
    List<JSONResult> results;

    public JSONFilms(String page, String next, String entries, List<JSONResult> results) {
        this.page = page;
        this.next = next;
        this.entries = entries;
        this.results = results;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getEntries() {
        return entries;
    }

    public void setEntries(String entries) {
        this.entries = entries;
    }

    public List<JSONResult> getResults() {
        return results;
    }

    public void setResults(List<JSONResult> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "JSONFilms{" +
                "page='" + page + '\'' +
                ", next='" + next + '\'' +
                ", entries='" + entries + '\'' +
                ", results=" + results +
                '}';
    }
}
