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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_song);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        // TODO: add checks for empty fields, alert user
        // TODO: an edit-song activity would also be neat
        fab.setOnClickListener(view -> {
            String title = ((EditText) findViewById(R.id.editTitle)).getText().toString();
            Snackbar.make(view, "Sav button was clicked, title: " + title, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        });
    }
}