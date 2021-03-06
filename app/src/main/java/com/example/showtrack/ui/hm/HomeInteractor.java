package com.example.showtrack.ui.hm;

import com.example.showtrack.data.model.New;
import com.example.showtrack.data.repository.NewsRepository;

import java.util.ArrayList;

public class HomeInteractor implements HomeContract.OnRepositoryHomeFragmentCallback {

    private HomeContract.OnInteractorListener listener;

    public HomeInteractor(HomeContract.OnInteractorListener listener) {
        this.listener = listener;
    }

    public void cargarNews() {
        NewsRepository.getInstance().cargarRvNews(this);
    }

    @Override
    public void onSuccessCargarRvNews(ArrayList<New> rvList) {
        this.listener.onSuccessCargarRvNews(rvList);
    }
}
