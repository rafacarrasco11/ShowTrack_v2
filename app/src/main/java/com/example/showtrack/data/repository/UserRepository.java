package com.example.showtrack.data.repository;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;

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
import com.example.showtrack.ui.prf.profile.settings.SettingsFragmentContract;
import com.example.showtrack.ui.srs.serieitem.SerieItemContract;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


/**
 * Clase repositorio local de las listas de peliculas que aparecen en la pantalla user.
 *
 * en esta clase se realizan las peticiones a la abse de datos para por ejemplo obtener las listas de series y peliculas de
 * la pantalla perfil, que son las propias que ha añadido el usuario.
 *
 * Aqui también se realizan las operaciones para añadir y eliminar series y peliculas
 *
 * Para los metodos que solicitan las listas se devuielve un callback con el resultado de la operacion (MVP).
 */
public class UserRepository implements ProfileContract.UserRepository, FilmItemContract.Repository, SerieItemContract.Repository, SettingsFragmentContract.Repository {

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
        callback.onSuccessCargarStatsRv((ArrayList<Stat>) ShowTrackApplication.getUserTemp().getMyStats());
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

    //region SETTINGS

    @Override
    public void changeName(String name, SettingsFragmentContract.OnSettingsCallback callback) {
        ShowTrackApplication.getUserTemp().setUsername(name);
        callback.onSuccessChangeName(ShowTrackApplication.context().getString(R.string.changeNameSuccess));
    }

    @Override
    public void changePassword(String passwd, String actPasswd, SettingsFragmentContract.OnSettingsCallback callback) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        AuthCredential credential = EmailAuthProvider
                .getCredential(ShowTrackApplication.getUserTemp().getEmail(), actPasswd);

        user.reauthenticate(credential)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            user.updatePassword(passwd).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        callback.onSuccessChangePassword(ShowTrackApplication.context().getString(R.string.succes_ChangePasswd));
                                    } else {
                                        callback.onFailureChangePassword(ShowTrackApplication.context().getString(R.string.failure_ChangePasswd));
                                    }
                                }
                            });
                        } else {
                            callback.onFailureChangePassword(ShowTrackApplication.context().getString(R.string.failure_ChangePasswd));
                        }
                    }
                });
    }

    @Override
    public void changeLanguage(String lang, SettingsFragmentContract.OnSettingsCallback callback) {
        // cambiar idioma
        callback.onSuccessChangeName(ShowTrackApplication.context().getString(R.string.changeNameSuccess));
    }

    @Override
    public void LogOut(SettingsFragmentContract.OnSettingsCallback callback) {
        FirebaseAuth.getInstance().signOut();
        callback.onSuccessLogOut();
    }

    @Override
    public void DeleteAccount(SettingsFragmentContract.OnSettingsCallback callback) {
        FirebaseAuth.getInstance().getCurrentUser().delete();
        ShowTrackDatabase.databaseWriteExecutor.submit(() -> userDao.delete(ShowTrackApplication.getUserTemp()));

        callback.onSuccessDeleteAccount();
    }

    @Override
    public void deleteAllFilms(ProfileContract.OnProfileGenreCallback callback) {
        ShowTrackDatabase.databaseWriteExecutor.submit(() -> filmDao.deleteFilms());

        callback.onSuccessDeleteFilms(ShowTrackApplication.context().getString(R.string.succes_deleteFilms));
    }

    @Override
    public void deleteAllSeries(ProfileContract.OnProfileGenreCallback callback) {
        ShowTrackDatabase.databaseWriteExecutor.submit(() -> episodeDao.deleteEpisodes());
        ShowTrackDatabase.databaseWriteExecutor.submit(() -> serieDao.deleteSeries());
        callback.onSuccessDeleteSeries(ShowTrackApplication.context().getString(R.string.succes_deleteSeries));
    }

    public void setProfilePhoto() {
        ShowTrackDatabase.databaseWriteExecutor.submit(() -> userDao.update(ShowTrackApplication.getUserTemp()));
    }

    public String getProfilePhoto() throws ExecutionException, InterruptedException {
        Future<String> s = ShowTrackDatabase.databaseWriteExecutor.submit(() -> userDao.getUserPhoto());

        return s.get();
    }

    public List<String> getUserSeriesAndFilmsGenres() {
        List<Serie> sereis = new ArrayList<>();
        List<Film> films = new ArrayList<>();
        List<String> genres = new ArrayList<>();
        try {
            sereis = ShowTrackDatabase.databaseWriteExecutor.submit(() -> serieDao.getUserSeries(ShowTrackApplication.getUserTemp().getId())).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            films = ShowTrackDatabase.databaseWriteExecutor.submit(() -> filmDao.getUserFilms(ShowTrackApplication.getUserTemp().getId())).get();

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (sereis != null) {
            for (Serie s : sereis) {
                if (!s.getGenre().contains(","))
                    genres.add(s.getGenre());
            }
        }

        if (films != null) {
            for (Film f : films) {
                if (!f.getGenre().contains(","))
                    genres.add(f.getGenre());
            }
        }


        return genres;
    }

    //endregion
}
