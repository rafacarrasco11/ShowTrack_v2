package com.example.showtrack.ui.hm;

import com.example.showtrack.data.model.New;

import java.util.ArrayList;

public interface HomeContract {

    interface View extends HomeContract.OnRepositoryHomeFragmentCallback {

    }

    interface Presenter {
        void cargarRvNews();

        void onDestroy();
    }

    interface Repository {
        void cargarRvNews(HomeContract.OnRepositoryHomeFragmentCallback callback);
    }

    interface OnInteractorListener extends HomeContract.OnRepositoryHomeFragmentCallback {}

    interface OnRepositoryHomeFragmentCallback {
        void onSuccessCargarRvNews(ArrayList<New> rvList);
    }

}
