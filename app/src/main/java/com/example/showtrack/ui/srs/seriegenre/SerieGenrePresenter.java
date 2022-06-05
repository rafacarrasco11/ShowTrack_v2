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
    public void cargarSeriesRvByGenre(String genre) {
        this.interactor.cargarSeriesRvByGenre(genre);
    }

    @Override
    public void cargarSeriesRvByList(String list) {
        this.interactor.cargarSeriesRvByList(list);
    }

    @Override
    public void onDestroy() {
        this.view = null;
        this.interactor = null;
    }

    @Override
    public void onSuccessCargarSeriesRv(ArrayList<Serie> rvList) {
        this.view.onSuccessCargarSeriesRv(rvList);
    }
}

