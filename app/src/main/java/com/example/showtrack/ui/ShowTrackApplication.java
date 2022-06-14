package com.example.showtrack.ui;

import android.app.Activity;
import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDeepLinkBuilder;

import com.example.showtrack.R;
import com.example.showtrack.data.model.Film;
import com.example.showtrack.data.model.database.ShowTrackDatabase;
import com.example.showtrack.data.model.recycler.RecyclerFilm;
import com.example.showtrack.data.model.recycler.RecyclerSerie;
import com.example.showtrack.data.model.serie.Season;
import com.example.showtrack.data.model.serie.Serie;
import com.example.showtrack.data.model.user.User;
import com.example.showtrack.data.repository.FilmRepository;
import com.example.showtrack.data.repository.SerieRepository;

import java.util.Random;

/**
 * Clase Application de la aplicacion.
 *
 * En la clase se guardan diferentes instancias estaticas de entidades de la aplicacion las cyuales funcionan com memoria temporal.
 */
public class ShowTrackApplication extends Application {
    public static final String IDCHANNEL = "676789";

    private static Context context;

    private static Fragment lastFragment;

    //#region TEMP ITEMS

    private static Film filmTemp;
    private static Serie serieTemp;
    private static RecyclerFilm recyclerFilmTemp;
    private static RecyclerSerie recyclerSerieTemp;
    private static String genreTemp;
    private static Season seasonTemp;
    private static User userTemp;

    public static RecyclerFilm getRecyclerFilmTemp() {
        return recyclerFilmTemp;
    }

    public static void setRecyclerFilmTemp(RecyclerFilm recyclerFilmTemp) {
        ShowTrackApplication.recyclerFilmTemp = recyclerFilmTemp;
    }

    //#endregion

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

        ShowTrackDatabase.create(this);
        createNotificationChannel();
    }

    private void createNotificationChannel() {
        //Se crea una clase Notificationchannel, es necesario que la API sea 26 o mas,
        //porque no se ha incluido en la librería de soporte
        //Si tenemos el minSdk 26 podemos quitar el if

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            int importace = NotificationManager.IMPORTANCE_DEFAULT;
            String nameChannel = getString(R.string.name_channel);
            NotificationChannel notificationChannel = new NotificationChannel(IDCHANNEL, nameChannel, importace);

            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);

            getSystemService(NotificationManager.class).createNotificationChannel(notificationChannel);
        }
    }

    public static void newNotification(Bundle bundle, Activity activity, int destinationId, int smallIcon, String title, String contentText) {

        PendingIntent pendingIntent = new NavDeepLinkBuilder(activity)
                .setGraph(R.navigation.nav_graph)
                .setDestination(destinationId)
                .setArguments(bundle)
                .createPendingIntent();

        Notification.Builder builder = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            builder = new Notification.Builder(activity, ShowTrackApplication.IDCHANNEL)
                    .setSmallIcon(smallIcon)
                    .setAutoCancel(true)
                    .setContentTitle(title)
                    .setContentText(contentText)
                    .setContentIntent(pendingIntent);
        }

        NotificationManager notificationManager = (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(new Random().nextInt(), builder.build());
    }

    public static void newNotification(Activity activity, int destinationId, int smallIcon, String title, String contentText) {

        PendingIntent pendingIntent = new NavDeepLinkBuilder(activity)
                .setGraph(R.navigation.nav_graph)
                .setDestination(destinationId)
                .createPendingIntent();

        Notification.Builder builder = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            builder = new Notification.Builder(activity, ShowTrackApplication.IDCHANNEL)
                    .setSmallIcon(smallIcon)
                    .setAutoCancel(true)
                    .setContentTitle(title)
                    .setContentText(contentText)
                    .setContentIntent(pendingIntent);
        }

        NotificationManager notificationManager = (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(new Random().nextInt(), builder.build());
    }


    public static void newNotification(Activity activity, int smallIcon, String title, String contentText) {

        PendingIntent pendingIntent = new NavDeepLinkBuilder(activity)
                .setGraph(R.navigation.nav_graph)
                .setDestination(R.id.dashboardFragment)
                .createPendingIntent();

        Notification.Builder builder = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            builder = new Notification.Builder(activity, ShowTrackApplication.IDCHANNEL)
                    .setSmallIcon(smallIcon)
                    .setAutoCancel(true)
                    .setContentTitle(title)
                    .setContentText(contentText)
                    .setContentIntent(pendingIntent);
        }

        NotificationManager notificationManager = (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(new Random().nextInt(), builder.build());
    }

    public static Context context() {
        return context;}


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

    public static RecyclerSerie getRecyclerSerieTemp() {
        return recyclerSerieTemp;
    }

    public static void setRecyclerSerieTemp(RecyclerSerie recyclerSerieTemp) {
        ShowTrackApplication.recyclerSerieTemp = recyclerSerieTemp;
    }

    public static Season getSeasonTemp() {
        return seasonTemp;
    }

    public static void setSeasonTemp(Season seasonTemp) {
        ShowTrackApplication.seasonTemp = seasonTemp;
    }

    public static User getUserTemp() {
        return userTemp;
    }

    public static void setUserTemp(User userTemp) {
        ShowTrackApplication.userTemp = userTemp;
    }
}
