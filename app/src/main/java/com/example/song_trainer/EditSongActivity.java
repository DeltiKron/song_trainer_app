package com.example.song_trainer;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditSongActivity extends AppCompatActivity {
    private EditText titleText;
    private EditText artistText;
    private EditText playCountText;
    private EditText notesText;
    private Song mSong;
    SongDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_song);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }

        RatingBar skillBar = findViewById(R.id.newRatingBar);

        int songId = getIntent().getIntExtra("songId", 0);

        db = SongDatabase.getInstance(this);
        mSong = db.songDAO().getSongById(songId);


        titleText = findViewById(R.id.etTitle);
        artistText = findViewById(R.id.etArtist);
        playCountText = findViewById(R.id.etPlayCount);
        notesText = findViewById(R.id.etNotes);

        titleText.setText(mSong.title);
        artistText.setText(mSong.artist);
        playCountText.setText(String.format(java.util.Locale.US, "%3d", mSong.playCount));
        skillBar.setNumStars(5);
        skillBar.setRating((float) 2.5);

        // Add callback to save song on click
        FloatingActionButton button = findViewById(R.id.save_song_button);
        button.setOnClickListener(view -> {
            String rating = String.valueOf(skillBar.getRating());
            Toast.makeText(getApplicationContext(), rating, Toast.LENGTH_LONG).show();

            float new_rating = skillBar.getRating();
            String new_title = titleText.getText().toString();
            String new_artist = artistText.getText().toString();
            int play_count = Integer.parseInt(playCountText.getText().toString().trim());
            String notes = notesText.getText().toString();

            mSong.notes = notes;
            mSong.artist = new_artist;
            mSong.skillLevel = new_rating;
            mSong.title = new_title;
            mSong.playCount = play_count;
            db.songDAO().updateSong(mSong);

            this.finish();
        });

    }
}