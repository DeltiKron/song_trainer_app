package com.example.song_trainer;

import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PlaySongActivity extends AppCompatActivity {

    private TextView titleText;
    private TextView artistText;
    private TextView playCountText;
    private TextView ratingText;
    public Song song = new Song("Foo", "Bar", 0, (float) 3.5, "");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);

        RatingBar skillBar = findViewById(R.id.ratingBar);

        titleText = findViewById(R.id.tvTitle);
        artistText = findViewById(R.id.tvArtist);
        playCountText = findViewById(R.id.tvPlayCount);
        ratingText = findViewById(R.id.tvRating);

        titleText.setText(song.title);
        artistText.setText(song.artist);
        playCountText.setText(String.format("%3d", song.playCount));
        String ratingString = "Rating: " + String.format("%3.1f", song.skillLevel);
        ratingText.setText(ratingString);

        skillBar.setNumStars(5);
        skillBar.setRating((float) 2.5);

        // Add callback to print out rating on click
        FloatingActionButton button = findViewById(R.id.submit_rating_button);
        button.setOnClickListener(view -> {
            String rating = String.valueOf(skillBar.getRating());
            Toast.makeText(getApplicationContext(), rating, Toast.LENGTH_LONG).show();
            // ToDo: Add update of rating here
            this.finish();
        });
    }
}