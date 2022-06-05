package com.example.showtrack.ui.hm;

import com.example.showtrack.data.model.New;

import java.util.ArrayList;

public class HomePresenter implements HomeContract.Presenter, HomeContract.OnInteractorListener {

    private HomeContract.View view;
    private HomeInteractor interactor;

    public HomePresenter(HomeContract.View view) {
        this.interactor = new HomeInteractor(this);
        this.view = view;
    }

    @Override
    public void cargarRvNews() {
        this.interactor.cargarNews();
    }

    @Override
    public void onDestroy() {
        this.interactor = null;
        this.view = null;
    }

    @Override
    public void onSuccessCargarRvNews(ArrayList<New> rvList) {
        this.view.onSuccessCargarRvNews(rvList);
    }
}
