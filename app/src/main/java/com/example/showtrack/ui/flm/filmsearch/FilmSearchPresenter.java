package com.example.showtrack.ui.flm.filmsearch;

import com.example.showtrack.data.model.Film;

import java.util.ArrayList;

public class FilmSearchPresenter implements FilmSearchContract.Presenter, FilmSearchContract.OnInteractorListener {

    private FilmSearchContract.View view;
    private FilmSearchInteractor interactor;

    public FilmSearchPresenter(FilmSearchContract.View view) {
        this.view = view;
        this.interactor = new FilmSearchInteractor(this);
    }

    @Override
    public void search(String searchText) {
        this.interactor.search(searchText);
    }

    @Override
    public void onDestroy() {
        this.view = null;
        this.interactor = null;
    }

    @Override
    public void onSearchTextEmptyError() {
        this.view.setSearchTextEmptyError();
    }

    @Override
    public void onSuccessSearchFilm(ArrayList<Film> rvList) {
        this.view.onSuccessSearchFilm(rvList);
    }

    @Override
    public void onFailureSearchFilm(String message) {
        this.view.onFailureSearchFilm(message);
    }
}
