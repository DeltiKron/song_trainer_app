package com.example.song_trainer;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Song.class, exportSchema = false, version = 1)
public abstract class SongDatabase extends RoomDatabase {
    private static final String DB_NAME = "song_db";
    private static SongDatabase instance;

    public static synchronized SongDatabase getInstance(Context context) {
        if (instance == null){
            instance = Room.databaseBuilder(
                    context.getApplicationContext(), SongDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
    public abstract SongDAO songDAO();
}
