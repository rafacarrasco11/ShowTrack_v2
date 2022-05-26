package com.example.showtrack.data.repository;

import com.example.showtrack.data.model.serie.Serie;

import java.time.LocalDate;
import java.util.ArrayList;

public class SerieRepository {
    private static SerieRepository instance;
    private ArrayList<Serie> rvList;

    private SerieRepository() {
        this.rvList = new ArrayList<>();
        iniSeriesList();
    }

    private void iniSeriesList() {
        this.rvList.add(new Serie("Accion", LocalDate.now(), "Breaking Bad"));
        this.rvList.add(new Serie("Accion",LocalDate.now(), "ToyBoy"));
        this.rvList.add(new Serie("Accion",LocalDate.now(), "Prision Break"));
        this.rvList.add(new Serie("Aventuras",LocalDate.now(), "Hora de Aventuras"));
        this.rvList.add(new Serie("Aventuras",LocalDate.now(), "Hora de Aventuras"));
        this.rvList.add(new Serie("Aventuras",LocalDate.now(), "Hora de Aventuras"));
        this.rvList.add(new Serie("Aventuras",LocalDate.now(), "Hora de Aventuras"));
        this.rvList.add(new Serie("Aventuras",LocalDate.now(), "Hora de Aventuras"));
        this.rvList.add(new Serie("Thriller",LocalDate.now(), "Black Mirror"));
        this.rvList.add(new Serie("Thriller",LocalDate.now(), "Black Mirror"));
        this.rvList.add(new Serie("Thriller",LocalDate.now(), "Black Mirror"));
        this.rvList.add(new Serie("Comedia",LocalDate.now(), "La que se avecina"));
        this.rvList.add(new Serie("Terror",LocalDate.now(), "The Walking Dead"));
        this.rvList.add(new Serie("Terror",LocalDate.now(), "The Walking Dead"));
        this.rvList.add(new Serie("Terror",LocalDate.now(), "The Walking Dead"));
        this.rvList.add(new Serie("Terror",LocalDate.now(), "The Walking Dead"));
        this.rvList.add(new Serie("Terror",LocalDate.now(), "The Walking Dead"));
        this.rvList.add(new Serie("Terror",LocalDate.now(), "The Walking Dead"));
        this.rvList.add(new Serie("Terror",LocalDate.now(), "The Walking Dead"));
        this.rvList.add(new Serie("Terror",LocalDate.now(), "The Walking Dead"));


    }

    public static SerieRepository getInstance() {
        if (instance == null) {
            instance = new SerieRepository();
        }

        return instance;
    }


    public ArrayList<Serie> cargarSeries(String genre) {
        ArrayList<Serie> serieListByGenre = new ArrayList<>();
        for (Serie s : rvList) {
            if (s.getGenre().equals(genre))
                serieListByGenre.add(s);
        }

        return serieListByGenre;
    }
}
