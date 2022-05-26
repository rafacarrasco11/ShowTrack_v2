package com.example.showtrack.ui.flm.filmgenre;

import com.example.showtrack.data.model.Film;

import java.util.ArrayList;

public interface FilmGenreContract {

    interface View extends OnFilmGenreCallback {

    }

    interface Presenter {
        void cargarFilmsRvLeft(String genre);
        void cargarFilmsRvRight(String genre);
        void onDestroy();
    }

    interface Repository {
        void cargarFilmsRvLeft(String genre, FilmGenreContract.OnFilmGenreCallback callback);
        void cargarFilmsRvRight(String genre, FilmGenreContract.OnFilmGenreCallback callback);
    }

    interface OnInteractorListener extends OnFilmGenreCallback {

    }

    interface OnFilmGenreCallback {
        void onSuccessCargarFilmsRvLeft(ArrayList<Film> rvList);
        void onSuccessCargarFilmsRvRight(ArrayList<Film> rvList);
    }
}
