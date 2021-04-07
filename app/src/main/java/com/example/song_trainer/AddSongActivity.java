package com.example.song_trainer;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;

public class AddSongActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SongDatabase songDB = SongDatabase.getInstance(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_song);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        // TODO: add checks for empty fields, alert user
        // TODO: an edit-song activity would also be neat
        // TODO: check for redundancy

        fab.setOnClickListener(view -> {
            String title = ((EditText) findViewById(R.id.editTitle)).getText().toString();
            String artist = ((EditText) findViewById(R.id.editArtist)).getText().toString();
            String notes = ((EditText) findViewById(R.id.editNotes)).getText().toString();
            Song song = new Song(title,artist,0,0,notes);


            songDB.songDAO().insertSong(song);
            Snackbar.make(view, "Save button was clicked, added " + title + " to the database!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            this.finish();

        });
    }
}