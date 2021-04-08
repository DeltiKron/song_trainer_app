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
    private Song mSong;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);

        RatingBar skillBar = findViewById(R.id.ratingBar);

        int songId = getIntent().getIntExtra("songId", 0);
        SongDatabase db = SongDatabase.getInstance(this);
        mSong = db.songDAO().getSongById(songId);


        titleText = findViewById(R.id.tvTitle);
        artistText = findViewById(R.id.tvArtist);
        playCountText = findViewById(R.id.tvPlayCount);
        ratingText = findViewById(R.id.tvRating);

        titleText.setText(mSong.title);
        artistText.setText(mSong.artist);
        playCountText.setText(String.format(java.util.Locale.US,"%3d", mSong.playCount));
        String ratingString = "Rating: " + String.format(java.util.Locale.US,"%3.1f", mSong.skillLevel);
        ratingText.setText(ratingString);

        skillBar.setNumStars(5);
        skillBar.setRating((float) 2.5);

        // Add callback to print out rating on click
        FloatingActionButton button = findViewById(R.id.submit_rating_button);
        button.setOnClickListener(view -> {
            String rating = String.valueOf(skillBar.getRating());
            Toast.makeText(getApplicationContext(), rating, Toast.LENGTH_LONG).show();

            float new_rating = skillBar.getRating();
            int play_count = mSong.playCount;
            float old_rating = mSong.skillLevel;
            float mean_rating;

            if (play_count == 0) {
                play_count = 1;
                mean_rating = new_rating;
            } else {
                play_count += 1;
                mean_rating = old_rating + ((new_rating - old_rating) / play_count);

            }
            mSong.skillLevel = mean_rating;
            mSong.playCount = play_count;
            db.songDAO().updateSong(mSong);

            this.finish();
        });
    }
}