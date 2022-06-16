package com.example.showtrack.data.model.api.APIClasses;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.showtrack.R;
import com.example.showtrack.data.model.New;
import com.example.showtrack.utils.APIUtil;
import com.example.showtrack.data.model.api.JSONObjects.news.JSONNew;
import com.example.showtrack.data.model.api.JSONObjects.news.JSONNews;
import com.example.showtrack.ui.ShowTrackApplication;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * API USADA = https://newsapi.org/
 */

/**
 * En esta clase se gestionan las peticiones a la API de noticias para el apartado de noticias de la pantalla HOME de la aplicacion.
 *
 * El funcionamiento es similar al de las demas clases de APIs, Aqui se genera una url que se pasa po los metodos de la clase APIUtils y devuelve un objeto JSON
 * en forma de string. Entonces este String se pasa a un objeto creado, en este caso New y se usa como entidad.
 *
 * De esta froma se sacan 5 noticias para pelculas series y cine
 */
@RequiresApi(api = Build.VERSION_CODES.O)
public class APINews {

    private static final List<New> newsList = new ArrayList<>();

    private static final String API_KEY = "be1e2b3473c248848654fe44cd7307ad";
    private static final String apiURL = "https://newsapi.org/v2/everything?apiKey=";

    // PARAMETROS
    private static final String searchIn = "searchIn=description";
    private static final String from = "from=" + LocalDate.now().minusWeeks(1).toString();
    private static final String to = "to=" + LocalDate.now().toString();

    private static final String pageSize = "pageSize=5";


    private static String generateURL(String keyword, String lang) {
        String language = "";
        if (lang == "en")
            language = "language=en";
        else if (lang == "es")
            language = "language=es";

        String s = apiURL + API_KEY + "&q=+" + keyword + "&" + searchIn+ "&" + from+ "&" + to+ "&" + language + "&" + pageSize;
        return s;
    }

    /**
     * Devuelve una lista de noticias del dia relacionadas con el mundo del cine.
     */
    public static List<New> getNewsOfTheDay(String lang)  {
        newsList.addAll(getNewsCine(lang));
        newsList.addAll(getNewsSeries(lang));
        newsList.addAll(getNewsPeliculas(lang));

        return newsList;
    }

    private static List<New> getNewsCine(String lang) {
        List<New> newsLCineist = new ArrayList<>();

        JSONNews jsonNews = null;
        try {
            jsonNews = (JSONNews) APIUtil.getInstance().getFromApi(JSONNews.class, generateURL(ShowTrackApplication.context().getString(R.string.keyword_cine_apinews), lang));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (jsonNews.getStatus().equals("ok")) {

            for (JSONNew jsonNew : jsonNews.getArticles()) {
                newsLCineist.add(new New(
                        jsonNew.getSource().getName(),
                        jsonNew.getAuthor(),
                        jsonNew.getTitle(),
                        jsonNew.getDescription(),
                        jsonNew.getUrl(),
                        jsonNew.getUrlToImage(),
                        jsonNew.getPublishedAt(),
                        jsonNew.getContent()
                ));
            }
        }

        return newsLCineist;
    }

    private static List<New> getNewsSeries(String lang) {
        List<New> newsSeriesList = new ArrayList<>();

        JSONNews jsonNews = null;
        try {
            jsonNews = (JSONNews) APIUtil.getInstance().getFromApi(JSONNews.class, generateURL(ShowTrackApplication.context().getString(R.string.keyword_series_apinews), lang));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (jsonNews.getStatus().equals("ok")) {

            for (JSONNew jsonNew : jsonNews.getArticles()) {
                newsSeriesList.add(new New(
                        jsonNew.getSource().getName(),
                        jsonNew.getAuthor(),
                        jsonNew.getTitle(),
                        jsonNew.getDescription(),
                        jsonNew.getUrl(),
                        jsonNew.getUrlToImage(),
                        jsonNew.getPublishedAt(),
                        jsonNew.getContent()
                ));
            }
        }

        return newsSeriesList;
    }

    private static List<New> getNewsPeliculas(String lang) {
        List<New> newsPeliculasList = new ArrayList<>();

        JSONNews jsonNews = null;
        try {
            jsonNews = (JSONNews) APIUtil.getInstance().getFromApi(JSONNews.class, generateURL(ShowTrackApplication.context().getString(R.string.keyword_peliculas_apinews), lang));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (jsonNews.getStatus().equals("ok")) {

            for (JSONNew jsonNew : jsonNews.getArticles()) {
                newsPeliculasList.add(new New(
                        jsonNew.getSource().getName(),
                        jsonNew.getAuthor(),
                        jsonNew.getTitle(),
                        jsonNew.getDescription(),
                        jsonNew.getUrl(),
                        jsonNew.getUrlToImage(),
                        jsonNew.getPublishedAt(),
                        jsonNew.getContent()
                ));
            }
        }

        return newsPeliculasList;
    }
}
