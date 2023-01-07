package com.example.song_trainer;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;



@Entity
public class Song {
    @PrimaryKey(autoGenerate = true)
    public int songId;
    public String title;
    public String artist;
    @ColumnInfo(name = "play_count")
    public int playCount;
    @ColumnInfo(name = "skill_level")
    public float skillLevel;
    @ColumnInfo(name = "notes")
    public String notes;


    public static Song from_json(JSONObject jsonObject) throws JSONException {
        String title = jsonObject.getString("title");
        String artist = jsonObject.getString("artist");
        String notes = jsonObject.getString("notes");
        int playCount = jsonObject.getInt("play_count");
        int skillLevel = jsonObject.getInt("skill_level");
        return new Song(title,artist,playCount,skillLevel,notes);
    }

    public Song( String title, String artist, int playCount, float skillLevel,String notes) {
        this.title = title;
        this.artist = artist;
        this.playCount = playCount;
        this.skillLevel = skillLevel;
        this.notes = notes;
    }

    public ArrayList<Song> createPlaceholderSongsList(int numContacts) {
        ArrayList<Song> songs = new ArrayList<>();

        for (int i = 1; i <= numContacts; i++) {
            songs.add(new Song(
                    "Hello",
                    "Lionel Richie",
                    0,
                    (float) 0.,
                    ""
            ));
        }
        return songs;
    }

    public double practiceScore(){
            return 1./(this.playCount+1)+1./(this.skillLevel+1);
    }
}

