package com.example.showtrack.data.model.api.JSONObjects.seriesandfilms.films;

import java.util.List;

public class JSONFilmSearch {

    List<JSONFilmSearchFilm> Search;

    public JSONFilmSearch(List<JSONFilmSearchFilm> search) {
        Search = search;
    }

    public List<JSONFilmSearchFilm> getSearch() {
        return Search;
    }

    public void setSearch(List<JSONFilmSearchFilm> search) {
        Search = search;
    }

    @Override
    public String toString() {
        return "JSONFilmSearch{" +
                "Search=" + Search +
                '}';
    }
}
