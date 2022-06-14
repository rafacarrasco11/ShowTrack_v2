package com.example.showtrack.data.repository;


import com.example.showtrack.data.model.Film;
import com.example.showtrack.data.model.Lists;
import com.example.showtrack.data.model.api.APIClasses.APIFilms;
import com.example.showtrack.data.model.dao.FilmDao;
import com.example.showtrack.data.model.database.ShowTrackDatabase;
import com.example.showtrack.data.model.user.User;
import com.example.showtrack.ui.ShowTrackApplication;
import com.example.showtrack.ui.flm.filmgenre.FilmGenreContract;
import com.example.showtrack.ui.flm.filmsearch.FilmSearchContract;
import com.example.showtrack.ui.prf.profile.prof.ProfileContract;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


/**
 * Clase repositorio local de las listas de peliculas que aparecen en la pantalla peliculas.
 *
 * Cada vez que se logea o se carga la pantalla, se cargan en las listas locales de esta clase los resultados de las APIS,
 * asi, si se actualiza la API irann cambaindo las listas que aparecen.
 * En el caso de la lista de l=peliculas mas valoradas no, pero en las listas por genero que se generan en funcion a nuestras series y peluclas añadidas si.
 *
 * Para los metodos que solicitan las listas se devuielve un callback con el resultado de la operacion (MVP).
 */
public class FilmRepository implements FilmGenreContract.Repository, FilmSearchContract.Repository, ProfileContract.FilmsRepository {
    private static FilmRepository instance;

    private static ArrayList<Film> mostPopMovies;
    private static ArrayList<Film> mostRatedMovies;
    private static ArrayList<Film> mostBoxOfficeMovies;
    private static ArrayList<Film> genreOneMovies;
    private static ArrayList<Film> genreTwoMovies;

    private static FilmDao filmDao;

    private FilmRepository() {
        mostPopMovies = new ArrayList<>();
        mostRatedMovies = new ArrayList<>();
        mostBoxOfficeMovies = new ArrayList<>();
        genreOneMovies = new ArrayList<>();
        genreTwoMovies = new ArrayList<>();

        mostPopMovies.addAll(APIFilms.getMostPopFilms());
        mostRatedMovies.addAll(APIFilms.getMostRatedFilms());
        mostBoxOfficeMovies.addAll(APIFilms.getMostBoxOfficeFilms());

        filmDao = ShowTrackDatabase.getDatabase().filmDao();


    }

    public static FilmRepository getInstance()  {
        if (instance == null) {
            instance = new FilmRepository();
        }

        if (ShowTrackApplication.getUserTemp() !=null ) {
            genreOneMovies.clear();
            genreOneMovies.addAll(APIFilms.getFilmsByGenre(ShowTrackApplication.getUserTemp().getGenreOne()));
            genreTwoMovies.clear();
            genreTwoMovies.addAll(APIFilms.getFilmsByGenre(ShowTrackApplication.getUserTemp().getGenreTwo()));
            mostPopMovies.clear();
            mostPopMovies.addAll(APIFilms.getMostPopFilms());
            mostRatedMovies.clear();
            mostRatedMovies.addAll(APIFilms.getMostRatedFilms());
            mostBoxOfficeMovies.clear();
            mostBoxOfficeMovies.addAll(APIFilms.getMostBoxOfficeFilms());
        }

        return instance;
    }


    public ArrayList<Film> cargarFilmsByGenre(String genre, int pages) {

        if (genre.equals(ShowTrackApplication.getUserTemp().getGenreOne())) {
            if (genreOneMovies.size() <= pages)
                pages = genreOneMovies.size();
            List<Film> listTmp = genreOneMovies.subList(0,pages);
            return new ArrayList<>(listTmp);
        }
        else if (genre.equals(ShowTrackApplication.getUserTemp().getGenreTwo())) {
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
            ArrayList<Film> list = (ArrayList<Film>) ShowTrackDatabase.databaseWriteExecutor.submit(() -> filmDao.getUserFilms(ShowTrackApplication.getUserTemp().getId())).get();

            if (list.size() ==0) {
                callback.onCargarFilmsRvNoData();
            } else {
                callback.onSuccessCargarFilmsRv(list);
            }
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
            if (films.size() > 0)
                return true;

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return false;
    }

    public ArrayList<Film> getUserFilms() throws ExecutionException, InterruptedException {
        return (ArrayList<Film>) ShowTrackDatabase.databaseWriteExecutor.submit(() -> filmDao.getUserFilms(ShowTrackApplication.getUserTemp().getId())).get();
    }


}
