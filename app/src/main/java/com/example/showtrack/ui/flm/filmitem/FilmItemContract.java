package com.example.showtrack.ui.flm.filmitem;

import com.example.showtrack.data.model.Film;
import com.example.showtrack.ui.flm.filmgenre.FilmGenreContract;

import java.util.ArrayList;

public interface FilmItemContract {

    interface View extends OnFilmItemCallback {

    }

    interface Presenter {
        void addFilm(Film film);
        void removeFilm(Film film);
        void onDestroy();
    }

    interface Repository {
        void addFilm(Film film, OnFilmItemCallback callback);
        void removeFilm(Film film);
    }

    interface OnInteractorListener extends OnFilmItemCallback {

    }

    interface OnFilmItemCallback {
        void onSuccessAddFilm(Film film);
        void onSuccessRemoveFilm(Film film);
    }

}
