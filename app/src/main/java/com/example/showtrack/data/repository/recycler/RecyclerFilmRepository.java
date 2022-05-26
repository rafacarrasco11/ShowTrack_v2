package com.example.showtrack.data.repository.recycler;

import com.example.showtrack.data.model.recycler.RecyclerFilm;
import com.example.showtrack.ui.flm.filmsrecycler.RecyclerFilmsContract;

import java.util.ArrayList;

public class RecyclerFilmRepository implements RecyclerFilmsContract.Repository {

    private static RecyclerFilmRepository instance;
    private ArrayList<RecyclerFilm> rvList;

    private RecyclerFilmRepository() {
        this.rvList = new ArrayList<>();
        iniFilmsList();
    }

    private void iniFilmsList() {
        this.rvList.add(new RecyclerFilm("Accion","Accion"));
        this.rvList.add(new RecyclerFilm("Aventuras","Aventuras"));
        this.rvList.add(new RecyclerFilm("Thriller","Thriller"));
        this.rvList.add(new RecyclerFilm("Comedia","Comedia"));
        this.rvList.add(new RecyclerFilm("Terror","Terror"));
    }

    public static RecyclerFilmRepository getInstance() {
        if (instance == null) {
            instance = new RecyclerFilmRepository();
        }

        return instance;
    }

    @Override
    public void cargarFilmsRv(RecyclerFilmsContract.OnRepositoryRecyclerFilmFragmentCallback callback) {
        callback.onSuccessCargarFilmsRv(this.rvList);
    }

}
