package com.example.showtrack.ui.prf.profile.prof;

import com.example.showtrack.data.model.Film;
import com.example.showtrack.data.model.serie.Serie;
import com.example.showtrack.data.model.user.Stat;
import com.example.showtrack.ui.prf.profile.settings.SettingsFragmentContract;

import java.util.ArrayList;

public interface ProfileContract {

    interface View extends OnProfileGenreCallback {

        void showDataFilms();
        void showDataSeries();

        void noDataFilms();
        void noDataSeries();
    }

    interface Presenter {
        void cargarSeriesRv();
        void cargarFilmsRv();

        void cargarStatsRv();

        void deleteAllFilms();
        void deleteAllSeries();

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

        void deleteAllFilms(OnProfileGenreCallback callback);
        void deleteAllSeries(OnProfileGenreCallback callback);

    }

    interface OnInteractorListener extends OnProfileGenreCallback {

    }

    interface OnProfileGenreCallback {
        void onSuccessCargarSeriessRv(ArrayList<Serie> rvList);
        void onSuccessCargarFilmsRv(ArrayList<Film> rvList);
        void onCargarSeriesRvNoData();
        void onCargarFilmsRvNoData();
        void onSuccessCargarStatsRv(ArrayList<Stat> rvList);


        void onSuccessDeleteSeries(String message);
        void onSuccessDeleteFilms(String message);
    }
}
