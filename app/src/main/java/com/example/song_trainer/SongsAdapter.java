package com.example.song_trainer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.ViewHolder> {

    private List<Song> mSongs;
    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access

    public SongsAdapter(List<Song> songs) {
        mSongs = songs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.song_list_row, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get the data model based on position
        Song song = mSongs.get(position);

        // Set item views based on your views and data model
        TextView idTextView = holder.idTextView;
        idTextView.setText(String.format("%03d",song.songId));
        TextView titleTextView = holder.titleTextView;
        titleTextView.setText(song.title);
        TextView artistTextView = holder.artistTextView;
        artistTextView.setText(song.artist);
        TextView skillLevelTextView = holder.skillLevelTextView;
        skillLevelTextView.setText(String.format("%.1f", song.skillLevel));
        TextView playCountTextView = holder.playCountTextView;
        playCountTextView.setText(String.format("%d", song.playCount));
    }

    @Override
    public int getItemCount() {
        return mSongs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView idTextView;
        public TextView titleTextView;
        public TextView artistTextView;
        public TextView playCountTextView;
        public TextView skillLevelTextView;


        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            idTextView = itemView.findViewById(R.id.tvId);
            titleTextView = itemView.findViewById(R.id.tvTitle);
            artistTextView = itemView.findViewById(R.id.tvArtist);
            playCountTextView = itemView.findViewById(R.id.tvPlayCount);
            skillLevelTextView = itemView.findViewById(R.id.tvSkillLevel);
        }
    }
}
