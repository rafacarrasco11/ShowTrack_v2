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
    public void cargarFilmsRvByGenre(String genre) {
        this.interactor.cargarFilmsRvByGenre(genre);
    }

    @Override
    public void cargarFilmsRvByList(String list) {
        this.interactor.cargarFilmsRvByList(list);
    }

    @Override
    public void onDestroy() {
        this.view = null;
        this.interactor = null;
    }

    @Override
    public void onSuccessCargarFilmsRv(ArrayList<Film> rvList) {
        this.view.onSuccessCargarFilmsRv(rvList);
    }

}
