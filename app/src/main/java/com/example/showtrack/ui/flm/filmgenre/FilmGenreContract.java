package com.example.showtrack.ui.flm.filmgenre;

import com.example.showtrack.data.model.Film;

import java.util.ArrayList;

public interface FilmGenreContract {

    interface View extends OnFilmGenreCallback {

    }

    interface Presenter {
        void cargarFilmsRvByGenre(String genre);
        void cargarFilmsRvByList(String list);
        void onDestroy();
    }

    interface Repository {
        void cargarFilmsRvByGenre(String genre, FilmGenreContract.OnFilmGenreCallback callback);
        void cargarFilmsRvByList(String list, FilmGenreContract.OnFilmGenreCallback callback);

    }

    interface OnInteractorListener extends OnFilmGenreCallback {

    }

    interface OnFilmGenreCallback {
        void onSuccessCargarFilmsRv(ArrayList<Film> rvList);
    }
}
