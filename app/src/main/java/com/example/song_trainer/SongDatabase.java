package com.example.song_trainer;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Song.class, exportSchema = false, version = 2)
public abstract class SongDatabase extends RoomDatabase {
    private static final String DB_NAME = "song_db";
    private static SongDatabase instance;

    public static synchronized SongDatabase getInstance(Context context) {
        if (instance == null){
            instance = Room.databaseBuilder(
                    context.getApplicationContext(), SongDatabase.class, DB_NAME)
                    //.fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .addMigrations(MIGRATION_1_2)
                    .build();

        }

        return instance;
    }

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        public void migrate(SupportSQLiteDatabase database) {
            // Since we didn't alter the table, there's nothing else to do here.
        }
    };




    public abstract SongDAO songDAO();
}
