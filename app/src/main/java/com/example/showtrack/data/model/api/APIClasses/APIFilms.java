package com.example.showtrack.data.model.api.APIClasses;

import com.example.showtrack.R;
import com.example.showtrack.data.model.New;
import com.example.showtrack.data.model.api.JSONObjects.news.JSONNew;
import com.example.showtrack.data.model.api.JSONObjects.news.JSONNews;
import com.example.showtrack.ui.ShowTrackApplication;
import com.example.showtrack.utils.APIUtil;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class APIFilms {
    /*private static List<New> newsList = new ArrayList<>();

    private static final String API_KEY = "be1e2b3473c248848654fe44cd7307ad";
    private static final String apiURL = "https://newsapi.org/v2/everything?apiKey=";

    // PARAMETROS
    private static final String searchIn = "searchIn=description";
    private static final String from = "from=" + LocalDate.now().minusWeeks(1).toString();
    private static final String to = "to=" + LocalDate.now().toString();
    private static final String language = "language=es";
    private static final String pageSize = "pageSize=5";


    private static String generateURL(String keyword) {
        String s = apiURL + API_KEY + "&q=+" + keyword + "&" + searchIn+ "&" + from+ "&" + to+ "&" + language+ "&" + pageSize;
        return s;
    }

    *//**
     * Devuelve una lista de noticias del dia relacionadas con el mundo del cine.
     *//*
    public static List<New> getNewsOfTheDay()  {
        newsList.addAll(getNewsCine());

        return newsList;
    }

    private static List<New> getNewsCine() {
        List<New> newsLCineist = new ArrayList<>();

        JSONNews jsonNews = null;
        try {
            jsonNews = (JSONNews) APIUtil.getInstance().getFromApi(JSONNews.class, generateURL(ShowTrackApplication.context().getString(R.string.keyword_cine_apinews)));
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
    }*/
}
