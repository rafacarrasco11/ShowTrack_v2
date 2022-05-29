package com.example.showtrack.ui.flm.filmitem;

import com.example.showtrack.data.model.Film;
import com.example.showtrack.ui.flm.filmgenre.FilmGenreContract;

public class FilmItemPresenter implements FilmItemContract.Presenter, FilmItemContract.OnInteractorListener {

    private FilmItemContract.View view;
    private FilmItemInteractor interactor;

    public FilmItemPresenter(FilmItemContract.View view) {
        this.view = view;
        this.interactor = new FilmItemInteractor(this);
    }

    @Override
    public void addFilm(Film film) {
        this.interactor.addFilm(film);
    }

    @Override
    public void removeFilm(Film film) {
        this.interactor.removeFIlm(film);
    }

    @Override
    public void onDestroy() {
        this.view = null;
        this.interactor = null;
    }

    @Override
    public void onSuccessAddFilm(String message) {
        this.view.onSuccessAddFilm(message);
    }

    @Override
    public void onSuccessRemoveFilm(String message) {
        this.view.onSuccessRemoveFilm(message);
    }
}
