package com.example.showtrack.data.model;

import com.example.showtrack.data.model.serie.Serie;

import java.util.List;

public class User {
    public static final String TAG = "user";

    int id;
    String username;
    String password;
    String email;

    List<Genres> favGenres;

    List<String> mySeries;
    List<String> myFilms;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
