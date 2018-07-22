package com.highsoftware96.songsqueues.models.local;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Lineup implements Serializable {
    public long ID;
    public String Title;
    public String Description;
    public int PresentationImageID;
    public Timestamp DateCreation;
    public Timestamp DateLastModified;
    public ArrayList<Song> Songs;
    public LineupCategory Category;
    public boolean Favourite;

    public Lineup(long ID, String Title, String description, int PresentationImage, Timestamp DateCreation, Timestamp DateLastModified, ArrayList<Song> Songs, LineupCategory category) {
        this.ID = ID;
        this.Title = Title;
        this.Description = description;
        this.PresentationImageID = PresentationImage;
        this.DateCreation = DateCreation;
        this.DateLastModified = DateLastModified;
        this.Songs = Songs;
        this.Category = category;
    }

    public String getSongsDescription() {
        StringBuilder desc = new StringBuilder();
        if (Songs != null) {
            for (Song item : Songs) {
                desc.append(item.Name).append(", ");
            }
            return desc.toString().substring(0, desc.length() - 2);
        }
        return "-";
    }

    public void setFavourite(boolean favourite) {
        Favourite = favourite;
    }
}
