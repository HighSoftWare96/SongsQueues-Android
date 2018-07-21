package com.highsoftware96.songsqueues.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.highsoftware96.songsqueues.fragments.songlineups.SongLineupsFragment;
import com.highsoftware96.songsqueues.fragments.songlineups.SongLineupsRecents;

public class CategoriesPagesAdapter extends FragmentStatePagerAdapter {
    private int nTabs;

    public CategoriesPagesAdapter(FragmentManager fm, int nTabs) {
        super(fm);
        this.nTabs = nTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new SongLineupsFragment();
            case 1:
                return new SongLineupsRecents();
            default:
                return new SongLineupsRecents();
        }
    }

    @Override
    public int getCount() {
        return this.nTabs;
    }
}
