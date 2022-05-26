package com.example.showtrack.ui.flm.filmsrecycler;

import com.example.showtrack.data.model.recycler.RecyclerFilm;

import java.util.ArrayList;

public class RecyclerFilmsFragmentPresenter implements RecyclerFilmsContract.Presenter, RecyclerFilmsContract.OnInteractorListener {

    private RecyclerFilmsFragmentInteractor interactor;
    private RecyclerFilmsContract.View view;

    public RecyclerFilmsFragmentPresenter(RecyclerFilmsContract.View view) {
        this.view = view;
        this.interactor = new RecyclerFilmsFragmentInteractor(this);
    }

    @Override
    public void cargarFilmsRv() {
        this.interactor.cargarFilmsRv();
    }

    @Override
    public void onDestroy() {
        this.view = null;
        this.interactor = null;
    }

    @Override
    public void onSuccessCargarFilmsRv(ArrayList<RecyclerFilm> rvList) {
        this.view.onSuccessCargarFilmsRv(rvList);
    }
}
