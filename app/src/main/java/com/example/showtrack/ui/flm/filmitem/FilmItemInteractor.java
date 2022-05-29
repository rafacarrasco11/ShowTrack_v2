package com.example.showtrack.ui.flm.filmitem;

import com.example.showtrack.data.model.Film;

public class FilmItemInteractor implements FilmItemContract.OnFilmItemCallback{

    private FilmItemContract.OnInteractorListener listener;

    public FilmItemInteractor(FilmItemContract.OnInteractorListener listener) {
        this.listener = listener;
    }

    public void addFilm(Film film) {
        //
    }

    public void removeFIlm(Film film) {
        //
    }


    @Override
    public void onSuccessAddFilm(String message) {
        listener.onSuccessAddFilm(message);
    }

    @Override
    public void onSuccessRemoveFilm(String message) {
        listener.onSuccessRemoveFilm(message);
    }


}
