package com.example.song_trainer;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class SongListActivity extends AppCompatActivity implements SongsAdapter.onSongListener {
    private static final String TAG = "SongListActivity";
    private List<Song> mSongs;

    private void update() {
        // Lookup the recyclerview in activity layout
        RecyclerView rvSongs = findViewById(R.id.rvSongs);

        // Fetch list of songs
        SongDatabase songDB = SongDatabase.getInstance(this);
        mSongs = songDB.songDAO().getSongList();

        // Create adapter passing in the sample user data
        SongsAdapter adapter = new SongsAdapter(mSongs,this);
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

    @Override
    public void onSongClick(int position) {
        Log.d(TAG,"onSongClick clicked at position "+String.format("%3d",position));
        Song song = mSongs.get(position);
        Toast.makeText(getApplicationContext(), song.title, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, PlaySongActivity.class);
        intent.putExtra("songId",song.songId);
        this.startActivity(intent);
    }
}