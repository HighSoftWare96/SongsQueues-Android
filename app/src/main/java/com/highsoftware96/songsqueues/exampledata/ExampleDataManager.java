package com.highsoftware96.songsqueues.exampledata;

import com.highsoftware96.songsqueues.R;
import com.highsoftware96.songsqueues.constants.LineUpSystemCategoriesNames;
import com.highsoftware96.songsqueues.models.local.Event;
import com.highsoftware96.songsqueues.models.local.Lineup;
import com.highsoftware96.songsqueues.models.local.LineupCategory;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class ExampleDataManager {
    public static Lineup getOneLineUp() {
        return new Lineup(new Random().nextLong(), "My new lineup", "This is a long description about this particular lineup created by a musician for a particular event...", R.drawable.template_test, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), null, getCategories().get(0), getEvents());
    }

    public static ArrayList<LineupCategory> getCategories() {
        ArrayList<LineupCategory> result = new ArrayList<>();
        result.add(new LineupCategory("Rock", false));
        result.add(new LineupCategory(LineUpSystemCategoriesNames.RECENTS, true));
        result.add(new LineupCategory(LineUpSystemCategoriesNames.ALL, true));
        return result;
    }

    public static ArrayList<Event> getEvents() {
        ArrayList<Event> result = new ArrayList<>();
        result.add(new Event("First event", new Date(), new Timestamp(0)));
        result.add(new Event("Second event", new Date(), null));
        result.add(new Event("Third event", new Date(), null));
        return result;
    }
}
