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
    @Query("SELECT count(*) FROM song where title= :title and artist= :artist")
    boolean songExists(String title, String artist);
    @Query("SELECT *, 1./(play_count+1)+1./(skill_level+1) as score from song order by score desc")
    List<Song> getSongsByPracticeScore();


}
