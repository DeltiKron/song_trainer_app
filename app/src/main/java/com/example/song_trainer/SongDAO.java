package com.example.song_trainer;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SongDAO {
    @Query("select * from song")
    List<Song> getSongList();
    @Query("SELECT * FROM song WHERE songId = :songId")
    Song getSongById(int songId);
    @Insert
    void insertSong(Song song);
    @Update
    void updateSong(Song song);
    @Delete
    void deleteSong(Song song);
}
