package com.example.showtrack.data.model.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.showtrack.data.model.Film;
import com.example.showtrack.data.model.dao.FilmDao;
import com.example.showtrack.data.model.dao.UserDao;
import com.example.showtrack.data.model.serie.Episode;
import com.example.showtrack.data.model.serie.Season;
import com.example.showtrack.data.model.serie.Serie;
import com.example.showtrack.data.model.user.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class, Film.class, Serie.class, Episode.class, Season.class}, version = 1, exportSchema = false)
public abstract class ShowTrackDatabase extends RoomDatabase {

    private static volatile ShowTrackDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    // DAO
    public abstract UserDao userDao();
    public abstract FilmDao filmDao();

    static ShowTrackDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ShowTrackDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ShowTrackDatabase.class, "showtrack")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public static void create(final Context context) {
        if (INSTANCE == null) {
            synchronized (ShowTrackDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ShowTrackDatabase.class, "showtrack")
                            .build();
                }
            }
        }
    }

    public static ShowTrackDatabase getDatabase() {
        return INSTANCE;
    }
}
