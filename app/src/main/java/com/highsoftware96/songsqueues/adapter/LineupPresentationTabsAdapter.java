package com.highsoftware96.songsqueues.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.highsoftware96.songsqueues.fragments.songlineupsdetails.LineupPresentationDetailsFragment;

public class LineupPresentationTabsAdapter extends FragmentStatePagerAdapter {
    public LineupPresentationTabsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // TODO: restituire le view da visualizzare per ogni categoria di dettagli della lineup
        switch (position) {
            default:
                return new LineupPresentationDetailsFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
