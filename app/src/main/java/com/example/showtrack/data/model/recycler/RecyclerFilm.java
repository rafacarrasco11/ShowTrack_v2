package com.example.showtrack.data.model.recycler;

public class RecyclerFilm {
    String genre;
    String tittle;

    public RecyclerFilm(String genre, String tittle) {
        this.genre = genre;
        this.tittle = tittle;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }
}
