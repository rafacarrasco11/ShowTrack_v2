package com.example.showtrack.data.repository;

import android.os.Build;

import com.example.showtrack.data.model.New;
import com.example.showtrack.data.model.api.APIClasses.APINews;
import com.example.showtrack.ui.ShowTrackApplication;
import com.example.showtrack.ui.hm.HomeContract;
import java.util.ArrayList;
import java.util.Locale;


/**
 * Clase repositorio local de las noticias que aparecen en la pantalla home.
 *
 * cada vez que se carga la pantalla Home se cargan las listas.
 *
 * Para los metodos que solicitan las listas se devuielve un callback con el resultado de la operacion (MVP).
 */
public class NewsRepository implements HomeContract.Repository {

    private static NewsRepository instance;
    private static ArrayList<New> newsList;

    private NewsRepository()  {
        newsList = new ArrayList<>();
    }

    public static NewsRepository getInstance()  {
        if (instance == null) {
            instance = new NewsRepository();
        }


        if (Locale.getDefault().getLanguage().equals("en")) {
            newsList.clear();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                newsList.addAll(APINews.getNewsOfTheDay("en"));
            }
        }
        else if (Locale.getDefault().getLanguage().equals("es")) {
            newsList.clear();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                newsList.addAll(APINews.getNewsOfTheDay("es"));
            }
        }


        return instance;
    }


    @Override
    public void cargarRvNews(HomeContract.OnRepositoryHomeFragmentCallback callback) {
        callback.onSuccessCargarRvNews(newsList);
    }
}
