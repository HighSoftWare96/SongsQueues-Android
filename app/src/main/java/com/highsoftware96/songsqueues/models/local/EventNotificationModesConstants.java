package com.highsoftware96.songsqueues.models.local;

import android.util.Pair;

import java.util.HashMap;
import java.util.Map;

public class EventNotificationModesConstants {
    // TODO: recuperare le stringhe da res
    private static HashMap<String, EventNotificationMode> possibleModes = new HashMap<>();

    static {
        possibleModes.put("One hour before", EventNotificationMode.ONE_HOUR_BEFORE_DEF);
        possibleModes.put("Five hours before", EventNotificationMode.FIVE_HOUR_BEFORE_DEF);
        possibleModes.put("One day before", EventNotificationMode.ONE_DAY_BEFORE_DEF);
        possibleModes.put("Two days before", EventNotificationMode.TWO_DAY_BEFORE_DEF);
        possibleModes.put("One week before", EventNotificationMode.ONE_WEEK_BEFORE_DEF);
    }

    public static HashMap<String, EventNotificationMode> getPossibleModes() {
        return possibleModes;
    }

    public static String[] getConstants() {
        return possibleModes.keySet().toArray(new String[]{});
    }

    public static EventNotificationMode getRelatedDefinition(String pos) {
        return possibleModes.get(pos);
    }

    public static String getStringRapr(EventNotificationMode mode) {
        for (Map.Entry<String, EventNotificationMode> item : possibleModes.entrySet()) {
            if (item.getValue().equals(mode)) {
                return item.getKey();
            }
        }
        return null;
    }

    public static int getModeIndex(EventNotificationMode mode) {
        int index = 0;
        for (EventNotificationMode item : possibleModes.values().toArray(new EventNotificationMode[]{})) {
            if (item.equals(mode))
                return index;
            index++;
        }
        return -1;
    }
}
