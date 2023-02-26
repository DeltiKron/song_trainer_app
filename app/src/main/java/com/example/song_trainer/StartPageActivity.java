package com.example.song_trainer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.Random;

public class StartPageActivity extends AppCompatActivity {
    Button add_song_button;
    Button random_song_button;
    Button song_list_button;
    Button practice_list_button;
    Button practice_song_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);

        add_song_button = findViewById(R.id.add_song_button);
        random_song_button = findViewById(R.id.random_song_button);
        song_list_button = findViewById(R.id.song_list_button);
        practice_list_button = findViewById(R.id.practice_list_button);
        practice_song_button = findViewById(R.id.practice_song_button);

        random_song_button.setOnClickListener(view -> {
            SongDatabase db = SongDatabase.getInstance(this);
            List<Song> songs = db.songDAO().getSongList();

            if(songs.size()==0){
                Snackbar.make(view, "No songs in database!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                return;
            }

            Random rand = new Random();
            int index = songs.get(rand.nextInt(songs.size())).songId;

            Intent intent = new Intent(this, PlaySongActivity.class);
            intent.putExtra("songId", index);
            this.startActivity(intent);
        });

        add_song_button.setOnClickListener(
                view -> {
                    Intent intent = new Intent(this, AddSongActivity.class);
                    startActivity(intent);
                }
        );

        song_list_button.setOnClickListener(
                view -> {
                    Intent intent = new Intent(this, SongListActivity.class);
                    Snackbar.make(view, "Add song was clicked!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    startActivity(intent);
                }
        );
        practice_list_button.setOnClickListener(
                view -> {
                    Intent intent = new Intent(this, SongListActivity.class);
                    intent.putExtra("practice_mode",true);
                    Snackbar.make(view, "Add song was clicked!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    startActivity(intent);
                }
        );

        practice_song_button.setOnClickListener(view -> {
            Intent intent = new Intent(this, PlaySongActivity.class);
            intent.putExtra("practice_mode", true);
            this.startActivity(intent);
        });


    }

}