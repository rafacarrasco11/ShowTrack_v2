package com.example.showtrack.data.repository;

import android.util.Log;

import com.example.showtrack.R;
import com.example.showtrack.data.model.Film;
import com.example.showtrack.data.model.dao.EpisodeDao;
import com.example.showtrack.data.model.dao.FilmDao;
import com.example.showtrack.data.model.dao.SerieDao;
import com.example.showtrack.data.model.dao.UserDao;
import com.example.showtrack.data.model.database.ShowTrackDatabase;
import com.example.showtrack.data.model.serie.Episode;
import com.example.showtrack.data.model.serie.Serie;
import com.example.showtrack.data.model.user.Stat;
import com.example.showtrack.data.model.user.User;
import com.example.showtrack.ui.ShowTrackApplication;
import com.example.showtrack.ui.flm.filmitem.FilmItemContract;
import com.example.showtrack.ui.prf.profile.prof.ProfileContract;
import com.example.showtrack.ui.srs.serieitem.SerieItemContract;

import java.util.ArrayList;

public class UserRepository implements ProfileContract.UserRepository, FilmItemContract.Repository, SerieItemContract.Repository {

    private static UserRepository instance;
    private static UserDao userDao;
    private static FilmDao filmDao;
    private static SerieDao serieDao;
    private static EpisodeDao episodeDao;

    private UserRepository() {

        userDao = ShowTrackDatabase.getDatabase().userDao();
        filmDao = ShowTrackDatabase.getDatabase().filmDao();
        serieDao = ShowTrackDatabase.getDatabase().serieDao();
        episodeDao = ShowTrackDatabase.getDatabase().episodeDao();
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
        film.setUser_id(ShowTrackApplication.getUserTemp().getId());
        ShowTrackDatabase.databaseWriteExecutor.submit(() -> filmDao.delete(film));
        callback.onSuccessRemoveFilm(ShowTrackApplication.context().getString(R.string.removeFilmSucces));
    }

    @Override
    public void addSerie(Serie serie, SerieItemContract.OnSerieItemCallback callback) {
        serie.setUser(ShowTrackApplication.getUserTemp().getId());
        ShowTrackDatabase.databaseWriteExecutor.submit(() -> serieDao.insert(serie));
        callback.onSuccessAddSerie(ShowTrackApplication.context().getString(R.string.addSerieSucces));
    }

    @Override
    public void removeSerie(Serie serie, SerieItemContract.OnSerieItemCallback callback) {
        serie.setUser(ShowTrackApplication.getUserTemp().getId());
        ShowTrackDatabase.databaseWriteExecutor.submit(() -> serieDao.delete(serie));
        callback.onSuccessRemoveSerie(ShowTrackApplication.context().getString(R.string.removeSerieSucces));
    }


    public void addEpisode(Episode episode) {
        episode.setUser_id(ShowTrackApplication.getUserTemp().getId());
        ShowTrackDatabase.databaseWriteExecutor.submit(() -> episodeDao.insert(episode));
    }


    public void removeEpisode(Episode episode) {
        episode.setUser_id(ShowTrackApplication.getUserTemp().getId());
        ShowTrackDatabase.databaseWriteExecutor.submit(() -> episodeDao.delete(episode));
    }
}
