package com.example.showtrack.data.repository.recycler;

import com.example.showtrack.data.model.recycler.RecyclerSerie;
import com.example.showtrack.ui.srs.seriesrecycler.SeriesContract;

import java.util.ArrayList;

public class RecyclerSerieRepository implements SeriesContract.Repository {

    private static RecyclerSerieRepository instance;
    private ArrayList<RecyclerSerie> rvList;

    private RecyclerSerieRepository() {
        this.rvList = new ArrayList<>();
        iniSeriesList();
    }

    private void iniSeriesList() {
        this.rvList.add(new RecyclerSerie("Accion","Accion"));
        this.rvList.add(new RecyclerSerie("Aventuras","Aventuras"));
        this.rvList.add(new RecyclerSerie("Thriller","Thriller"));
        this.rvList.add(new RecyclerSerie("Comedia","Comedia"));
        this.rvList.add(new RecyclerSerie("Terror","Terror"));
    }

    public static RecyclerSerieRepository getInstance() {
        if (instance == null) {
            instance = new RecyclerSerieRepository();
        }

        return instance;
    }

    @Override
    public void cargarSeriesRv(SeriesContract.OnRepositoryRecyclerSerieFragmentCallback callback) {
        callback.onSuccessCargarSeriesRv(this.rvList);
    }
}
