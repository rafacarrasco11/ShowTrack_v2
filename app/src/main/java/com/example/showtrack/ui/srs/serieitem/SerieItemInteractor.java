package com.example.showtrack.ui.srs.serieitem;

import com.example.showtrack.data.model.serie.Serie;

public class SerieItemInteractor implements SerieItemContract.OnSerieItemCallback{

    private SerieItemContract.OnInteractorListener listener;

    public SerieItemInteractor(SerieItemContract.OnInteractorListener listener) {
        this.listener = listener;
    }

    public void addSerie(Serie Serie) {
        //
    }

    public void removeSerie(Serie Serie) {
        //
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
