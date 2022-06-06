package com.example.showtrack.ui.srs.serieitem;

import com.example.showtrack.data.model.serie.Episode;
import com.example.showtrack.data.model.serie.Season;
import com.example.showtrack.data.model.serie.Serie;

import java.util.List;

public interface SerieItemContract {

    interface View extends SerieItemContract.OnSerieItemCallback {

    }

    interface Presenter {
        void addSerie(Serie serie);
        void removeSerie(Serie serie);

        void onDestroy();
    }

    interface Repository {
        void addSerie(Serie serie,SerieItemContract.OnSerieItemCallback callback);
        void removeSerie(Serie serie, SerieItemContract.OnSerieItemCallback callback);
    }

    interface OnInteractorListener extends SerieItemContract.OnSerieItemCallback {

    }

    interface OnSerieItemCallback {
        void onSuccessAddSerie(String message);
        void onSuccessRemoveSerie( String message);
    }

    interface OnEpisodeCallback {
        void onSuccessAddEpisode(Episode episode);
        void onSuccessRemoveEpisode(Episode episode);
    }

}

