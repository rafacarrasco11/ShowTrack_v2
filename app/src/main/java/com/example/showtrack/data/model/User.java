package com.example.showtrack.data.model;

import com.example.showtrack.data.model.serie.Serie;

import java.util.List;

public class User {
    public static final String TAG = "user";

    int id;
    String username;
    String password;


    List<Serie> mySeries;
    List<Film> myFilms;

    public User(int id, String username, String password, List<Serie> mySeries, List<Film> myFilms) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.mySeries = mySeries;
        this.myFilms = myFilms;
    }


}
