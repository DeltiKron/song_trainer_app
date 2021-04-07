package com.example.song_trainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

public class StartPageActivity extends AppCompatActivity {
    Button add_song_button;
    Button random_song_button;
    Button song_list_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);

        add_song_button = findViewById(R.id.add_song_button);
        random_song_button = findViewById(R.id.random_song_button);
        song_list_button = findViewById(R.id.song_list_button);

        add_song_button.setOnClickListener(
                view -> {
                    Intent intent = new Intent(this,ScrollingActivity.class);
                    Snackbar.make(view, "Add song was clicked!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    startActivity(intent);
                }
        );

        song_list_button.setOnClickListener(
                view -> {
                    Intent intent = new Intent(this,SongListActivity.class);
                    Snackbar.make(view, "Add song was clicked!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    startActivity(intent);
                }
        );
    }

    // TODO: POC for retrieval from DB using room: https://developer.android.com/training/data-storage/room
}