package com.example.showtrack.data.model.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.showtrack.data.model.Film;
import com.example.showtrack.data.model.user.User;

import java.util.List;

@Dao
public interface FilmDao {

    @Insert()
    long insert(Film film);

    @Update()
    void update(Film film);

    @Delete()
    void delete(Film film);

    @Query("SELECT * from Film where user_id=:user_id")
    List<Film> getUserFilms( int user_id);

    @Query("SELECT * from Film where user_id=:user_id AND imdbID=:imdbID")
    List<Film> getFilmUser( int user_id, String imdbID);
}
