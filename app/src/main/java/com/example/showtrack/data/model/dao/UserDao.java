package com.example.showtrack.data.model.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.showtrack.data.model.user.User;

import java.util.List;

/**
 * Esta clase DAO es necesario para el usod e la Base de Datos de ROOM.
 *
 * Aqui se gestionan las consultas a la base de datos sobre el objeto User
 */
@Dao
public interface UserDao {

    @Insert()
    long insert(User user);

    @Update()
    void update(User user);

    @Delete()
    void delete(User user);

    @Query("SELECT profilePhotoRoom FROM User")
    String getUserPhoto();

    @Query("SELECT * FROM User")
    List<User> getUsers();
}
