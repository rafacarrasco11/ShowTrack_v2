package com.example.showtrack.ui.prf.profile.prof;

import com.example.showtrack.data.model.Film;
import com.example.showtrack.data.model.serie.Serie;
import com.example.showtrack.data.model.user.Stat;

import java.util.ArrayList;

public interface ProfileContract {

    interface View extends OnProfileGenreCallback {

    }

    interface Presenter {
        void cargarSeriesRv();
        void cargarFilmsRv();

        void cargarStatsRv();

        void onDestroy();
    }

    interface FilmsRepository {
        void cargarFilmsRv(OnProfileGenreCallback callback);
    }

    interface SeriesRepository {
        void cargarSeriesRv(OnProfileGenreCallback callback);
    }

    interface UserRepository {
        void cargarStatsRv(OnProfileGenreCallback callback);
    }

    interface OnInteractorListener extends OnProfileGenreCallback {

    }

    interface OnProfileGenreCallback {
        void onSuccessCargarSeriessRv(ArrayList<Serie> rvList);
        void onSuccessCargarFilmsRv(ArrayList<Film> rvList);
        void onSuccessCargarStatsRv(ArrayList<Stat> rvList);
    }
}
