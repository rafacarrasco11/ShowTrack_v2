package com.example.showtrack.data.repository;

import com.example.showtrack.data.model.Film;
import com.example.showtrack.data.model.Film;
import com.example.showtrack.ui.flm.filmgenre.FilmGenreContract;

import java.util.ArrayList;

public class FilmRepository implements FilmGenreContract.Repository {
    private static FilmRepository instance;
    private ArrayList<Film> rvList;

    private FilmRepository() {
        this.rvList = new ArrayList<>();
        iniFilmsList();
    }

    private void iniFilmsList() {
        this.rvList.add(new Film("Accion",2009, "Transporter 1"));
        this.rvList.add(new Film("Accion",2017, "Transporter 2"));
        this.rvList.add(new Film("Accion",2004, "Transporter 3"));
        this.rvList.add(new Film("Aventuras",2009, "Uncharted"));
        this.rvList.add(new Film("Aventuras",2017, "Uncharted"));
        this.rvList.add(new Film("Aventuras",1998, "Uncharted"));
        this.rvList.add(new Film("Aventuras",2009, "Uncharted"));
        this.rvList.add(new Film("Aventuras",1998, "Uncharted"));
        this.rvList.add(new Film("Thriller",2017, "Shutter Island"));
        this.rvList.add(new Film("Thriller",2009, "Dont Breathe 2"));
        this.rvList.add(new Film("Thriller",2017, "Dont Breathe 1"));
        this.rvList.add(new Film("Comedia",2004, "Borat"));
        this.rvList.add(new Film("Terror",2009, "Hush"));
        this.rvList.add(new Film("Terror",2009, "Hush"));
        this.rvList.add(new Film("Terror",2009, "Hush"));
        this.rvList.add(new Film("Terror",2009, "Hush"));
        this.rvList.add(new Film("Terror",2009, "Hush"));
    }

    public static FilmRepository getInstance() {
        if (instance == null) {
            instance = new FilmRepository();
        }

        return instance;
    }


    public ArrayList<Film> cargarFilms(String genre) {
        ArrayList<Film> FilmListByGenre = new ArrayList<>();
        for (Film s : rvList) {
            if (s.getGenre().equals(genre))
                FilmListByGenre.add(s);
        }

        return FilmListByGenre;
    }

    @Override
    public void cargarFilmsRvLeft(String genre, FilmGenreContract.OnFilmGenreCallback callback) {
        callback.onSuccessCargarFilmsRvLeft(cargarFilms(genre));
    }

    @Override
    public void cargarFilmsRvRight(String genre, FilmGenreContract.OnFilmGenreCallback callback) {
        callback.onSuccessCargarFilmsRvRight(cargarFilms(genre));
    }
}
