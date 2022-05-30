package com.example.showtrack.data.repository;

import com.example.showtrack.data.model.serie.Serie;
import com.example.showtrack.ui.prf.profile.prof.ProfileContract;
import com.example.showtrack.ui.srs.seriegenre.SerieGenreContract;
import com.example.showtrack.ui.srs.serieitem.SerieItemContract;
import com.example.showtrack.ui.srs.seriesearch.SerieSearchContract;

import java.time.LocalDate;
import java.util.ArrayList;

public class SerieRepository implements SerieGenreContract.Repository, SerieItemContract.Repository, SerieSearchContract.Repository, ProfileContract.SeriesRepository {

    private static SerieRepository instance;
    private ArrayList<Serie> seriesList;

    private SerieRepository() {
        this.seriesList = new ArrayList<>();
        iniSeriesList();
    }

    private void iniSeriesList() {
        this.seriesList.add(new Serie("Accion", LocalDate.now().plusYears(10), "Breaking Bad"));
        this.seriesList.add(new Serie("Accion",LocalDate.now().plusYears(20), "ToyBoy"));
        this.seriesList.add(new Serie("Accion",LocalDate.now().plusYears(14), "Prision Break"));
        this.seriesList.add(new Serie("Aventuras",LocalDate.now().plusYears(15), "Hora de Aventuras"));
        this.seriesList.add(new Serie("Aventuras",LocalDate.now().plusYears(7), "Hora de Aventuras"));
        this.seriesList.add(new Serie("Aventuras",LocalDate.now().plusYears(8), "Hora de Aventuras"));
        this.seriesList.add(new Serie("Aventuras",LocalDate.now().plusYears(10), "Hora de Aventuras"));
        this.seriesList.add(new Serie("Aventuras",LocalDate.now().plusYears(11), "Hora de Aventuras"));
        this.seriesList.add(new Serie("Thriller",LocalDate.now().plusYears(9), "Black Mirror"));
        this.seriesList.add(new Serie("Thriller",LocalDate.now().plusYears(17), "Black Mirror"));
        this.seriesList.add(new Serie("Thriller",LocalDate.now().plusYears(19), "Black Mirror"));
        this.seriesList.add(new Serie("Comedia",LocalDate.now().plusYears(10), "La que se avecina"));
        this.seriesList.add(new Serie("Terror",LocalDate.now().plusYears(19), "The Walking Dead"));
        this.seriesList.add(new Serie("Terror",LocalDate.now().plusYears(7), "The Walking Dead"));
        this.seriesList.add(new Serie("Terror",LocalDate.now().plusYears(3), "The Walking Dead"));
        this.seriesList.add(new Serie("Terror",LocalDate.now().plusYears(2), "The Walking Dead"));
        this.seriesList.add(new Serie("Terror",LocalDate.now().plusYears(1), "The Walking Dead"));
        this.seriesList.add(new Serie("Terror",LocalDate.now().plusYears(11), "The Walking Dead"));
        this.seriesList.add(new Serie("Terror",LocalDate.now().plusYears(10), "The Walking Dead"));
        this.seriesList.add(new Serie("Terror",LocalDate.now().plusYears(25), "The Walking Dead"));


    }

    public static SerieRepository getInstance() {
        if (instance == null) {
            instance = new SerieRepository();
        }

        return instance;
    }

    public ArrayList<Serie> cargarSeriesByGenre(String genre) {
        ArrayList<Serie> SerieListByGenre = new ArrayList<>();
        for (Serie s : seriesList) {
            if (s.getGenre().equals(genre))
                SerieListByGenre.add(s);
        }

        return SerieListByGenre;
    }

    public ArrayList<Serie> cargarSeries(String genre) {
        ArrayList<Serie> serieListByGenre = new ArrayList<>();
        for (Serie s : seriesList) {
            if (s.getGenre().equals(genre))
                serieListByGenre.add(s);
        }

        return serieListByGenre;
    }

    @Override
    public void cargarSeriesRvLeft(String genre, SerieGenreContract.OnSerieGenreCallback callback) {
        callback.onSuccessCargarSeriesRvLeft(cargarSeriesByGenre(genre));
    }

    @Override
    public void cargarSeriesRvRight(String genre, SerieGenreContract.OnSerieGenreCallback callback) {
        callback.onSuccessCargarSeriesRvRight(cargarSeriesByGenre(genre));
    }

    @Override
    public void addSerie(Serie Serie, SerieItemContract.OnSerieItemCallback callback) {

    }

    @Override
    public void removeSerie(Serie Serie, SerieItemContract.OnSerieItemCallback callback) {

    }

    @Override
    public void search(String searchText, SerieSearchContract.OnSerieSearchCallback callback) {

    }

    //region PROFILE (ROOM)
    @Override
    public void cargarSeriesRv(ProfileContract.OnProfileGenreCallback callback) {
        callback.onSuccessCargarSeriessRv(cargarSeriesAlea());
    }

    //endregion

    //TEMPORAL
    private ArrayList<Serie> cargarSeriesAlea() {
        ArrayList<Serie> list = new ArrayList<>();

        for (int i = 1; i < 11; i++) {
            list.add(this.seriesList.get(i));
        }

        return list;
    }
}
