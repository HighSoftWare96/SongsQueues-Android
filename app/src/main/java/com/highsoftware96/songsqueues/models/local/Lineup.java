package com.highsoftware96.songsqueues.models.local;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Lineup implements Serializable {
    private long ID;
    private String Title;
    private String Description;
    private int PresentationImageID;
    private Timestamp DateCreation;
    private Timestamp DateLastModified;
    private ArrayList<Song> Songs;
    private ArrayList<Event> Events;
    private LineupCategory Category;
    private boolean Favourite;

    public Lineup(long ID, String Title, String description, int PresentationImage, Timestamp DateCreation, Timestamp DateLastModified, ArrayList<Song> Songs, LineupCategory category, ArrayList<Event> events) {
        this.ID = ID;
        this.Title = Title;
        this.Description = description;
        this.PresentationImageID = PresentationImage;
        this.DateCreation = DateCreation;
        this.DateLastModified = DateLastModified;
        this.Songs = Songs;
        this.Category = category;
        this.Events = events;
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

    public boolean getFavourite() {
        return this.Favourite;
    }

    public ArrayList<Song> getSongs() {
        return Songs;
    }

    public void setSongs(ArrayList<Song> songs) {
        Songs = songs;
    }

    public int getPresentationImageID() {
        return PresentationImageID;
    }

    public void setPresentationImageID(int presentationImageID) {
        PresentationImageID = presentationImageID;
    }

    public long getID() {
        return ID;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public Timestamp getDateCreation() {
        return DateCreation;
    }

    public void setDateCreation(Timestamp dateCreation) {
        DateCreation = dateCreation;
    }

    public Timestamp getDateLastModified() {
        return DateLastModified;
    }

    public void setDateLastModified(Timestamp dateLastModified) {
        DateLastModified = dateLastModified;
    }

    public ArrayList<Event> getEvents() {
        return Events;
    }

    public void setEvents(ArrayList<Event> events) {
        Events = events;
    }

    public LineupCategory getCategory() {
        return Category;
    }

    public void setCategory(LineupCategory category) {
        Category = category;
    }

    public void setFavourite(boolean favourite) {
        Favourite = favourite;
    }
}
