package com.example.showtrack.data.model.api.APIClasses;


import android.util.Log;

import com.example.showtrack.data.model.Lists;
import com.example.showtrack.data.model.api.JSONObjects.seriesandfilms.JSONResult;
import com.example.showtrack.data.model.api.JSONObjects.seriesandfilms.series.JSONEpisode;
import com.example.showtrack.data.model.api.JSONObjects.seriesandfilms.series.JSONSearchSeason;
import com.example.showtrack.data.model.api.JSONObjects.seriesandfilms.series.JSONSerie;
import com.example.showtrack.data.model.api.JSONObjects.seriesandfilms.series.JSONSerieSearch;
import com.example.showtrack.data.model.api.JSONObjects.seriesandfilms.series.JSONSerieSearchSerie;
import com.example.showtrack.data.model.api.JSONObjects.seriesandfilms.series.JSONSeries;
import com.example.showtrack.data.model.serie.Episode;
import com.example.showtrack.data.model.serie.Season;
import com.example.showtrack.data.model.serie.Serie;
import com.example.showtrack.utils.APIUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/***************************************************************
 * ******************** APIS USADAS ****************************
 * *************************************************************
 * **************** The OmdB Api *******************************
 * ******** https://www.omdbapi.com/ ***************************
 * *************************************************************
 * **************** Movies Database (RapidApi) *****************
 * ****** https://rapidapi.com/SAdrian/api/moviesdatabase/ *****
 * ****************************************v********************
 * *************************************************************
 * *************************************************************
 * ********* RAPID API --> https://rapidapi.com/ ***************
 * *************************************************************
 * *************************************************************
 */
public class APISeries {

    private static final String HOST = "moviesdatabase.p.rapidapi.com";
    private static final String API_KEY = "365724d226msh0e5daf87ac95f51p1ede08jsn57250a1f6fe0";
    private static final String URL_MD = "https://moviesdatabase.p.rapidapi.com/titles?";

    private static final String URL_OMDB = "http://www.omdbapi.com/?apikey=c0693bf7&plot=full&type=series&";

    // PARAMETROS
    private static final String info = "mini_info";
    private static final String limit= "20";
    private static final String page = "1";
    private static final String titleType = "tvSeries";
    private static final String listPops = Lists.most_pop_series.name();

    /**
     * Este metodo hace una peticion a MoviesDatabase API para obtener info basica como id de una peliucla o serie (Busqueda por Genero)
     * @param genre
     * @return
     */
    private static String generateURLByGenre( String genre, String page) {
        return URL_MD + "info=" + info
                + "&limit=" + limit
                + "&page=" + page
                + "&titleType=" + titleType
                + "&genre=" + genre
                + "&list=" + listPops;

    }

    /**
     * Este metodo hace una peticion a MoviesDatabase API para obtener info basica como id de una peliucla o serie (Busqueda por Lista)
     * @param list
     * @return
     */
    private static String generateURLByList(String list) {
        return URL_MD + "info=" + info
                + "&limit=" + limit
                + "&page=" + page
                + "&titleType=" + titleType
                + "&list=" + list;
    }

    /**
     * Este metodo hace una peticion a Omdb API para buscar por palabras clave
     * @param search
     * @return
     */
    private static String generateURLBySearch(String search) {
        Log.d("AAAAAAAAAAAa", URL_OMDB + "s=" + search);
        return URL_OMDB + "s=" + search;
    }

    /**
     * Este metodo hace una peticion a Omdb API para rellenar toda la info de una pelicula o seria ya que esta api contiene mas informacion
     * @param id
     * @return
     */
    private static String generateURLByID(String id) {
        return URL_OMDB + "i=" + id;
    }

    /**
     * Este metodo hace una peticion a Omdb API para rellenar toda la info de una serie en cuanto a sus temporadas y capitulos.
     * @param serie
     * @param i
     * @return
     */
    private static String generateURLBySerieAndSeason(Serie serie, int i) {
        return URL_OMDB + "i=" + serie.getImdbID() + "&Season=" + (i+1);
    }

    /**
     * Devuelve las peliculas mas populares.
     **/
    public static List<Serie> getMostPopSeries()  {
        List<Serie> SeriesPopList = new ArrayList<>();

        JSONSeries jsonSeries1 = null;
        try {
            jsonSeries1 = (JSONSeries) APIUtil.getInstance().getFromApi(JSONSeries.class, generateURLByList(Lists.most_pop_series.name()), HOST, API_KEY);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!jsonSeries1.getEntries().equals("0")) {
            for (JSONResult result : jsonSeries1.getResults()) {
                if (result.getPrimaryImage() != null) {
                    SeriesPopList.add(new Serie(
                            result.getTitleText().getText(),
                            result.getReleaseYear().getYear(),
                            result.getImdbID(),
                            result.getTitleType().getText(),
                            result.getPrimaryImage().getUrl()
                    ));
                }
            }
        }

        return SeriesPopList;
    }

