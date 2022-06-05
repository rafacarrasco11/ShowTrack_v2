package com.example.showtrack.ui.srs.seriesearch;
import com.example.showtrack.data.model.serie.Serie;

import java.util.ArrayList;

public class SerieSearchPresenter implements SerieSearchContract.Presenter, SerieSearchContract.OnInteractorListener {

    private SerieSearchContract.View view;
    private SerieSearchInteractor interactor;

    public SerieSearchPresenter(SerieSearchContract.View view) {
        this.view = view;
        this.interactor = new SerieSearchInteractor(this);
    }

    @Override
    public void search(String searchText) {
        this.interactor.search(searchText);
    }

    @Override
    public void onDestroy() {
        this.view = null;
        this.interactor = null;
    }

    @Override
    public void onSearchTextEmptyError() {
        this.view.setSearchTextEmptyError();
    }

    @Override
    public void onSuccessSearchSerie(ArrayList<Serie> rvList) {
        this.view.onSuccessSearchSerie(rvList);
    }

    @Override
    public void onFailureSearchSerie(String message) {
        this.view.onFailureSearchSerie(message);
    }
}
