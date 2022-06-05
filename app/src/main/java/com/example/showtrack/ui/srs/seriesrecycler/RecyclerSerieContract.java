package com.example.showtrack.ui.srs.seriesrecycler;

import com.example.showtrack.data.model.Film;
import com.example.showtrack.data.model.recycler.RecyclerFilm;
import com.example.showtrack.data.model.recycler.RecyclerSerie;
import com.example.showtrack.data.model.serie.Serie;


import java.util.ArrayList;

public interface RecyclerSerieContract {

    interface View extends RecyclerSerieContract.OnRepositoryRecyclerSerieFragmentCallback {

    }

    interface Presenter {
        void cargarSeriesRv();

        void onDestroy();
    }

    interface Repository {
        void cargarSeriesRv(RecyclerSerieContract.OnRepositoryRecyclerSerieFragmentCallback callback);
    }

    interface OnInteractorListener extends RecyclerSerieContract.OnRepositoryRecyclerSerieFragmentCallback {}

    interface OnRepositoryRecyclerSerieFragmentCallback {
        void onSuccessCargarSeriesRv(ArrayList<RecyclerSerie> rvList);
    }

    interface OnRepositorySeriesFragmentCallback {
        void onSuccessCargarSeries(ArrayList<Serie> seriesList);
    }
}
