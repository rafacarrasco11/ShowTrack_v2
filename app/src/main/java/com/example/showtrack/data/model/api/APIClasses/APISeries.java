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

/**
 * En esta clase se gestionan las peticiones a las APIS y se devuelven en forma de objeto JAVA.
 * Se compone de diferentes variables constantes donde se almacenan los datos necesarios para acceder a las APIS y algunos parametros no variables (siempre son el mismo)
 *
 * Lo que se hace en esta clase es generar una url que pasa por la clase APIUtils, donde hay metodos que convierten esta url en una respuesta (OkHttp) en formato JSON,
 * este JSON vienen como una String y aqui en esta clase y usando la libreria GSON se convierte a una entidad de la alpicacion, en este caso a una serie.
 * En la clase tambien podemos encontrar otros metodos para obtener informacion o algo en especifico de una serie desde la API.
 *
 * Esta clase contiene metodos los cuales recogen (o no) parametros y devuelven listas de series, asi se cargan las listas que encontramos en el apartado series de
 * la aplicacion o en nuestro perfil
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
        return URL_OMDB + "s=" + search;
    }

    /**
     * Este metodo hace una peticion a Omdb API para rellenar toda la info de una serie o seria ya que esta api contiene mas informacion
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
     * Devuelve las series mas populares.
     **/
    public static List<Serie> getMostPopSeries()  {
        List<Serie> SeriesPopList = new ArrayList<>();

        JSONSeries jsonSeries1 = null;
        try {
            jsonSeries1 = (JSONSeries) APIUtil.getInstance().getFromApi(JSONSeries.class, generateURLByList(Lists.most_pop_series.name()), HOST, API_KEY);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (jsonSeries1 != null) {
            if (!jsonSeries1.getEntries().equals("0")) {
                for (JSONResult result : jsonSeries1.getResults()) {
                    if (result.getPrimaryImage() != null) {
                        Serie s = new Serie();

                        s.setTittle(result.getTitleText().getText());
                        s.setYearReleased(result.getReleaseYear().getYear());
                        s.setImdbID(result.getImdbID());
                        s.setType(result.getTitleType().getText());
                        s.setPoster(result.getPrimaryImage().getUrl());

                        SeriesPopList.add(s);
                    }
                }
            }
        }

        return SeriesPopList;
    }

    /**
     * Devuelve las series mas valoradas.
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
                    Serie s = new Serie();

                    s.setTittle(result.getTitleText().getText());
                    s.setYearReleased(result.getReleaseYear().getYear());
                    s.setImdbID(result.getImdbID());
                    s.setType(result.getTitleType().getText());
                    s.setPoster(result.getPrimaryImage().getUrl());

                    SeriesMRatedList.add(s);
                }
            }
        }

        return SeriesMRatedList;
    }

    /**
     * Obtienes una lista de series segun su genero
     */
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
                        Serie s = new Serie();

                        s.setTittle(result.getTitleText().getText());
                        s.setYearReleased(result.getReleaseYear().getYear());
                        s.setImdbID(result.getImdbID());
                        s.setType(result.getTitleType().getText());
                        s.setPoster(result.getPrimaryImage().getUrl());

                        Series.add(s);
                    }
                }
            }
        }

        return Series;
    }

    /**
     * Este metodo devuelve la serie que le entra por paramteros pero con toda la informacion obtenida de la API
     */
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

    /**
     * Este metodo devuelve toda la info disponible sobre un episodio en concreto
     */
    public static Episode getEpisodeFullInfo(Episode episode) throws IOException {

        Episode e = episode;

        JSONEpisode jsonEpisode = null;

        try {
            jsonEpisode = (JSONEpisode) APIUtil.getInstance().getFromApi(JSONEpisode.class, generateURLByID(e.getImdbID()));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        e.setAgeRestriction(jsonEpisode.getImdbRating());
        e.setTime(jsonEpisode.getRuntime());
        e.setGenre(jsonEpisode.getGenre());
        e.setLanguage(jsonEpisode.getLanguage());
        e.setPlot(jsonEpisode.getPlot());
        e.setCountry(jsonEpisode.getCountry());
        e.setAwards(jsonEpisode.getAwards());
        e.setPoster(jsonEpisode.getPoster());
        e.setDirector(jsonEpisode.getDirector());
        e.setWriters(jsonEpisode.getWriters());
        e.setActors(jsonEpisode.getActors());
        e.setImdbRating(jsonEpisode.getImdbRating());

        return e;
    }

    /**
     * Este metodo devuelve una lista de temporadas cuando le introducimos la serie de la cual queremos sacar las temporadas por parametros
     * @param serie
     * @return
     * @throws IOException
     */
    private static List<Season> getSeasonsBySerie(Serie serie) throws IOException {
        List<Season> seasons = new ArrayList<>();

        for (int i = 0 ; i < serie.getTotalSeasons() ; i++) {

            JSONSearchSeason jsonSearchSeason = (JSONSearchSeason) APIUtil.getInstance().getFromApi(JSONSearchSeason.class, generateURLBySerieAndSeason(serie,i));

            if (jsonSearchSeason != null) {
                Season s = new Season();

                s.setSerieImdbID(serie.getImdbID());
                s.setSerieTittle(serie.getTittle());
                s.setSeasonNumber((i + 1));
                s.setEpisodes(getEpisodes(jsonSearchSeason.getEpisodes()));
                s.setSerie_id(serie.getId());

                seasons.add(s);
            }
        }

        return seasons;

    }

    /**
     * Este metodo nos devuelve los episodios de una temporda
     * @param episodes (OBJETO CLASE JSON)
     * @return
     * @throws IOException
     */
    private static List<Episode> getEpisodes(List<JSONEpisode> episodes) throws IOException {
        List<Episode> ep = new ArrayList<>();

        if (episodes != null) {
            for (JSONEpisode jsonEpisode : episodes) {
                Episode e = new Episode();

                e.setEpisodeNumber(jsonEpisode.getEpisode());
                e.setYearReleased(jsonEpisode.getYearReleased());
                e.setImdbID(jsonEpisode.getImdbID());
                e.setTittle(jsonEpisode.getTitle());
                e.setImdbRating(jsonEpisode.getImdbRating());
                e.setId(jsonEpisode.getImdbID());

                ep.add(e);
            }
        }

        return ep;
    }


    /**
     * Devuelve una lista de series en funciona una busqueda por palabras clave que se pasa como parametro
     * @param search
     * @return
     */
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
                    Serie s = new Serie();

                    s.setTittle(jsonF.getTitle());
                    s.setYearReleased(jsonF.getYear());
                    s.setImdbID(jsonF.getImdbID());
                    s.setType(jsonF.getType());
                    s.setPoster(jsonF.getPoster());

                    series.add(s);
                }

            }
        }

        return series;
    }

    /**
     * Se obtiene una nueva foto para la serie oibtenida de una API diferente
     * @param serie
     * @return
     */
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


