package com.example.showtrack.data.model.recycler;

public class RecyclerSerie {
    String genre;
    String tittle;
    String list;

    public RecyclerSerie(String tittle, String list) {
        this.tittle = tittle;
        this.list = list;
    }

    public RecyclerSerie(String tittle, String list, String genre) {
        this.tittle = tittle;
        this.genre = genre;
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

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }
}
