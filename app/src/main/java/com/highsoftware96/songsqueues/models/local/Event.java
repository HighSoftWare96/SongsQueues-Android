package com.highsoftware96.songsqueues.models.local;


import org.threeten.bp.Duration;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

public class Event implements Serializable, Cloneable {
    private long ID;
    private Date eventDate;
    private Timestamp notificationTimestamp;
    private String eventName;
    private boolean notificationEnabled;
    private EventNotificationMode notificationMode;

    public Event(String eventName, Date eventDate, boolean notificationDefaultEnabled) {
        this.ID = new Random().nextLong();
        this.eventName = eventName;
        this.eventDate = eventDate;
        if (notificationDefaultEnabled) {
            this.notificationEnabled = true;
            setNotificationMode(EventNotificationMode.ONE_HOUR_BEFORE_DEF);
        } else {
            this.notificationEnabled = false;
        }
    }

    public Event(String eventName, Date eventDate, EventNotificationMode notificationMode) {
        this(eventName, eventDate, true);
        setNotificationMode(notificationMode);
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

    public EventNotificationMode getNotificationMode() {
        return notificationMode;
    }

    public void setNotificationMode(EventNotificationMode mode) {
        this.notificationEnabled = true;
        // Valore di default..
        if (mode == null)
            this.notificationMode = EventNotificationMode.ONE_HOUR_BEFORE_DEF;
        else
            this.notificationMode = mode;
        // REGOLO LA DATA INDIETRO A SECONDA DI QUANDO HO SELEZIONATO
        switch (this.notificationMode) {
            case ONE_DAY_BEFORE_DEF:
                this.notificationTimestamp = new Timestamp(eventDate.getTime() - Duration.ofDays(1).toMillis());
                break;
            case TWO_DAY_BEFORE_DEF:
                this.notificationTimestamp = new Timestamp(eventDate.getTime() - Duration.ofDays(2).toMillis());
                break;
            case FIVE_HOUR_BEFORE_DEF:
                this.notificationTimestamp = new Timestamp(eventDate.getTime() - Duration.ofHours(5).toMillis());
                break;
            case ONE_HOUR_BEFORE_DEF:
                this.notificationTimestamp = new Timestamp(eventDate.getTime() - Duration.ofHours(1).toMillis());
                break;
            case ONE_WEEK_BEFORE_DEF:
                this.notificationTimestamp = new Timestamp(eventDate.getTime() - Duration.ofDays(7).toMillis());
                break;
        }
    }

    public boolean isNotificationEnabled() {
        return notificationEnabled;
    }

    public void unsetNotificationMode() {
        this.notificationEnabled = false;
        this.notificationMode = null;
        this.notificationTimestamp = null;
    }

    public Event clone() {
        try {
            return (Event) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
