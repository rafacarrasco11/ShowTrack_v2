package com.example.showtrack.ui.srs.serieitem;

import com.example.showtrack.data.model.serie.Serie;

public interface SerieItemContract {

    interface View extends SerieItemContract.OnSerieItemCallback {

    }

    interface Presenter {
        void addSerie(Serie Serie);
        void removeSerie(Serie Serie);
        void onDestroy();
    }

    interface Repository {
        void addSerie(Serie Serie,SerieItemContract.OnSerieItemCallback callback);
        void removeSerie(Serie Serie, SerieItemContract.OnSerieItemCallback callback);
    }

    interface OnInteractorListener extends SerieItemContract.OnSerieItemCallback {

    }

    interface OnSerieItemCallback {
        void onSuccessAddSerie(String message);
        void onSuccessRemoveSerie( String message);
    }

}

