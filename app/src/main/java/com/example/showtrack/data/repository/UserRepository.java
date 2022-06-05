package com.example.showtrack.data.repository;

import android.util.Log;

import com.example.showtrack.R;
import com.example.showtrack.data.model.Film;
import com.example.showtrack.data.model.dao.FilmDao;
import com.example.showtrack.data.model.dao.UserDao;
import com.example.showtrack.data.model.database.ShowTrackDatabase;
import com.example.showtrack.data.model.user.Stat;
import com.example.showtrack.data.model.user.User;
import com.example.showtrack.ui.ShowTrackApplication;
import com.example.showtrack.ui.flm.filmitem.FilmItemContract;
import com.example.showtrack.ui.prf.profile.prof.ProfileContract;

import java.util.ArrayList;

public class UserRepository implements ProfileContract.UserRepository, FilmItemContract.Repository {

    private static UserRepository instance;
    private static UserDao userDao;
    private static FilmDao filmDao;

    private UserRepository() {

        userDao = ShowTrackDatabase.getDatabase().userDao();
        filmDao = ShowTrackDatabase.getDatabase().filmDao();
    }


    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }

        return instance;
    }

    @Override
    public void cargarStatsRv(ProfileContract.OnProfileGenreCallback callback) {
        //callback.onSuccessCargarStatsRv((ArrayList<Stat>) user.getMyStats());
    }

    @Override
    public void addFilm(Film film, FilmItemContract.OnFilmItemCallback callback) {
        film.setUser_id(ShowTrackApplication.getUserTemp().getId());
        ShowTrackDatabase.databaseWriteExecutor.submit(() -> filmDao.insert(film));
        callback.onSuccessAddFilm(ShowTrackApplication.context().getString(R.string.addFilmSucces));
    }

    @Override
    public void removeFilm(Film film, FilmItemContract.OnFilmItemCallback callback) {

    }
}
