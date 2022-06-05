package com.example.showtrack.ui.flm.filmsrecycler;

import com.example.showtrack.data.model.recycler.RecyclerFilm;
import com.example.showtrack.data.repository.recycler.RecyclerFilmRepository;

import java.util.ArrayList;

public class RecyclerFilmsFragmentInteractor implements RecyclerFilmsContract.OnRepositoryRecyclerFilmFragmentCallback {

    private RecyclerFilmsContract.OnInteractorListener listener;

    public RecyclerFilmsFragmentInteractor(RecyclerFilmsContract.OnInteractorListener listener) {
        this.listener = listener;
    }

    void cargarFilmsRv() {
        RecyclerFilmRepository.getInstance().cargarFilmsRv(this);
    }

    @Override
    public void onSuccessCargarFilmsRv(ArrayList<RecyclerFilm> rvList) {
        listener.onSuccessCargarFilmsRv(rvList);
    }
}
