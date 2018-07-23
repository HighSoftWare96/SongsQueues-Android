package com.highsoftware96.songsqueues.models.local;

import android.support.annotation.Nullable;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class Event implements Serializable {
    private long ID;
    private Date eventDate;
    private Timestamp notificationTimestamp;
    private String eventName;
    private boolean notificationEnabled;

    public Event(String eventName, Date eventDate, @Nullable Timestamp notificationTimestamp) {
        if (notificationTimestamp == null) {
            notificationEnabled = false;
        } else {
            this.notificationTimestamp = notificationTimestamp;
            notificationEnabled = true;
        }
        this.eventName = eventName;
        this.eventDate = eventDate;
    }

    public long getID() {
        return ID;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Timestamp getNotificationTimestamp() {
        return notificationTimestamp;
    }

    public void setNotificationTimestamp(Timestamp notificationTimestamp) {
        this.notificationTimestamp = notificationTimestamp;
    }

    public boolean isNotificationEnabled() {
        return notificationEnabled;
    }

    public void setNotificationEnabled(boolean notificationEnabled) {
        this.notificationEnabled = notificationEnabled;
    }
}
