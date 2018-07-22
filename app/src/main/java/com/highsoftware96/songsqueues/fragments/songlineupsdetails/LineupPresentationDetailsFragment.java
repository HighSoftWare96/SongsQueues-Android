package com.highsoftware96.songsqueues.fragments.songlineupsdetails;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.highsoftware96.songsqueues.R;


public class LineupPresentationDetailsFragment extends Fragment {

    public LineupPresentationDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lineup_presentation_details, container, false);
    }
}
