package com.example.showtrack.ui.flm.filmsearch;


import com.example.showtrack.data.model.Film;

import java.util.ArrayList;

public interface FilmSearchContract {

    interface View extends FilmSearchContract.OnFilmSearchCallback {
        void setSearchTextEmptyError();

        void showProgress();
        void hideProgress();
    }

    interface Presenter {
        void search(String searchText);

        void onDestroy();
    }

    interface Repository {
        void search(String searchText, OnFilmSearchCallback callback);
    }

    interface OnInteractorListener extends FilmSearchContract.OnFilmSearchCallback {
        void onSearchTextEmptyError();
    }

    interface OnFilmSearchCallback {
        void onSuccessSearchFilm(ArrayList<Film> rvList);
        void onFailureSearchFilm(String message);
    }
}
