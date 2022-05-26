package com.example.showtrack.ui.flm.filmgenre;

import com.example.showtrack.data.model.Film;
import com.example.showtrack.data.repository.FilmRepository;

import java.util.ArrayList;

public class FilmGenreInteractor implements FilmGenreContract.OnFilmGenreCallback{

    private FilmGenreContract.OnInteractorListener listener;

    public FilmGenreInteractor(FilmGenreContract.OnInteractorListener listener) {
        this.listener = listener;
    }

    @Override
    public void onSuccessCargarFilmsRvLeft(ArrayList<Film> rvList) {
        this.listener.onSuccessCargarFilmsRvLeft(rvList);
    }

    @Override
    public void onSuccessCargarFilmsRvRight(ArrayList<Film> rvList) {
        this.listener.onSuccessCargarFilmsRvRight(rvList);
    }

    public void cargarFilmsRvLeft(String genre) {
        FilmRepository.getInstance().cargarFilmsRvLeft(genre, this);
    }

    public void cargarFilmsRvRight(String genre) {
        FilmRepository.getInstance().cargarFilmsRvRight(genre, this);
    }
}
