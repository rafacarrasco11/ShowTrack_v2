package com.example.showtrack.ui.srs.seriegenre;

import com.example.showtrack.data.model.serie.Serie;
import com.example.showtrack.data.repository.SerieRepository;

import java.util.ArrayList;

public class SerieGenreInteractor implements SerieGenreContract.OnSerieGenreCallback{

    private SerieGenreContract.OnInteractorListener listener;

    public SerieGenreInteractor(SerieGenreContract.OnInteractorListener listener) {
        this.listener = listener;
    }

    @Override
    public void onSuccessCargarSeriesRvLeft(ArrayList<Serie> rvList) {
        this.listener.onSuccessCargarSeriesRvLeft(rvList);
    }

    @Override
    public void onSuccessCargarSeriesRvRight(ArrayList<Serie> rvList) {
        this.listener.onSuccessCargarSeriesRvRight(rvList);
    }

    public void cargarSeriesRvLeft(String genre) {
        SerieRepository.getInstance().cargarSeriesRvLeft(genre, this);
    }

    public void cargarSeriesRvRight(String genre) {
        SerieRepository.getInstance().cargarSeriesRvRight(genre, this);
    }
}
