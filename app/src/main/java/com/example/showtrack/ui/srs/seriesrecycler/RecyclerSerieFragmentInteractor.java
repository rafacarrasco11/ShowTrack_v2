package com.example.showtrack.ui.srs.seriesrecycler;


import com.example.showtrack.data.model.recycler.RecyclerFilm;
import com.example.showtrack.data.model.recycler.RecyclerSerie;
import com.example.showtrack.data.repository.recycler.RecyclerFilmRepository;
import com.example.showtrack.data.repository.recycler.RecyclerSerieRepository;

import java.util.ArrayList;

public class RecyclerSerieFragmentInteractor implements RecyclerSerieContract.OnRepositoryRecyclerSerieFragmentCallback {

    private RecyclerSerieContract.OnInteractorListener listener;

    public RecyclerSerieFragmentInteractor(RecyclerSerieContract.OnInteractorListener listener) {
        this.listener = listener;
    }

    void cargarSeriesRv() {
        RecyclerSerieRepository.getInstance().cargarSeriesRv(this);
    }

    @Override
    public void onSuccessCargarSeriesRv(ArrayList<RecyclerSerie> rvList) {
        listener.onSuccessCargarSeriesRv(rvList);
    }
}
