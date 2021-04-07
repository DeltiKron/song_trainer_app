package com.example.song_trainer;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;

import java.util.List;

public class SongListActivity extends AppCompatActivity {

    private void update(){
        // Lookup the recyclerview in activity layout
        RecyclerView rvSongs = findViewById(R.id.rvSongs);

        // Generate Placeholder songs
        SongDatabase songDB = SongDatabase.getInstance(this);
        List<Song> songs = songDB.songDAO().getSongList();

        // Create adapter passing in the sample user data
        SongsAdapter adapter = new SongsAdapter(songs);
        // Attach the adapter to the recyclerview to populate items
        rvSongs.setAdapter(adapter);
        // Set layout manager to position the items
        rvSongs.setLayoutManager(new

                LinearLayoutManager(this));
        // That's all!
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton add_song_button = findViewById(R.id.add_song_button);
        add_song_button.setOnClickListener(view -> {
                    Intent intent = new Intent(this, AddSongActivity.class);
                    startActivity(intent);
                }
        );

        this.update();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.update();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        this.update();
    }
}