package com.example.song_trainer;

import java.util.Locale;
import java.util.Objects;

public class utility {
    public static String cleanNotes(String rawText){
        String notes = !Objects.isNull(rawText) ? rawText : "";
        if(notes.trim().toLowerCase(Locale.ROOT) == "null") {
            notes = "";
        }
        return notes;
    }
}
