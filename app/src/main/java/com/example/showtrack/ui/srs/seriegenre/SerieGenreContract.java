package com.example.showtrack.ui.srs.seriegenre;

import com.example.showtrack.data.model.serie.Serie;

import java.util.ArrayList;

public interface SerieGenreContract {
    interface View extends SerieGenreContract.OnSerieGenreCallback {

    }

    interface Presenter {
        void cargarSeriesRvLeft(String genre);
        void cargarSeriesRvRight(String genre);
        void onDestroy();
    }

    interface Repository {
        void cargarSeriesRvLeft(String genre, SerieGenreContract.OnSerieGenreCallback callback);
        void cargarSeriesRvRight(String genre, SerieGenreContract.OnSerieGenreCallback callback);
    }

    interface OnInteractorListener extends SerieGenreContract.OnSerieGenreCallback {

    }

    interface OnSerieGenreCallback {
        void onSuccessCargarSeriesRvLeft(ArrayList<Serie> rvList);
        void onSuccessCargarSeriesRvRight(ArrayList<Serie> rvList);
    }
}
