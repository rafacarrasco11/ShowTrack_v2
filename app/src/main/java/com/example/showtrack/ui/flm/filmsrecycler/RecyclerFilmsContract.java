package com.example.showtrack.ui.flm.filmsrecycler;

import com.example.showtrack.data.model.Film;
import com.example.showtrack.data.model.recycler.RecyclerFilm;


import java.util.ArrayList;

public interface RecyclerFilmsContract {

    interface View extends RecyclerFilmsContract.OnRepositoryRecyclerFilmFragmentCallback {

    }

    interface Presenter {
        void cargarFilmsRv();

        void onDestroy();
    }

    interface Repository {
        void cargarFilmsRv(RecyclerFilmsContract.OnRepositoryRecyclerFilmFragmentCallback callback);
    }

    interface OnInteractorListener extends RecyclerFilmsContract.OnRepositoryRecyclerFilmFragmentCallback {}

    interface OnRepositoryRecyclerFilmFragmentCallback {
        void onSuccessCargarFilmsRv(ArrayList<RecyclerFilm> rvList);
    }

    interface OnRepositoryFilmsFragmentCallback {
        void onSuccessCargarFilms(ArrayList<Film> FilmsList);
    }
}
