package com.example.showtrack.data.model.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.showtrack.data.model.user.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert()
    long insert(User user);

    @Update()
    void update(User user);

    @Query("SELECT * FROM User")
    List<User> getUsers();
}
