package com.example.showtrack.ui.flm.filmgenre;

import com.example.showtrack.data.model.Film;

import java.util.ArrayList;

public class FilmGenrePresenter implements FilmGenreContract.Presenter, FilmGenreContract.OnInteractorListener{

    private FilmGenreContract.View view;
    private FilmGenreInteractor interactor;

    public FilmGenrePresenter(FilmGenreContract.View view) {
        this.view = view;
        this.interactor = new FilmGenreInteractor(this);
    }

    @Override
    public void cargarFilmsRvLeft(String genre) {
        this.interactor.cargarFilmsRvLeft(genre);
    }

    @Override
    public void cargarFilmsRvRight(String genre) {
        this.interactor.cargarFilmsRvRight(genre);
    }

    @Override
    public void onDestroy() {
        this.view = null;
        this.interactor = null;
    }

    @Override
    public void onSuccessCargarFilmsRvLeft(ArrayList<Film> rvList) {
        this.view.onSuccessCargarFilmsRvLeft(rvList);
    }

    @Override
    public void onSuccessCargarFilmsRvRight(ArrayList<Film> rvList) {
        this.view.onSuccessCargarFilmsRvRight(rvList);
    }
}
