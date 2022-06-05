package com.example.showtrack.ui.srs.seriegenre;

import com.example.showtrack.data.model.serie.Serie;

import java.util.ArrayList;

public interface SerieGenreContract {
    interface View extends SerieGenreContract.OnSerieGenreCallback {

    }

    interface Presenter {
        void cargarSeriesRvByGenre(String genre);
        void cargarSeriesRvByList(String list);
        void onDestroy();
    }

    interface Repository {
        void cargarSeriesRvGenre(String genre, SerieGenreContract.OnSerieGenreCallback callback);
        void cargarSeriesRvList(String list, SerieGenreContract.OnSerieGenreCallback callback);
    }

    interface OnInteractorListener extends SerieGenreContract.OnSerieGenreCallback {

    }

    interface OnSerieGenreCallback {
        void onSuccessCargarSeriesRv(ArrayList<Serie> rvList);
    }
}
