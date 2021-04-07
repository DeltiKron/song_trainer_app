package com.example.song_trainer;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;



@Entity
public class Song {
    @PrimaryKey
    public int songId;
    public String title;
    public String artist;
    @ColumnInfo(name = "play_count")
    public int playCount;
    @ColumnInfo(name = "skill_level")
    public float skillLevel;
    @ColumnInfo(name = "notes")
    public String notes;

    public Song(int songId, String title, String artist, int playCount, float skillLevel) {
        this.songId = songId;
        this.title = title;
        this.artist = artist;
        this.playCount = playCount;
        this.skillLevel = skillLevel;
        this.notes = "";
    }

    public static ArrayList<Song> createSongsList(int numContacts) {
        ArrayList<Song> songs = new ArrayList<Song>();

        for (int i = 1; i <= numContacts; i++) {
            songs.add(new Song(
                    i+500,
                    "Hello",
                    "Lionel Richie",
                    0,
                    (float) 0.
            ));
        }
        return songs;
    }
}

