package com.example.showtrack.ui.srs.seriesearch;

import com.example.showtrack.data.model.serie.Serie;

import java.util.ArrayList;

public interface SerieSearchContract {

    interface View extends SerieSearchContract.OnSerieSearchCallback {
        void setSearchTextEmptyError();

        void showProgress();
        void hideProgress();
    }

    interface Presenter {
        void search(String searchText);
        void onDestroy();
    }

    interface Repository {
        void search(String searchText, SerieSearchContract.OnSerieSearchCallback callback);
    }

    interface OnInteractorListener extends SerieSearchContract.OnSerieSearchCallback {
        void onSearchTextEmptyError();
    }

    interface OnSerieSearchCallback {
        void onSuccessSearchSerie(ArrayList<Serie> rvList);
        void onFailureSearchSerie(String message);
    }
}
