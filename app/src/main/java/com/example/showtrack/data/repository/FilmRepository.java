package com.example.showtrack.data.repository;

import com.example.showtrack.data.model.Film;
import com.example.showtrack.ui.flm.filmgenre.FilmGenreContract;
import com.example.showtrack.ui.flm.filmitem.FilmItemContract;
import com.example.showtrack.ui.flm.filmsearch.FilmSearchContract;
import com.example.showtrack.ui.prf.profile.prof.ProfileContract;

import java.time.LocalDate;
import java.util.ArrayList;

public class FilmRepository implements FilmGenreContract.Repository, FilmItemContract.Repository, FilmSearchContract.Repository, ProfileContract.FilmsRepository {
    private static FilmRepository instance;
    private ArrayList<Film> filmsList;

    private FilmRepository() {
        this.filmsList = new ArrayList<>();
        iniFilmsList();
    }

    private void iniFilmsList() {
        this.filmsList.add(new Film("Accion", LocalDate.now().plusYears(10), "Transporter 1"));
        this.filmsList.add(new Film("Accion",LocalDate.now().plusYears(13), "Transporter 2"));
        this.filmsList.add(new Film("Accion",LocalDate.now().plusYears(30), "Transporter 3"));
        this.filmsList.add(new Film("Aventuras",LocalDate.now().plusYears(20), "Uncharted"));
        this.filmsList.add(new Film("Aventuras",LocalDate.now().plusYears(15), "Uncharted"));
        this.filmsList.add(new Film("Aventuras",LocalDate.now().plusYears(7), "Uncharted"));
        this.filmsList.add(new Film("Aventuras",LocalDate.now().plusYears(9), "Uncharted"));
        this.filmsList.add(new Film("Aventuras",LocalDate.now().plusYears(10), "Uncharted"));
        this.filmsList.add(new Film("Thriller",LocalDate.now().plusYears(3), "Shutter Island"));
        this.filmsList.add(new Film("Thriller",LocalDate.now().plusYears(18), "Dont Breathe 2"));
        this.filmsList.add(new Film("Thriller",LocalDate.now().plusYears(11), "Dont Breathe 1"));
        this.filmsList.add(new Film("Comedia",LocalDate.now().plusYears(18), "Borat"));
        this.filmsList.add(new Film("Terror",LocalDate.now().plusYears(25), "Hush"));
        this.filmsList.add(new Film("Terror",LocalDate.now().plusYears(20), "Hush"));
        this.filmsList.add(new Film("Terror",LocalDate.now().plusYears(20), "Hush"));
        this.filmsList.add(new Film("Terror",LocalDate.now().plusYears(50), "Hush"));
        this.filmsList.add(new Film("Terror",LocalDate.now().plusYears(11), "Hush"));
    }

    public static FilmRepository getInstance() {
        if (instance == null) {
            instance = new FilmRepository();
        }

        return instance;
    }


    public ArrayList<Film> cargarFilmsByGenre(String genre) {
        ArrayList<Film> FilmListByGenre = new ArrayList<>();
        for (Film f : filmsList) {
            if (f.getGenre().equals(genre))
                FilmListByGenre.add(f);
        }

        return FilmListByGenre;
    }

    public void changeFilm(Film film) {
        //filmsList.indexOf(film);

    }

    @Override
    public void cargarFilmsRvLeft(String genre, FilmGenreContract.OnFilmGenreCallback callback) {
        callback.onSuccessCargarFilmsRvLeft(cargarFilmsByGenre(genre));
    }

    @Override
    public void cargarFilmsRvRight(String genre, FilmGenreContract.OnFilmGenreCallback callback) {
        callback.onSuccessCargarFilmsRvRight(cargarFilmsByGenre(genre));
    }

    @Override
    public void addFilm(Film film, FilmItemContract.OnFilmItemCallback callback) {

    }

    @Override
    public void removeFilm(Film film, FilmItemContract.OnFilmItemCallback callback) {

    }

    @Override
    public void search(String searchText, FilmSearchContract.OnFilmSearchCallback callback) {

    }

    //#region PROFILE (ROOM)

    @Override
    public void cargarFilmsRv(ProfileContract.OnProfileGenreCallback callback) {
        callback.onSuccessCargarFilmsRv(cargarFilmsAlea());
    }

    //#endregion

    //TEMPORAL
    private ArrayList<Film> cargarFilmsAlea() {
        ArrayList<Film> list = new ArrayList<>();

        for (int i = 1; i < 11; i++) {
            list.add(this.filmsList.get(i));
        }

        return list;
    }
}
