package com.example.song_trainer;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import static com.android.volley.toolbox.Volley.newRequestQueue;

public class SongListActivity extends AppCompatActivity implements SongsAdapter.onSongListener {
    private static final String TAG = "SongListActivity";
    private List<Song> mSongs;
    boolean startedInPracticeMode = false;
    private void update() {
        // Lookup the recyclerview in activity layout
        RecyclerView rvSongs = findViewById(R.id.rvSongs);

        // Fetch list of songs
        SongDatabase songDB = SongDatabase.getInstance(this);
        mSongs = this.startedInPracticeMode ? songDB.songDAO().getSongsByPracticeScore() : songDB.songDAO().getSongList();


        // Create adapter passing in the sample user data
        SongsAdapter adapter = new SongsAdapter(mSongs,this);
        // Attach the adapter to the recyclerview to populate items
        rvSongs.setAdapter(adapter);
        // Set layout manager to position the items
        rvSongs.setLayoutManager(new
                LinearLayoutManager(this));
        // That's all!

        this.importSongsFromJSON("http://192.168.2.101:5000/songs/export_songs",songDB);
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

        Intent myIntent = getIntent(); // gets the previously created intent
        this.startedInPracticeMode = myIntent.getBooleanExtra("practice_mode",false);


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

    public void importSongsFromJSON(String url,SongDatabase db){
        // Instantiate the RequestQueue.
        RequestQueue queue = newRequestQueue(this);

// Request a string response from the provided URL.
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url,
                response -> {
                    try {

                        Log.d("JsonArray", response.toString());
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject entry = response.getJSONObject(i);
                            Song song =  Song.from_json(entry);
                            Log.d("title", song.title);
                            boolean exists = db.songDAO().songExists(song.title, song.artist);
                            Log.d("exists", String.valueOf(exists));
                            if (!exists){
                                db.songDAO().insertSong(song);
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

/*
                        try {
                            entry = response.getJSONObject(i);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.d("JSONEntry", entry.toString());
*/

                }, error -> System.out.println("That didn't work!"));

// Add the request to the RequestQueue.
        queue.add(jsonArrayRequest);
    }

}