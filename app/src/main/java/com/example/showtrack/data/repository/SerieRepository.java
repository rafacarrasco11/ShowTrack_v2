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
        this.rvList.add(new Serie("Accion", LocalDate.now().plusYears(10), "Breaking Bad"));
        this.rvList.add(new Serie("Accion",LocalDate.now().plusYears(20), "ToyBoy"));
        this.rvList.add(new Serie("Accion",LocalDate.now().plusYears(14), "Prision Break"));
        this.rvList.add(new Serie("Aventuras",LocalDate.now().plusYears(15), "Hora de Aventuras"));
        this.rvList.add(new Serie("Aventuras",LocalDate.now().plusYears(7), "Hora de Aventuras"));
        this.rvList.add(new Serie("Aventuras",LocalDate.now().plusYears(8), "Hora de Aventuras"));
        this.rvList.add(new Serie("Aventuras",LocalDate.now().plusYears(10), "Hora de Aventuras"));
        this.rvList.add(new Serie("Aventuras",LocalDate.now().plusYears(11), "Hora de Aventuras"));
        this.rvList.add(new Serie("Thriller",LocalDate.now().plusYears(9), "Black Mirror"));
        this.rvList.add(new Serie("Thriller",LocalDate.now().plusYears(17), "Black Mirror"));
        this.rvList.add(new Serie("Thriller",LocalDate.now().plusYears(19), "Black Mirror"));
        this.rvList.add(new Serie("Comedia",LocalDate.now().plusYears(10), "La que se avecina"));
        this.rvList.add(new Serie("Terror",LocalDate.now().plusYears(19), "The Walking Dead"));
        this.rvList.add(new Serie("Terror",LocalDate.now().plusYears(7), "The Walking Dead"));
        this.rvList.add(new Serie("Terror",LocalDate.now().plusYears(3), "The Walking Dead"));
        this.rvList.add(new Serie("Terror",LocalDate.now().plusYears(2), "The Walking Dead"));
        this.rvList.add(new Serie("Terror",LocalDate.now().plusYears(1), "The Walking Dead"));
        this.rvList.add(new Serie("Terror",LocalDate.now().plusYears(11), "The Walking Dead"));
        this.rvList.add(new Serie("Terror",LocalDate.now().plusYears(10), "The Walking Dead"));
        this.rvList.add(new Serie("Terror",LocalDate.now().plusYears(25), "The Walking Dead"));


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
