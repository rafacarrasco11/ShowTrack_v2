package com.example.showtrack.ui.srs.seriesrecycler;

import com.example.showtrack.data.model.recycler.RecyclerFilm;
import com.example.showtrack.data.model.recycler.RecyclerSerie;

import java.util.ArrayList;

public class RecyclerSerieFragmentPresenter implements RecyclerSerieContract.Presenter, RecyclerSerieContract.OnInteractorListener {

    private RecyclerSerieFragmentInteractor interactor;
    private RecyclerSerieContract.View view;

    public RecyclerSerieFragmentPresenter(RecyclerSerieContract.View view) {
        this.view = view;
        this.interactor = new RecyclerSerieFragmentInteractor(this);
    }

    @Override
    public void cargarSeriesRv() {
        this.interactor.cargarSeriesRv();
    }

    @Override
    public void onDestroy() {
        this.view = null;
        this.interactor = null;
    }

    @Override
    public void onSuccessCargarSeriesRv(ArrayList<RecyclerSerie> rvList) {
        this.view.onSuccessCargarSeriesRv(rvList);
    }
}
