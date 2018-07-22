package com.highsoftware96.songsqueues.models.local;

import java.io.Serializable;

public class ImageData extends LineupAttachment implements Serializable {
    public ImageData(String name, String path, boolean remote, String contentType) {
        super(name, path, remote, contentType);
    }

    public ImageData(String name, String base64, String contentType) {
        super(name, base64, contentType);
    }
}
