package com.example.showtrack.ui;

import android.app.Application;
import android.content.Context;

import androidx.fragment.app.Fragment;

import com.example.showtrack.data.model.Film;
import com.example.showtrack.data.model.serie.Serie;

public class ShowTrackApplication extends Application {
    private static Context context;

    private static Fragment lastFragment;

    //#region TEMP ITEMS

    private static Film filmTemp;
    private static Serie serieTemp;
    private static String genreTemp;

    //#endregion

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
//        createNotificationChannel();
//        database.create(this);
    }

    public static Context context() { return context;}

    public static Film getFilmTemp() {
        return filmTemp;
    }

    public static void setFilmTemp(Film filmTempp) {
        filmTemp = filmTempp;
    }

    public static Serie getSerieTemp() {
        return serieTemp;
    }

    public static  void setSerieTemp(Serie serieTempp) {
        serieTemp = serieTempp;
    }

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        ShowTrackApplication.context = context;
    }

    public static Fragment getLastFragment() {
        return lastFragment;
    }

    public static void setLastFragment(Fragment lastFragment) {
        ShowTrackApplication.lastFragment = lastFragment;
    }

    public static String getGenreTemp() {
        return genreTemp;
    }

    public static void setGenreTemp(String genreTemp) {
        ShowTrackApplication.genreTemp = genreTemp;
    }
}
