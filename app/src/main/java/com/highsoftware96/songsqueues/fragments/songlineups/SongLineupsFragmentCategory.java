package com.highsoftware96.songsqueues.fragments.songlineups;

import android.support.annotation.Nullable;

import com.highsoftware96.songsqueues.exampledata.ExampleDataManager;
import com.highsoftware96.songsqueues.models.local.Lineup;

import java.util.ArrayList;

public class SongLineupsFragmentCategory extends SongLineupsFragment {
    @Override
    public ArrayList<Lineup> onSetupLineupsListContent(@Nullable String category) {
        ArrayList<Lineup> result = new ArrayList<>();
        // TODO: la lista comprende solamente gli ultimi elementi con quella categoria!
        // Required empty public constructor
        result.add(ExampleDataManager.getOneLineUp());
        return result;
    }

}
