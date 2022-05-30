package com.example.showtrack.ui.prf.profile.prof;

import com.example.showtrack.data.model.Film;
import com.example.showtrack.data.model.serie.Serie;
import com.example.showtrack.data.repository.FilmRepository;
import com.example.showtrack.data.repository.SerieRepository;

import java.util.ArrayList;

public class ProfileInteractor implements ProfileContract.OnProfileGenreCallback {

    private ProfileContract.OnInteractorListener listener;

    public ProfileInteractor(ProfileContract.OnInteractorListener listener) {
        this.listener = listener;
    }

    public void cargarFilms() {
        FilmRepository.getInstance().cargarFilmsRv(this);
    }

    public void cargarSeries() {
        SerieRepository.getInstance().cargarSeriesRv(this);
    }

    @Override
    public void onSuccessCargarSeriessRv(ArrayList<Serie> rvList) {
        this.listener.onSuccessCargarSeriessRv(rvList);
    }

    @Override
    public void onSuccessCargarFilmsRv(ArrayList<Film> rvList) {
        this.listener.onSuccessCargarFilmsRv(rvList);
    }
}
