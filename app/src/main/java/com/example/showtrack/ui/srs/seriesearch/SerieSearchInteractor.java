package com.example.showtrack.ui.srs.seriesearch;

import com.example.showtrack.R;
import com.example.showtrack.data.model.serie.Serie;
import com.example.showtrack.data.repository.SerieRepository;
import com.example.showtrack.ui.ShowTrackApplication;

import java.util.ArrayList;

public class SerieSearchInteractor implements SerieSearchContract.OnSerieSearchCallback{

    private SerieSearchContract.OnInteractorListener listener;

    public SerieSearchInteractor(SerieSearchContract.OnInteractorListener listener) {
        this.listener = listener;
    }

    public void search(String searchText) {
        if (searchText.length() == 0)
            listener.onSearchTextEmptyError();
        else
            SerieRepository.getInstance().search(searchText, this);
    }

    @Override
    public void onSuccessSearchSerie(ArrayList<Serie> rvList) {
        if (rvList.size() > 0)
            listener.onSuccessSearchSerie(rvList);
        else
            onFailureSearchSerie(ShowTrackApplication.getContext().getString(R.string.searchFailure));

    }

    @Override
    public void onFailureSearchSerie(String message) {
        listener.onFailureSearchSerie(message);
    }
}
