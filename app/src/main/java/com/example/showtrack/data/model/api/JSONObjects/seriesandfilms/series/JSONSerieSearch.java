package com.example.showtrack.data.model.api.JSONObjects.seriesandfilms.series;


import java.util.List;

public class JSONSerieSearch {
    List<JSONSerieSearchSerie> Search;

    public JSONSerieSearch(List<JSONSerieSearchSerie> search) {
        this.Search = search;
    }

    public List<JSONSerieSearchSerie> getSearch() {
        return Search;
    }

    public void setSearch(List<JSONSerieSearchSerie> search) {
        this.Search = search;
    }

    @Override
    public String toString() {
        return "JSONSerieSearch{" +
                "search=" + Search +
                '}';
    }
}
