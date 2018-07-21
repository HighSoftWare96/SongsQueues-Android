package com.highsoftware96.songsqueues.models.local;

import java.util.ArrayList;

class Song {
    public String Name;
    public ArrayList<LineupAttachment> Documents;
    public ArrayList<MusicFile> MusicFiles;

    public Song(String Name, ArrayList<LineupAttachment> Documents, ArrayList<MusicFile> MusicFiles) {
        this.Name = Name;
        this.Documents = Documents;
        this.MusicFiles = MusicFiles;
    }
}
