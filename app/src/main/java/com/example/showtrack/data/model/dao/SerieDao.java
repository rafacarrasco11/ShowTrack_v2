package com.example.showtrack.data.model.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.showtrack.data.model.Film;
import com.example.showtrack.data.model.serie.Serie;

import java.util.List;

@Dao
public interface SerieDao {
    @Insert()
    long insert(Serie serie);

    @Update()
    void update(Serie serie);

    @Delete()
    void delete(Serie serie);

    @Query("SELECT * from Serie where user_id=:user_id")
    List<Serie> getUserSeries(int user_id);

    @Query("SELECT * from Serie where user_id=:user_id AND imdbID=:imdbID")
    List<Serie> getSerieUser( int user_id, String imdbID);
}
