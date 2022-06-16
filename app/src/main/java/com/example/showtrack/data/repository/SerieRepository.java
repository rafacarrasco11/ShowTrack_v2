package com.example.showtrack.data.repository;


import com.example.showtrack.data.model.Genres;
import com.example.showtrack.data.model.Lists;
import com.example.showtrack.data.model.api.APIClasses.APISeries;
import com.example.showtrack.data.model.dao.EpisodeDao;
import com.example.showtrack.data.model.dao.SerieDao;
import com.example.showtrack.data.model.database.ShowTrackDatabase;
import com.example.showtrack.data.model.serie.Episode;
import com.example.showtrack.data.model.serie.Serie;
import com.example.showtrack.data.model.user.User;
import com.example.showtrack.ui.ShowTrackApplication;
import com.example.showtrack.ui.prf.profile.prof.ProfileContract;
import com.example.showtrack.ui.srs.seriegenre.SerieGenreContract;
import com.example.showtrack.ui.srs.seriesearch.SerieSearchContract;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Clase repositorio local de las listas de series que aparecen en la pantalla series.
 *
 * Cada vez que se logea o se carga la pantalla, se cargan en las listas locales de esta clase los resultados de las APIS,
 * asi, si se actualiza la API irann cambaindo las listas que aparecen.
 * En el caso de la lista de series mas valoradas no, pero en las listas por genero que se generan en funcion a nuestras series y peluclas añadidas si.
 *
 * Para los metodos que solicitan las listas se devuelve un callback con el resultado de la operacion (MVP).
 */
public class SerieRepository implements SerieGenreContract.Repository,  SerieSearchContract.Repository, ProfileContract.SeriesRepository {

    private static SerieRepository instance;

    private final ArrayList<Serie> mostPopSeries;
    private final ArrayList<Serie> mostRatedSeries;
    private static ArrayList<Serie> genreThreeSeries;
    private static ArrayList<Serie> genreOneSeries;
    private static ArrayList<Serie> genreTwoSeries;

    private final SerieDao serieDao;
    private final EpisodeDao episodeDao;

    private SerieRepository() {
        mostPopSeries = new ArrayList<>();
        mostRatedSeries = new ArrayList<>();
        genreThreeSeries = new ArrayList<>();
        genreOneSeries = new ArrayList<>();
        genreTwoSeries = new ArrayList<>();

        mostPopSeries.addAll(APISeries.getMostPopSeries());
        mostRatedSeries.addAll(APISeries.getMostRatedSeries());

        this.serieDao = ShowTrackDatabase.getDatabase().serieDao();
        this.episodeDao = ShowTrackDatabase.getDatabase().episodeDao();

        if (mostPopSeries.size() != 0)
            mostPopSeries.remove(0);
    }

    public static SerieRepository getInstance() {
        if (instance == null) {
            instance = new SerieRepository();
        }

        if (ShowTrackApplication.getUserTemp() !=null ) {
            genreOneSeries.clear();
            genreOneSeries.addAll(APISeries.getSeriesByGenre(ShowTrackApplication.getUserTemp().getGenreOne()));
            genreTwoSeries.clear();
            genreTwoSeries.addAll(APISeries.getSeriesByGenre(ShowTrackApplication.getUserTemp().getGenreTwo()));
            genreThreeSeries.clear();
            genreThreeSeries.addAll(APISeries.getSeriesByGenre(ShowTrackApplication.getUserTemp().getGenreThree()));
        }

        return instance;
    }

    public ArrayList<Serie> cargarSeriesByGenre(String genre, int pages) {
        if (genre.equals(ShowTrackApplication.getUserTemp().getGenreOne())) {
            if (genreOneSeries.size() <= pages)
                pages = genreOneSeries.size();
            List<Serie> listTmp =  genreOneSeries.subList(0,pages);
            return new ArrayList<>(listTmp);
        }
        else if (genre.equals(ShowTrackApplication.getUserTemp().getGenreTwo())) {
            if (genreTwoSeries.size() <= pages)
                pages = genreTwoSeries.size();
            List<Serie> listTmp =  genreTwoSeries.subList(0,pages);
            return new ArrayList<>(listTmp);
        }
        else if (genre.equals(ShowTrackApplication.getUserTemp().getGenreThree())) {
            if (genreThreeSeries.size() <= pages)
                pages = genreThreeSeries.size();
            List<Serie> listTmp =  genreThreeSeries.subList(0,pages);
            return new ArrayList<>(listTmp);
        }
        return null;
    }

    public ArrayList<Serie> cargarSeriesByList(String list, int pages) {
        if (list.equals(Lists.most_pop_series.name())) {
            if (mostPopSeries.size() <= pages)
                pages = mostPopSeries.size();
            List<Serie> listTmp = mostPopSeries.subList(0,pages);
            return new ArrayList<>(listTmp);
        }
        if (list.equals(Lists.top_rated_series_250.name())) {
            if (mostRatedSeries.size() <= pages)
                pages = mostRatedSeries.size();
            List<Serie> listTmp = mostRatedSeries.subList(0,pages);
            return new ArrayList<>(listTmp);
        }
        return null;
    }


    @Override
    public void cargarSeriesRvGenre(String genre, SerieGenreContract.OnSerieGenreCallback callback) {
        callback.onSuccessCargarSeriesRv(cargarSeriesByGenre(genre,20));
    }

    @Override
    public void cargarSeriesRvList(String list, SerieGenreContract.OnSerieGenreCallback callback) {
        callback.onSuccessCargarSeriesRv(cargarSeriesByList(list,20));
    }

    @Override
    public void search(String searchText, SerieSearchContract.OnSerieSearchCallback callback) {
        callback.onSuccessSearchSerie((ArrayList<Serie>) APISeries.getSeriesBySearch(searchText));
    }

    //region PROFILE (ROOM)
    @Override
    public void cargarSeriesRv(ProfileContract.OnProfileGenreCallback callback) {
        try {
            List<Serie> list =ShowTrackDatabase.databaseWriteExecutor.submit(() -> serieDao.getUserSeries(ShowTrackApplication.getUserTemp().getId())).get();
            if (list.size() ==0) {
                callback.onCargarSeriesRvNoData();
            } else {
                callback.onSuccessCargarSeriessRv((ArrayList<Serie>) list);
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //endregion


    public boolean isWatched(Serie serie, User userTemp) {
        try {
            List<Serie> series = ShowTrackDatabase.databaseWriteExecutor.submit(() -> serieDao.getSerieUser(userTemp.getId(),serie.getImdbID())).get();
            if (series.size() > 0)
                return true;

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean isEpisodeWatched(Episode episode, User userTemp) {
        try {
            List<Episode> episodes = ShowTrackDatabase.databaseWriteExecutor.submit(() -> episodeDao.getEpisodeUser(userTemp.getId(),episode.getImdbID())).get();
            if (episodes.size() == 1)
                return true;

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return false;
    }

    public ArrayList<Episode> getUserEpisodes() throws ExecutionException, InterruptedException {
        return (ArrayList<Episode>) ShowTrackDatabase.databaseWriteExecutor.submit(() -> episodeDao.getUserEpisodes(ShowTrackApplication.getUserTemp().getId())).get();
    }

    public ArrayList<Serie> getUserSeries() throws ExecutionException, InterruptedException {
        return (ArrayList<Serie>) ShowTrackDatabase.databaseWriteExecutor.submit(() -> serieDao.getUserSeries(ShowTrackApplication.getUserTemp().getId())).get();
    }

}
