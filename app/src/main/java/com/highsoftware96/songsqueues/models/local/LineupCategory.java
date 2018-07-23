package com.highsoftware96.songsqueues.models.local;

import java.io.Serializable;

public class LineupCategory implements Serializable {
    public String Name;
    public boolean SystemCategory;

    public LineupCategory(String name, boolean systemCategory) {
        this.Name = name;
        this.SystemCategory = systemCategory;
    }

    @Override
    public String toString() {
        return this.Name;
    }
}
