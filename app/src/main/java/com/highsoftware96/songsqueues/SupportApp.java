package com.highsoftware96.songsqueues;

import android.app.Application;
import android.content.Context;

import com.jakewharton.threetenabp.AndroidThreeTen;

public class SupportApp extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        AndroidThreeTen.init(this);
    }

    public static Context getContext() {
        return mContext;
    }
}
