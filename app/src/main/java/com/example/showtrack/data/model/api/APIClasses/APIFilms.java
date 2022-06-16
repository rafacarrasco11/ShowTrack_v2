package com.example.showtrack.data.model.api.APIClasses;


import com.example.showtrack.data.model.Film;
import com.example.showtrack.data.model.Lists;
import com.example.showtrack.data.model.api.JSONObjects.seriesandfilms.JSONResult;
import com.example.showtrack.data.model.api.JSONObjects.seriesandfilms.films.JSONFilm;
import com.example.showtrack.data.model.api.JSONObjects.seriesandfilms.films.JSONFilmSearch;
import com.example.showtrack.data.model.api.JSONObjects.seriesandfilms.films.JSONFilmSearchFilm;
import com.example.showtrack.data.model.api.JSONObjects.seriesandfilms.films.JSONFilms;
import com.example.showtrack.data.model.dao.FilmDao;
import com.example.showtrack.data.model.dao.UserDao;
import com.example.showtrack.data.model.database.ShowTrackDatabase;
import com.example.showtrack.data.model.serie.Serie;
import com.example.showtrack.data.model.user.User;
import com.example.showtrack.utils.APIUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/***************************************************************
 * ********************   APIS USADAS **************************
 * *************************************************************
 * **************** The OmdB Api *******************************
 * ******** https://www.omdbapi.com/ ***************************
 * *************************************************************
 * **************** Movies Database (RapidApi) *****************
 * ******** https://rapidapi.com/SAdrian/api/moviesdatabase/ ***
 * ****************************************v********************
 * *************************************************************
 * *************************************************************
 * **** RAPID API --> https://rapidapi.com/ ********************
 * *************************************************************
 */

/**
 * En esta clase se gestionan las peticiones a las APIS y se devuelven en forma de objeto JAVA.
 * Se compone de diferentes variables constantes donde se almacenan los datos necesarios para acceder a las APIS y algunos parametros no variables (siempre son el mismo)
 *
 * Lo que se hace en esta clase es generar una url que pasa por la clase APIUtils, donde hay metodos que convierten esta url en una respuesta (OkHttp) en formato JSON,
 * este JSON vienen como una String y aqui en esta clase y usando la libreria GSON se convierte a una entidad de la alpicacion, en este caso a una Pelicula.
 * En la clase tambien podemos encontrar otros metodos para obtener informacion o algo en especifico de una pelicula desde la API.
 *
 * Esta clase contiene metodos los cuales recogen (o no) parametros y devuelven listas de Peliculas, asi se cargan las listas que encontramos en el apartado peliculas de
 * la aplicacion o en nuestro perfil
 */
public class APIFilms {

    private static final String HOST = "moviesdatabase.p.rapidapi.com";
    private static final String API_KEY = "365724d226msh0e5daf87ac95f51p1ede08jsn57250a1f6fe0";
    private static final String URL_MD = "https://moviesdatabase.p.rapidapi.com/titles?";
    private static final String URL_OMDB = "http://www.omdbapi.com/?apikey=c0693bf7&plot=full&type=movie&";

