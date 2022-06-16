package com.example.showtrack.ui.srs.serieitem;

import com.example.showtrack.data.model.serie.Season;
import com.example.showtrack.data.model.serie.Serie;
import com.example.showtrack.data.repository.SerieRepository;
import com.example.showtrack.data.repository.UserRepository;

import java.util.List;

public class SerieItemInteractor implements SerieItemContract.OnSerieItemCallback{

    private final SerieItemContract.OnInteractorListener listener;

    public SerieItemInteractor(SerieItemContract.OnInteractorListener listener) {
        this.listener = listener;
    }

    public void addSerie(Serie serie) {
        UserRepository.getInstance().addSerie(serie, this);
    }

    public void removeSerie(Serie serie) {
        UserRepository.getInstance().removeSerie(serie, this);
    }


    @Override
    public void onSuccessAddSerie(String message) {
        listener.onSuccessAddSerie(message);
    }

    @Override
    public void onSuccessRemoveSerie(String message) {
        listener.onSuccessRemoveSerie(message);
    }

}
