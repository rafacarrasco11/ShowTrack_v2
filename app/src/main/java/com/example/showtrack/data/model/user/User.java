package com.example.showtrack.data.model.user;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.showtrack.data.model.Film;
import com.example.showtrack.data.model.Genres;
import com.example.showtrack.data.model.serie.Serie;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
public class User {
    public static final String TAG = "user";

    @PrimaryKey(autoGenerate = true)
    int id;
    String username;

    String email;
    String genreOne;
    String genreTwo;
    String genreThree;

    @Ignore
    public User(String username) {
        this.username = username;
        //myStats = new ArrayList<>();
        generateStats();
    }

    public User(int id, String username, String email, String genreOne, String genreTwo, String genreThree) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.genreOne = genreOne;
        this.genreTwo = genreTwo;
        this.genreThree = genreThree;
    }

    @Ignore
    public User() {
    }

    public void generateStats() {
        /*myStats.add(new Stat("Weeks Watched",3));
        myStats.add(new Stat("Days Watched",3 * 7));
        myStats.add(new Stat("Hours Watched",3 * 7 * 24));
        myStats.add(new Stat("Minutes Watched",3 * 7 * 24 * 60));
        myStats.add(new Stat("Seconds Watched",3 * 7 * 24 * 60 * 60));*/
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
}
