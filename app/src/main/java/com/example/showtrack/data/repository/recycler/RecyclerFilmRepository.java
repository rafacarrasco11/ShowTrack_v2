package com.example.showtrack.data.repository.recycler;

import com.example.showtrack.R;
import com.example.showtrack.data.model.Genres;
import com.example.showtrack.data.model.Lists;
import com.example.showtrack.data.model.recycler.RecyclerFilm;
import com.example.showtrack.ui.ShowTrackApplication;
import com.example.showtrack.ui.flm.filmsrecycler.RecyclerFilmsContract;

import java.util.ArrayList;

public class RecyclerFilmRepository implements RecyclerFilmsContract.Repository {

    private static RecyclerFilmRepository instance;
    private final ArrayList<RecyclerFilm> rvList;

    private RecyclerFilmRepository() {
        this.rvList = new ArrayList<>();

    }

    public void iniFilmsList() {
        this.rvList.clear();
        this.rvList.add(new RecyclerFilm(ShowTrackApplication.context().getString(R.string.recyclerFilmsTittle_mostPopular),Lists.most_pop_movies.name()) );
        this.rvList.add(new RecyclerFilm(ShowTrackApplication.context().getString(R.string.recyclerFilmsTittle_topRated), Lists.top_rated_250.name()));
        this.rvList.add(new RecyclerFilm(ShowTrackApplication.context().getString(R.string.recyclerFilmsTittle_topBoxOffice), Lists.top_boxoffice_200.name()));
        this.rvList.add(new RecyclerFilm(ShowTrackApplication.context().getString(R.string.recyclerFilmsTittle) + ShowTrackApplication.getUserTemp().getGenreOne(), null, ShowTrackApplication.getUserTemp().getGenreOne()));
        this.rvList.add(new RecyclerFilm(ShowTrackApplication.context().getString(R.string.recyclerFilmsTittle) + ShowTrackApplication.getUserTemp().getGenreTwo(), null, ShowTrackApplication.getUserTemp().getGenreTwo()));
    }

    public static RecyclerFilmRepository getInstance() {
        if (instance == null) {
            instance = new RecyclerFilmRepository();
        }
        return instance;
    }

    @Override
    public void cargarFilmsRv(RecyclerFilmsContract.OnRepositoryRecyclerFilmFragmentCallback callback) {
        iniFilmsList();
        callback.onSuccessCargarFilmsRv(this.rvList);
    }

}