    // PARAMETROS
    private static final String info = "mini_info";
    private static final String limit= "20";
    private static final String page = "1";
    private static final String titleType = "movie";
    private static final String listPops = Lists.most_pop_movies.name();


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
        return URL_OMDB + "s=" + search ;
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
     * Devuelve las peliculas mas populares.
     **/
    public static List<Film> getMostPopFilms()  {
        List<Film> filmsPopList = new ArrayList<>();

        JSONFilms jsonFilms1 = null;
        try {
            jsonFilms1 = (JSONFilms) APIUtil.getInstance().getFromApi(JSONFilms.class, generateURLByList(Lists.most_pop_movies.name()), HOST, API_KEY);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (jsonFilms1 != null) {
            if (!jsonFilms1.getEntries().equals("0")) {
                for (JSONResult result : jsonFilms1.getResults()) {
                    if (result.getPrimaryImage() != null) {
                        Film f = new Film();
                        f.setTittle(result.getTitleText().getText());
                        f.setYearReleased(result.getReleaseYear().getYear());
                        f.setImdbID(result.getImdbID());
                        f.setType(result.getTitleType().getText());
                        f.setPoster(result.getPrimaryImage().getUrl());

                        filmsPopList.add(f);
                    }
                }
            }
        }



        return filmsPopList;
    }

    /**
     * Devuelve las peliculas mas valoradas.
     **/
    public static List<Film> getMostRatedFilms()  {
        List<Film> filmsMRatedList = new ArrayList<>();

        JSONFilms jsonFilms = null;
        try {
            jsonFilms = (JSONFilms) APIUtil.getInstance().getFromApi(JSONFilms.class, generateURLByList(Lists.top_rated_250.name()), HOST, API_KEY);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (jsonFilms != null) {
            for (JSONResult result : jsonFilms.getResults()) {
                if (result.getPrimaryImage() != null) {
                    Film f = new Film();
                    f.setTittle(result.getTitleText().getText());
                    f.setYearReleased(result.getReleaseYear().getYear());
                    f.setImdbID(result.getImdbID());
                    f.setType(result.getTitleType().getText());
                    f.setPoster(result.getPrimaryImage().getUrl());

                    filmsMRatedList.add(f);
                }
            }
        }

        return filmsMRatedList;
    }

    /**
     * Devuelve las peliculas mas taquilleras
     */
    public static List<Film> getMostBoxOfficeFilms() {
        List<Film> filmsMBOfficeList = new ArrayList<>();

        JSONFilms jsonFilms2 = null;
        try {
            jsonFilms2 = (JSONFilms) APIUtil.getInstance().getFromApi(JSONFilms.class, generateURLByList(Lists.top_boxoffice_200.name()), HOST, API_KEY);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (jsonFilms2 != null) {
            if (!jsonFilms2.getEntries().equals("0")) {
                for (JSONResult result : jsonFilms2.getResults()) {
                    if (result.getPrimaryImage() != null) {
                        Film f = new Film();
                        f.setTittle(result.getTitleText().getText());
                        f.setYearReleased(result.getReleaseYear().getYear());
                        f.setImdbID(result.getImdbID());
                        f.setType(result.getTitleType().getText());
                        f.setPoster(result.getPrimaryImage().getUrl());

                        filmsMBOfficeList.add(f);
                    }
                }
            }
        }

        return filmsMBOfficeList;
    }

    /**
     * Devuelve las peliculas mas taquilleras
     */
    public static List<Film>  getFilmsByGenre(String name) {
        List<Film> films = new ArrayList<>();

        JSONFilms jsonFilms3 = null;
        try {
            jsonFilms3 = (JSONFilms) APIUtil.getInstance().getFromApi(JSONFilms.class, generateURLByGenre(name, "1"), HOST, API_KEY);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (jsonFilms3 != null) {
            if (!jsonFilms3.getEntries().equals("0")) {
                for (JSONResult result : jsonFilms3.getResults()) {
                    if (result.getPrimaryImage() != null) {
                        Film f = new Film();
                        f.setTittle(result.getTitleText().getText());
                        f.setYearReleased(result.getReleaseYear().getYear());
                        f.setImdbID(result.getImdbID());
                        f.setType(result.getTitleType().getText());
                        f.setPoster(result.getPrimaryImage().getUrl());

                        films.add(f);
                    }
                }
            }
        }

        return films;
    }

    /**
     * Devuelve la informacion completa (rellena los campos de la pelicula) de una pelicula.
     */
    public static Film getFilmFullInfo(Film film) {

        Film f = film;

        JSONFilm jsonFilm = null;

        try {
            jsonFilm = (JSONFilm) APIUtil.getInstance().getFromApi(JSONFilm.class, generateURLByID(film.getImdbID()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        f.setAgeRestriction(jsonFilm.getRated());
        f.setTime(jsonFilm.getRuntime());
        f.setGenre(jsonFilm.getGenre());
        f.setLanguage(jsonFilm.getLanguage());
        f.setPlot(jsonFilm.getPlot());
        f.setCountry(jsonFilm.getCountry());
        f.setAwards(jsonFilm.getAwards());
        f.setPoster(jsonFilm.getPoster());
        f.setDirector(jsonFilm.getDirector());
        f.setWriters(jsonFilm.getWriter());
        f.setActors(jsonFilm.getActors());
        f.setImdbRating(jsonFilm.getImdbRating());
        f.setType(jsonFilm.getType());
        f.setBoxOffice(jsonFilm.getBoxOffice());

        return f;
    }

    /**
     * Devuelve una lista de peliculas segun palabras especiales como busqueda
     */
    public static List<Film> getFilmsBySearch(String search) {
        List<Film> films = new ArrayList<>();

        JSONFilmSearch jsonFilmSearch = null;
        try {
            jsonFilmSearch = (JSONFilmSearch) APIUtil.getInstance().getFromApi(JSONFilmSearch.class, generateURLBySearch(search));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (jsonFilmSearch.getSearch() != null) {
            for (JSONFilmSearchFilm jsonF : jsonFilmSearch.getSearch()) {
                if (jsonF != null) {
                    Film f = new Film();
                    f.setTittle(jsonF.getTitle());
                    f.setYearReleased(jsonF.getYear());
                    f.setImdbID(jsonF.getImdbID());
                    f.setType(jsonF.getType());
                    f.setPoster(jsonF.getPoster());

                    films.add(f);
                }

            }
        }

        return films;
    }

    /**
     * Devuelve una nueva imagen para la pelicula, buscando en otra API.
     */
    public static String getNewBackground(Film film) {
        JSONFilm jsonFilmSearch = null;

        try {
            jsonFilmSearch = (JSONFilm) APIUtil.getInstance().getFromApi(JSONFilm.class, generateURLByID(film.getImdbID()));
        } catch (IOException e) {
            e.printStackTrace();
        }



        return jsonFilmSearch.getPoster();
    }

}

