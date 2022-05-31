package com.example.showtrack.data.model.user;

import com.example.showtrack.data.model.Genres;
import com.example.showtrack.data.model.serie.Serie;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class User {
    public static final String TAG = "user";

    int id;
    String username;

    List<Genres> favGenres;

    List<String> mySeries;
    List<String> myFilms;

    List<Stat> myStats;

    public User(String username) {
        this.username = username;
        myStats = new ArrayList<>();
        generateStats();
    }

    public void generateStats() {
        myStats.add(new Stat("Weeks Watched",3));
        myStats.add(new Stat("Days Watched",3 * 7));
        myStats.add(new Stat("Hours Watched",3 * 7 * 24));
        myStats.add(new Stat("Minutes Watched",3 * 7 * 24 * 60));
        myStats.add(new Stat("Seconds Watched",3 * 7 * 24 * 60 * 60));
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

    public List<Genres> getFavGenres() {
        return favGenres;
    }

    public void setFavGenres(List<Genres> favGenres) {
        this.favGenres = favGenres;
    }

    public List<String> getMySeries() {
        return mySeries;
    }

    public void setMySeries(List<String> mySeries) {
        this.mySeries = mySeries;
    }

    public List<String> getMyFilms() {
        return myFilms;
    }

    public void setMyFilms(List<String> myFilms) {
        this.myFilms = myFilms;
    }

    public List<Stat> getMyStats() {
        return myStats;
    }

    public void setMyStats(List<Stat> myStats) {
        this.myStats = myStats;
    }
}
