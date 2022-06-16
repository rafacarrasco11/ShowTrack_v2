package com.example.showtrack.ui.flm.filmsearch;

import com.example.showtrack.R;
import com.example.showtrack.data.model.Film;
import com.example.showtrack.data.repository.FilmRepository;
import com.example.showtrack.ui.ShowTrackApplication;

import java.util.ArrayList;

public class FilmSearchInteractor implements FilmSearchContract.OnFilmSearchCallback{

    private final FilmSearchContract.OnInteractorListener listener;

    public FilmSearchInteractor(FilmSearchContract.OnInteractorListener listener) {
        this.listener = listener;
    }

    public void search(String searchText) {
        if (searchText.length() == 0)
            listener.onSearchTextEmptyError();
        else
            FilmRepository.getInstance().search(searchText, this);
    }

    @Override
    public void onSuccessSearchFilm(ArrayList<Film> rvList) {
        if (rvList.size() > 0)
            listener.onSuccessSearchFilm(rvList);
        else
            onFailureSearchFilm(ShowTrackApplication.getContext().getString(R.string.searchFailure));

    }

    @Override
    public void onFailureSearchFilm(String message) {
        listener.onFailureSearchFilm(message);
    }
}
