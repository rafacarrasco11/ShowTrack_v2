package com.example.showtrack.ui.prf.profile.prof;

import com.example.showtrack.data.model.Film;
import com.example.showtrack.data.model.serie.Serie;
import com.example.showtrack.data.model.user.Stat;

import java.util.ArrayList;

public class ProfilePresenter implements ProfileContract.Presenter, ProfileContract.OnInteractorListener {

    private ProfileContract.View view;
    private ProfileInteractor interactor;

    public ProfilePresenter(ProfileContract.View view) {
        this.view = view;
        this.interactor = new ProfileInteractor(this);
    }

    @Override
    public void cargarSeriesRv() {
        this.interactor.cargarSeries();
    }

    @Override
    public void cargarFilmsRv() {
        this.interactor.cargarFilms();
    }

    @Override
    public void cargarStatsRv() {
        this.interactor.cargarStats();
    }

    @Override
    public void onDestroy() {
        this.interactor = null;
        this.view = null;
    }

    @Override
    public void onSuccessCargarSeriessRv(ArrayList<Serie> rvList) {
        this.view.onSuccessCargarSeriessRv(rvList);
    }

    @Override
    public void onSuccessCargarFilmsRv(ArrayList<Film> rvList) {
        this.view.onSuccessCargarFilmsRv(rvList);
    }

    @Override
    public void onCargarSeriesRvNoData() {
        this.view.onCargarSeriesRvNoData();
    }

    @Override
    public void onCargarFilmsRvNoData() {
        this.view.onCargarFilmsRvNoData();
    }

    @Override
    public void onSuccessCargarStatsRv(ArrayList<Stat> rvList) {
        this.view.onSuccessCargarStatsRv(rvList);
    }

    @Override
    public void deleteAllFilms() {
        this.interactor.deleteAllFilms();
    }

    @Override
    public void deleteAllSeries() {
        this.interactor.deleteAllSeries();
    }


    @Override
    public void onSuccessDeleteSeries(String message) {
        this.view.onSuccessDeleteSeries(message);
    }

    @Override
    public void onSuccessDeleteFilms(String message) {
        this.view.onSuccessDeleteFilms(message);
    }
}
