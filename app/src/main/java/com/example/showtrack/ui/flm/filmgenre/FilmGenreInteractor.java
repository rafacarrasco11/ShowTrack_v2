package com.example.showtrack.ui.flm.filmgenre;

import com.example.showtrack.data.model.Film;
import com.example.showtrack.data.repository.FilmRepository;

import java.util.ArrayList;

public class FilmGenreInteractor implements FilmGenreContract.OnFilmGenreCallback{

    private final FilmGenreContract.OnInteractorListener listener;

    public FilmGenreInteractor(FilmGenreContract.OnInteractorListener listener) {
        this.listener = listener;
    }

    @Override
    public void onSuccessCargarFilmsRv(ArrayList<Film> rvList) {
        this.listener.onSuccessCargarFilmsRv(rvList);
    }

    public void cargarFilmsRvByList(String list) {
        FilmRepository.getInstance().cargarFilmsRvByList(list , this);
    }

    public void cargarFilmsRvByGenre(String genre) {
        FilmRepository.getInstance().cargarFilmsRvByGenre(genre , this);
    }
}
