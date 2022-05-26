package com.example.showtrack.ui.srs.seriesrecycler;

import com.example.showtrack.data.model.serie.Serie;
import com.example.showtrack.data.model.recycler.RecyclerSerie;

import java.util.ArrayList;

public interface SeriesContract {

    interface View extends OnRepositoryRecyclerSerieFragmentCallback {

    }

    interface Presenter {
        void cargarSeriesRv();

        void onDestroy();
    }

    interface Repository {
        void cargarSeriesRv(OnRepositoryRecyclerSerieFragmentCallback callback);
    }

    interface OnInteractorListener extends OnRepositoryRecyclerSerieFragmentCallback {}

    interface OnRepositoryRecyclerSerieFragmentCallback {
        void onSuccessCargarSeriesRv(ArrayList<RecyclerSerie> rvList);
    }

    interface OnRepositorySeriesFragmentCallback {
        void onSuccessCargarSeries(ArrayList<Serie> seriesList);
    }
}
