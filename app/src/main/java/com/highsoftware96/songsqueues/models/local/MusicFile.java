package com.highsoftware96.songsqueues.models.local;

import java.util.HashMap;

class MusicFile extends LineupAttachment {

    /// mappa che contiene per ogni istante di tempo inserito in millisecondi una nota
    public HashMap<Long, String> Placeholders;

    public MusicFile(String name, String path, boolean remote, String contentType) {
        super(name, path, remote, contentType);
    }

    public MusicFile(String name, String path, boolean remote, String contentType, HashMap<Long, String> placeholders) {
        super(name, path, remote, contentType);
        this.Placeholders = placeholders;
    }
}
