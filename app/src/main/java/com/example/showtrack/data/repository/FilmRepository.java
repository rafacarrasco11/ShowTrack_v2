package com.example.showtrack.data.repository;

import android.util.Log;

import com.example.showtrack.data.model.Film;
import com.example.showtrack.data.model.Genres;
import com.example.showtrack.data.model.Lists;
import com.example.showtrack.data.model.api.APIClasses.APIFilms;
import com.example.showtrack.data.model.dao.FilmDao;
import com.example.showtrack.data.model.database.ShowTrackDatabase;
import com.example.showtrack.data.model.user.User;
import com.example.showtrack.ui.ShowTrackApplication;
import com.example.showtrack.ui.flm.filmgenre.FilmGenreContract;
import com.example.showtrack.ui.flm.filmitem.FilmItemContract;
import com.example.showtrack.ui.flm.filmsearch.FilmSearchContract;
import com.example.showtrack.ui.prf.profile.prof.ProfileContract;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class FilmRepository implements FilmGenreContract.Repository, FilmSearchContract.Repository, ProfileContract.FilmsRepository {
    private static FilmRepository instance;

    private ArrayList<Film> mostPopMovies;
    private ArrayList<Film> mostRatedMovies;
    private ArrayList<Film> mostBoxOfficeMovies;
    private ArrayList<Film> genreOneMovies;
    private ArrayList<Film> genreTwoMovies;

    private FilmDao filmDao;

    private FilmRepository() {
        mostPopMovies = new ArrayList<>();
        mostRatedMovies = new ArrayList<>();
        mostBoxOfficeMovies = new ArrayList<>();
        genreOneMovies = new ArrayList<>();
        genreTwoMovies = new ArrayList<>();

        mostPopMovies.addAll(APIFilms.getMostPopFilms());
        mostRatedMovies.addAll(APIFilms.getMostRatedFilms());
        mostBoxOfficeMovies.addAll(APIFilms.getMostBoxOfficeFilms());
        genreOneMovies.addAll(APIFilms.getFilmsByGenre(Genres.Drama.name()));
        genreTwoMovies.addAll(APIFilms.getFilmsByGenre(Genres.Crime.name()));

        this.filmDao = ShowTrackDatabase.getDatabase().filmDao();

        mostPopMovies.remove(0);
    }

    public static FilmRepository getInstance() {
        if (instance == null) {
            instance = new FilmRepository();
        }

        return instance;
    }


    public ArrayList<Film> cargarFilmsByGenre(String genre, int pages) {
        if (genre.equals(Genres.Drama.name())) {
            if (genreOneMovies.size() <= pages)
                pages = genreOneMovies.size();
            List<Film> listTmp = genreOneMovies.subList(0,pages);
            return new ArrayList<>(listTmp);
        }
        else if (genre.equals(Genres.Crime.name())) {
            if (genreTwoMovies.size() <= pages)
                pages = genreTwoMovies.size();
            List<Film> listTmp = genreTwoMovies.subList(0,pages);
            return new ArrayList<>(listTmp);
        }

        return null;

    }


    public ArrayList<Film> cargarFilmsByList(String list, int pages) {
        if (list.equals(Lists.most_pop_movies.name())) {
            if (mostPopMovies.size() <= pages)
                pages = mostPopMovies.size();
             List<Film> listTmp = mostPopMovies.subList(0,pages);
            return new ArrayList<>(listTmp);
        }
        if (list.equals(Lists.top_rated_250.name())) {
            if (mostRatedMovies.size() <= pages)
                pages = mostRatedMovies.size();
            List<Film> listTmp = mostRatedMovies.subList(0,pages);
            return new ArrayList<>(listTmp);
        }
        if (list.equals(Lists.top_boxoffice_200.name())) {
            if (mostBoxOfficeMovies.size() <= pages)
                pages = mostBoxOfficeMovies.size();
            List<Film> listTmp = mostBoxOfficeMovies.subList(0,pages);
            return new ArrayList<>(listTmp);
        }
        return null;
    }

    public void changeFilm(Film film) {
        //filmsList.indexOf(film);
    }


    @Override
    public void cargarFilmsRvByGenre(String genre, FilmGenreContract.OnFilmGenreCallback callback) {
        callback.onSuccessCargarFilmsRv(cargarFilmsByGenre(genre,20));
    }

    @Override
    public void cargarFilmsRvByList(String list, FilmGenreContract.OnFilmGenreCallback callback) {
        callback.onSuccessCargarFilmsRv(cargarFilmsByList(list,20));
    }

    @Override
    public void search(String searchText, FilmSearchContract.OnFilmSearchCallback callback) {
        callback.onSuccessSearchFilm((ArrayList<Film>) APIFilms.getFilmsBySearch(searchText));

    }

    //#region PROFILE (ROOM)

    @Override
    public void cargarFilmsRv(ProfileContract.OnProfileGenreCallback callback) {
        try {
            callback.onSuccessCargarFilmsRv((ArrayList<Film>) ShowTrackDatabase.databaseWriteExecutor.submit(() -> filmDao.getUserFilms(ShowTrackApplication.getUserTemp().getId())).get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //#endregion

    public boolean isWatched(Film film, User userTemp) {
        try {
            List<Film> films = ShowTrackDatabase.databaseWriteExecutor.submit(() -> filmDao.getFilmUser(userTemp.getId(),film.getImdbID())).get();
            if (films.size() == 1)
                return true;

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return false;
    }

}
