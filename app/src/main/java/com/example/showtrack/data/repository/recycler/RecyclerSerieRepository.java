package com.example.showtrack.data.repository.recycler;

import com.example.showtrack.R;
import com.example.showtrack.data.model.Genres;
import com.example.showtrack.data.model.Lists;
import com.example.showtrack.data.model.recycler.RecyclerSerie;
import com.example.showtrack.ui.ShowTrackApplication;
import com.example.showtrack.ui.srs.seriesrecycler.RecyclerSerieContract;

import java.util.ArrayList;

public class RecyclerSerieRepository implements RecyclerSerieContract.Repository {

    private static RecyclerSerieRepository instance;
    private ArrayList<RecyclerSerie> rvList;

    private RecyclerSerieRepository() {
        this.rvList = new ArrayList<>();
        iniSeriesList();
    }

    private void iniSeriesList() {
        this.rvList.add(new RecyclerSerie(ShowTrackApplication.context().getString(R.string.recyclerSerieTittle_mostPopular), Lists.most_pop_series.name()) );
        this.rvList.add(new RecyclerSerie(ShowTrackApplication.context().getString(R.string.recyclerSerieTittle_mostRated), Lists.top_rated_series_250.name()) );
        this.rvList.add(new RecyclerSerie(ShowTrackApplication.context().getString(R.string.recyclerSerieTittle_genre) + Genres.Western.name(), null, Genres.Western.name()));
        this.rvList.add(new RecyclerSerie(ShowTrackApplication.context().getString(R.string.recyclerSerieTittle_genre) + Genres.History.name(), null, Genres.History.name()) );
        this.rvList.add(new RecyclerSerie(ShowTrackApplication.context().getString(R.string.recyclerSerieTittle_genre)+ Genres.Comedy.name(), null, Genres.Comedy.name()) );
    }

    public static RecyclerSerieRepository getInstance() {
        if (instance == null) {
            instance = new RecyclerSerieRepository();
        }

        return instance;
    }

    @Override
    public void cargarSeriesRv(RecyclerSerieContract.OnRepositoryRecyclerSerieFragmentCallback callback) {
        callback.onSuccessCargarSeriesRv(this.rvList);
    }
}
