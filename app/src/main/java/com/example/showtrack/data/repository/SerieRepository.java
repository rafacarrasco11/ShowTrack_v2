package com.example.showtrack.data.repository;


import com.example.showtrack.data.model.Film;
import com.example.showtrack.data.model.Genres;
import com.example.showtrack.data.model.Lists;
import com.example.showtrack.data.model.api.APIClasses.APISeries;
import com.example.showtrack.data.model.dao.EpisodeDao;
import com.example.showtrack.data.model.dao.SerieDao;
import com.example.showtrack.data.model.database.ShowTrackDatabase;
import com.example.showtrack.data.model.serie.Episode;
import com.example.showtrack.data.model.serie.Season;
import com.example.showtrack.data.model.serie.Serie;
import com.example.showtrack.data.model.user.User;
import com.example.showtrack.ui.ShowTrackApplication;
import com.example.showtrack.ui.prf.profile.prof.ProfileContract;
import com.example.showtrack.ui.srs.seriegenre.SerieGenreContract;
import com.example.showtrack.ui.srs.serieitem.SerieItemContract;
import com.example.showtrack.ui.srs.seriesearch.SerieSearchContract;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class SerieRepository implements SerieGenreContract.Repository,  SerieSearchContract.Repository, ProfileContract.SeriesRepository {

    private static SerieRepository instance;

    private ArrayList<Serie> mostPopSeries;
    private ArrayList<Serie> mostRatedSeries;
    private ArrayList<Serie> genreThreeSeries;
    private ArrayList<Serie> genreOneSeries;
    private ArrayList<Serie> genreTwoSeries;

    private SerieDao serieDao;
    private EpisodeDao episodeDao;

    private SerieRepository() {
        mostPopSeries = new ArrayList<>();
        mostRatedSeries = new ArrayList<>();
        genreThreeSeries = new ArrayList<>();
        genreOneSeries = new ArrayList<>();
        genreTwoSeries = new ArrayList<>();

        mostPopSeries.addAll(APISeries.getMostPopSeries());
        mostRatedSeries.addAll(APISeries.getMostRatedSeries());
        genreOneSeries.addAll(APISeries.getSeriesByGenre(Genres.Western.name()));
        genreTwoSeries.addAll(APISeries.getSeriesByGenre(Genres.History.name()));
        genreThreeSeries.addAll(APISeries.getSeriesByGenre(Genres.Comedy.name()));

        this.serieDao = ShowTrackDatabase.getDatabase().serieDao();
        this.episodeDao = ShowTrackDatabase.getDatabase().episodeDao();

        mostPopSeries.remove(0);
    }

    public static SerieRepository getInstance() {
        if (instance == null) {
            instance = new SerieRepository();
        }



        return instance;
    }

    public ArrayList<Serie> cargarSeriesByGenre(String genre, int pages) {
        if (genre.equals(Genres.Western.name())) {
            if (genreOneSeries.size() <= pages)
                pages = genreOneSeries.size();
            List<Serie> listTmp =  genreOneSeries.subList(0,pages);
            return new ArrayList<>(listTmp);
        }
        else if (genre.equals(Genres.History.name())) {
            if (genreTwoSeries.size() <= pages)
                pages = genreTwoSeries.size();
            List<Serie> listTmp =  genreTwoSeries.subList(0,pages);
            return new ArrayList<>(listTmp);
        }
        else if (genre.equals(Genres.Comedy.name())) {
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
            callback.onSuccessCargarSeriessRv((ArrayList<Serie>) ShowTrackDatabase.databaseWriteExecutor.submit(() -> serieDao.getUserSeries(ShowTrackApplication.getUserTemp().getId())).get());
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
            if (series.size() == 1)
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

}
