package com.example.showtrack.ui.srs.seriegenre;

import com.example.showtrack.data.model.serie.Serie;
import com.example.showtrack.data.repository.SerieRepository;

import java.util.ArrayList;

public class SerieGenreInteractor implements SerieGenreContract.OnSerieGenreCallback{

    private final SerieGenreContract.OnInteractorListener listener;

    public SerieGenreInteractor(SerieGenreContract.OnInteractorListener listener) {
        this.listener = listener;
    }

    @Override
    public void onSuccessCargarSeriesRv(ArrayList<Serie> rvList) {
        this.listener.onSuccessCargarSeriesRv(rvList);
    }

    public void cargarSeriesRvByGenre(String genre) {
        SerieRepository.getInstance().cargarSeriesRvGenre(genre, this);
    }

    public void cargarSeriesRvByList(String list) {
        SerieRepository.getInstance().cargarSeriesRvList(list, this);
    }
}