    /**
     * Devuelve las peliculas mas valoradas.
     **/
    public static List<Serie> getMostRatedSeries()  {
        List<Serie> SeriesMRatedList = new ArrayList<>();

        JSONSeries jsonSeries = null;
        try {
            jsonSeries = (JSONSeries) APIUtil.getInstance().getFromApi(JSONSeries.class, generateURLByList(Lists.top_rated_series_250.name()), HOST, API_KEY);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (jsonSeries != null) {
            for (JSONResult result : jsonSeries.getResults()) {
                if (result.getPrimaryImage() != null) {
                    SeriesMRatedList.add(new Serie(
                            result.getTitleText().getText(),
                            result.getReleaseYear().getYear(),
                            result.getImdbID(),
                            result.getTitleType().getText(),
                            result.getPrimaryImage().getUrl()
                    ));
                }
            }
        }

        return SeriesMRatedList;
    }

    public static List<Serie> getSeriesByGenre(String name) {
        List<Serie> Series = new ArrayList<>();

        JSONSeries jsonSeries3 = null;
        try {
            jsonSeries3 = (JSONSeries) APIUtil.getInstance().getFromApi(JSONSeries.class, generateURLByGenre(name, "1"), HOST, API_KEY);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (jsonSeries3 != null) {
            if (!jsonSeries3.getEntries().equals("0")) {
                for (JSONResult result : jsonSeries3.getResults()) {
                    if (result.getPrimaryImage() != null) {
                        Series.add(new Serie(
                                result.getTitleText().getText(),
                                result.getReleaseYear().getYear(),
                                result.getImdbID(),
                                result.getTitleType().getText(),
                                result.getPrimaryImage().getUrl()
                        ));
                    }
                }
            }
        }

        return Series;
    }

    public static Serie getSerieFullInfo(Serie serie) throws IOException {

        Serie f = serie;

        JSONSerie jsonSerie = null;

        try {
            jsonSerie = (JSONSerie) APIUtil.getInstance().getFromApi(JSONSerie.class, generateURLByID(serie.getImdbID()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        f.setAgeRestriction(jsonSerie.getRated());
        f.setTime(jsonSerie.getRuntime());
        f.setGenre(jsonSerie.getGenre());
        f.setLanguage(jsonSerie.getLanguage());
        f.setPlot(jsonSerie.getPlot());
        f.setCountry(jsonSerie.getCountry());
        f.setAwards(jsonSerie.getAwards());
        f.setPoster(jsonSerie.getPoster());
        f.setDirector(jsonSerie.getDirector());
        f.setWriters(jsonSerie.getWriter());
        f.setActors(jsonSerie.getActors());
        f.setImdbRating(jsonSerie.getImdbRating());
        f.setType(jsonSerie.getType());
        f.setTotalSeasons(jsonSerie.getTotalSeasons());

        f.setSeasons(getSeasonsBySerie(serie));

        return f;
    }

    private static List<Season> getSeasonsBySerie(Serie serie) throws IOException {
        List<Season> seasons = new ArrayList<>();

        for (int i = 0 ; i < serie.getTotalSeasons() ; i++) {

            JSONSearchSeason jsonSearchSeason = (JSONSearchSeason) APIUtil.getInstance().getFromApi(JSONSearchSeason.class, generateURLBySerieAndSeason(serie,i));

            if (jsonSearchSeason != null) {
                seasons.add(new Season(
                        serie.getImdbID(),
                        serie.getTittle(),
                        (i+1),
                        getEpisodes(jsonSearchSeason.getEpisodes())
                ));
            }
        }

        return seasons;

    }

    private static List<Episode> getEpisodes(List<JSONEpisode> episodes) {
        List<Episode> ep = new ArrayList<>();

        if (episodes != null) {
            for (JSONEpisode jsonEpisode : episodes) {
                ep.add(new Episode(
                        jsonEpisode.getEpisode(),
                        jsonEpisode.getReleased(),
                        jsonEpisode.getImdbID(),
                        jsonEpisode.getTitle(),
                        jsonEpisode.getImdbRating()
                ));
            }
        }

        return ep;
    }


    public static List<Serie> getSeriesBySearch(String search) {
        List<Serie> series = new ArrayList<>();

        JSONSerieSearch jsonSerieSearch = null;
        try {
            jsonSerieSearch = (JSONSerieSearch) APIUtil.getInstance().getFromApi(JSONSerieSearch.class, generateURLBySearch(search));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (jsonSerieSearch.getSearch() != null) {
            for (JSONSerieSearchSerie jsonF : jsonSerieSearch.getSearch()) {
                if (jsonF != null) {
                    series.add(new Serie(
                            jsonF.getTitle(),
                            jsonF.getYear(),
                            jsonF.getImdbID(),
                            jsonF.getType(),
                            jsonF.getPoster()
                    ));
                }

            }
        }

        return series;
    }

    public static String getNewBackground(Serie serie) {
        JSONSerie jsonSerieSearch = null;

        try {
            jsonSerieSearch = (JSONSerie) APIUtil.getInstance().getFromApi(JSONSerie.class, generateURLByID(serie.getImdbID()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonSerieSearch.getPoster();
    }
}


