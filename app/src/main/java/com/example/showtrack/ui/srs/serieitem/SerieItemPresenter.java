package com.example.showtrack.ui.srs.serieitem;

import com.example.showtrack.data.model.serie.Serie;

public class SerieItemPresenter implements SerieItemContract.Presenter, SerieItemContract.OnInteractorListener {

    private SerieItemContract.View view;
    private SerieItemInteractor interactor;

    public SerieItemPresenter(SerieItemContract.View view) {
        this.view = view;
        this.interactor = new SerieItemInteractor(this);
    }

    @Override
    public void addSerie(Serie serie) {
        this.interactor.addSerie(serie);
    }

    @Override
    public void removeSerie(Serie serie) {
        this.interactor.removeSerie(serie);
    }

    @Override
    public void onDestroy() {
        this.view = null;
        this.interactor = null;
    }

    @Override
    public void onSuccessAddSerie(String message) {
        this.view.onSuccessAddSerie(message);
    }

    @Override
    public void onSuccessRemoveSerie(String message) {
        this.view.onSuccessRemoveSerie(message);
    }
}

