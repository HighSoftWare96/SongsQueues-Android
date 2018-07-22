package com.highsoftware96.songsqueues.models.local;

import java.io.Serializable;

class LineupAttachment implements Serializable {
    public String RemoteRelativePath;
    public String LocalPath;
    public String Base64String;
    public String ContentType;
    public String Name;


    public LineupAttachment(String name, String path, boolean remote, String contentType) {
        this.Name = name;
        if (remote) {
            this.RemoteRelativePath = path;
        } else {
            this.LocalPath = path;
        }
        this.ContentType = contentType;
    }

    public LineupAttachment(String name, String base64, String contentType) {
        this.Name = name;
        this.Base64String = base64;
        this.ContentType = contentType;
    }

}
