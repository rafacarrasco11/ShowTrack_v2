package com.example.showtrack.ui.srs.seriegenre;


import com.example.showtrack.data.model.serie.Serie;

import java.util.ArrayList;

public class SerieGenrePresenter implements SerieGenreContract.Presenter, SerieGenreContract.OnInteractorListener{

    private SerieGenreContract.View view;
    private SerieGenreInteractor interactor;

    public SerieGenrePresenter(SerieGenreContract.View view) {
        this.view = view;
        this.interactor = new SerieGenreInteractor(this);
    }

    @Override
    public void cargarSeriesRvLeft(String genre) {
        this.interactor.cargarSeriesRvLeft(genre);
    }

    @Override
    public void cargarSeriesRvRight(String genre) {
        this.interactor.cargarSeriesRvRight(genre);
    }

    @Override
    public void onDestroy() {
        this.view = null;
        this.interactor = null;
    }

    @Override
    public void onSuccessCargarSeriesRvLeft(ArrayList<Serie> rvList) {
        this.view.onSuccessCargarSeriesRvLeft(rvList);
    }

    @Override
    public void onSuccessCargarSeriesRvRight(ArrayList<Serie> rvList) {
        this.view.onSuccessCargarSeriesRvRight(rvList);
    }
}

