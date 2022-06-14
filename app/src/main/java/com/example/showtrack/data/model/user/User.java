package com.example.showtrack.data.model.user;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.showtrack.R;
import com.example.showtrack.data.model.Film;
import com.example.showtrack.data.model.serie.Episode;
import com.example.showtrack.data.model.serie.Serie;
import com.example.showtrack.data.repository.FilmRepository;
import com.example.showtrack.data.repository.SerieRepository;
import com.example.showtrack.ui.ShowTrackApplication;
import com.example.showtrack.utils.DrawableUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Clase POJO para la entidad de un USUARIO
 *
 * Esta clase almacena la informacion de un usuario en la base de datos. Tiene las anotaciones de ROOM.
 *
 * Ademas poedmos observar los campo genreOne, genreThree, genreTwo.
 * Estos campos se rellenan cuando el usuario añade series o peliculas y lo que hacen es definir los generos que aparecen en las pantallas ed series y peliculas.
 * Funciona de manera que segun las series y peliculas que hayas añadido, apareceran listas con estos generos en la pantalla. Asi podras descubir en fguncion a tus gustos.
 */
@Entity
public class User implements Serializable {
    public static final String TAG = "user";

    @PrimaryKey(autoGenerate = true)
    int id;
    String username;

    String email;
    String genreOne;
    String genreTwo;
    String genreThree;

    @Ignore
    Bitmap profilePhoto;

    String profilePhotoRoom;


    @Ignore
    Uri uriProfilePhoto;
    @Ignore
    Bitmap backgroundPhoto;

    @Ignore
    List<Stat> myStats;

    @Ignore
    public User(String username) throws ExecutionException, InterruptedException {
        this.username = username;
        this.profilePhoto = ((BitmapDrawable)ShowTrackApplication.context().getDrawable(R.drawable.user_avatar_preview)).getBitmap();

    }

    public User(int id, String username, String email, String genreOne, String genreTwo, String genreThree, String profilePhotoRoom) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.genreOne = genreOne;
        this.genreTwo = genreTwo;
        this.genreThree = genreThree;
        this.profilePhotoRoom = profilePhotoRoom;
    }

    @Ignore
    public User() {
        this.profilePhoto = ((BitmapDrawable)ShowTrackApplication.context().getDrawable(R.drawable.user_avatar_preview)).getBitmap();
    }

    public void generateStats() throws ExecutionException, InterruptedException {
        long stat1 = 0;
        long stat2 = 0;
        long stat3 = 0;
        long stat4 = 0;
        long stat5 = 0;

        ArrayList<Film> userFilms = FilmRepository.getInstance().getUserFilms();
        ArrayList<Episode> userEpisodes = SerieRepository.getInstance().getUserEpisodes();
        ArrayList<Serie> userSeries = SerieRepository.getInstance().getUserSeries();

        myStats = new ArrayList<>();

        for (Film f : userFilms) {
            stat1 += parsetime(f.getTime());
        }
        for (Episode e : userEpisodes) {
            stat1 += parsetime(e.getTime());
        }

        if (stat1 != 0) {
            myStats.add(new Stat(ShowTrackApplication.context().getString(R.string.stats1_tittle), stat1));

            stat2 = stat1 / 60;
            myStats.add(new Stat(ShowTrackApplication.context().getString(R.string.stats2_tittle), stat2));
        }

        stat3 = userFilms.size();
        if (stat3 != 0 ) {
            myStats.add(new Stat(ShowTrackApplication.context().getString(R.string.stats3_tittle),stat3));
        }

        stat4 = userSeries.size();
        if (stat4 != 0 ) {
            myStats.add(new Stat(ShowTrackApplication.context().getString(R.string.stats4_tittle),stat4));
        }

        stat5 = userEpisodes.size();
        if (stat5 != 0 ) {
            myStats.add(new Stat(ShowTrackApplication.context().getString(R.string.stats5_tittle),stat5));
        }

    }

    private long parsetime(String time) {
        char[] chars = new char[0 ];
        if (time != null) {
             chars= time.toUpperCase().toCharArray();
        }
        StringBuilder s = new StringBuilder();

        for (char c : chars) {
            if ((int) c < 65 ||(int) c > 90 )
                s.append(c);
        }


        return Long.parseLong(s.toString().trim());
    }

    public String getProfilePhotoRoom() {
        return profilePhotoRoom;
    }

    public void setProfilePhotoRoom(String profilePhotoRoom) {
        this.profilePhotoRoom = profilePhotoRoom;
    }

    public Uri getUriProfilePhoto() {
        return uriProfilePhoto;
    }

    public void setUriProfilePhoto(Uri uriProfilePhoto) {
        this.uriProfilePhoto = uriProfilePhoto;
    }

    public static String getTAG() {
        return TAG;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Bitmap getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(Bitmap profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public String getGenreOne() {
        return genreOne;
    }

    public void setGenreOne(String genreOne) {
        this.genreOne = genreOne;
    }

    public String getGenreTwo() {
        return genreTwo;
    }

    public void setGenreTwo(String genreTwo) {
        this.genreTwo = genreTwo;
    }

    public String getGenreThree() {
        return genreThree;
    }

    public void setGenreThree(String genreThree) {
        this.genreThree = genreThree;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Stat> getMyStats() {
        return myStats;
    }

    public void setMyStats(List<Stat> myStats) {
        this.myStats = myStats;
    }

    public Bitmap getBackgroundPhoto() {
        return backgroundPhoto;
    }

    public void setBackgroundPhoto(Bitmap backgroundPhoto) {
        this.backgroundPhoto = backgroundPhoto;
    }

}
