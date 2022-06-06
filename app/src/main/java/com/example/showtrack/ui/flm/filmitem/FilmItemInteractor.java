package com.example.showtrack.ui.flm.filmitem;

import com.example.showtrack.data.model.Film;
import com.example.showtrack.data.repository.FilmRepository;
import com.example.showtrack.data.repository.UserRepository;

public class FilmItemInteractor implements FilmItemContract.OnFilmItemCallback{

    private FilmItemContract.OnInteractorListener listener;

    public FilmItemInteractor(FilmItemContract.OnInteractorListener listener) {
        this.listener = listener;
    }

    public void addFilm(Film film) {
        UserRepository.getInstance().addFilm(film, this);
    }

    public void removeFIlm(Film film) {
        UserRepository.getInstance().removeFilm(film, this);
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
