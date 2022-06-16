package com.example.showtrack.data.model.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.showtrack.data.model.serie.Episode;

import java.util.List;

/**
 * Esta clase DAO es necesario para el usod e la Base de Datos de ROOM.
 *
 * Aqui se gestionan las consultas a la base de datos sobre el objeto Episodios
 */
@Dao
public interface EpisodeDao {
    @Insert()
    long insert(Episode episode);

    @Update()
    void update(Episode episode);

    @Delete()
    void delete(Episode episode);

    @Query("SELECT * from Episode where user_id=:user_id")
    List<Episode> getUserEpisodes(int user_id);

    @Query("SELECT * from Episode where user_id=:user_id AND imdbID=:imdbID")
    List<Episode> getEpisodeUser(int user_id, String imdbID);

    @Query("DELETE FROM Episode")
    void deleteEpisodes();

    @Query("DELETE FROM Episode WHERE user_id=:userId")
    void deleteEpisodesById(String userId);
}
