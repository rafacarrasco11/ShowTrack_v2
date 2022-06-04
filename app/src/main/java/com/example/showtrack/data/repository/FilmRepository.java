package com.example.showtrack.data.repository;

import android.util.Log;

import com.example.showtrack.data.model.Film;
import com.example.showtrack.data.model.Genres;
import com.example.showtrack.data.model.Lists;
import com.example.showtrack.data.model.api.APIClasses.APIFilms;
import com.example.showtrack.ui.ShowTrackApplication;
import com.example.showtrack.ui.flm.filmgenre.FilmGenreContract;
import com.example.showtrack.ui.flm.filmitem.FilmItemContract;
import com.example.showtrack.ui.flm.filmsearch.FilmSearchContract;
import com.example.showtrack.ui.prf.profile.prof.ProfileContract;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FilmRepository implements FilmGenreContract.Repository, FilmItemContract.Repository, FilmSearchContract.Repository, ProfileContract.FilmsRepository {
    private static FilmRepository instance;

    private ArrayList<Film> mostPopMovies;
    private ArrayList<Film> mostRatedMovies;
    private ArrayList<Film> mostBoxOfficeMovies;
    private ArrayList<Film> genreOneMovies;
    private ArrayList<Film> genreTwoMovies;
    private ArrayList<Film> genreOneMoviesPageTwo;
    private ArrayList<Film> genreTwoMoviesPageTwo;

    private FilmRepository() {
        mostPopMovies = new ArrayList<>();
        mostRatedMovies = new ArrayList<>();
        mostBoxOfficeMovies = new ArrayList<>();
        genreOneMovies = new ArrayList<>();
        genreOneMoviesPageTwo = new ArrayList<>();
        genreTwoMovies = new ArrayList<>();
        genreTwoMoviesPageTwo = new ArrayList<>();

        mostPopMovies.addAll(APIFilms.getMostPopFilms());
        mostRatedMovies.addAll(APIFilms.getMostRatedFilms());
        mostBoxOfficeMovies.addAll(APIFilms.getMostBoxOfficeFilms());
        genreOneMovies.addAll(APIFilms.getFilmsByGenre(Genres.Drama.name()));      // GET USER GENRE ONE
        genreOneMoviesPageTwo.addAll(APIFilms.getFilmsByGenrePageTwo(Genres.Drama.name()));      // GET USER GENRE ONE
        genreTwoMovies.addAll(APIFilms.getFilmsByGenre(Genres.Crime.name()));      // GET USER GENRE TWO
        genreTwoMoviesPageTwo.addAll(APIFilms.getFilmsByGenrePageTwo(Genres.Crime.name()));      // GET USER GENRE TWO
    }

    public static FilmRepository getInstance() {
        if (instance == null) {
            instance = new FilmRepository();
        }

        return instance;
    }


    public ArrayList<Film> cargarFilmsByGenre(String genre) {
        if (genre == ShowTrackApplication.getGenreOneTemp()) {
            return genreOneMovies;
        }
        else if (genre == ShowTrackApplication.getGenreTwoTemp())
            return genreTwoMovies;

        return null;
    }

    public ArrayList<Film> cargarFilmsByGenrePage2(String genre) {
        if (genre == ShowTrackApplication.getGenreOneTemp()) {
            return genreOneMoviesPageTwo;
        }
        else if (genre == ShowTrackApplication.getGenreTwoTemp())
            return genreTwoMoviesPageTwo;

        return null;
    }

    public ArrayList<Film> cargarFilmsByList(String list) {
        if (list.equals(Lists.most_pop_movies.name()))
            return mostPopMovies;
        if (list.equals(Lists.top_rated_250.name()))
            return mostRatedMovies;
        if (list.equals(Lists.top_boxoffice_200.name()))
            return mostBoxOfficeMovies;

        return null;
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
        callback.onSuccessCargarFilmsRvRight(cargarFilmsByGenrePage2(genre));
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
        callback.onSuccessCargarFilmsRv(mostBoxOfficeMovies);
    }

    //#endregion

}
