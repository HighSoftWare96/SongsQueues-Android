package com.highsoftware96.songsqueues.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.highsoftware96.songsqueues.R;
import com.highsoftware96.songsqueues.fragments.songlineups.SongLineupsFragment;
import com.highsoftware96.songsqueues.models.local.Lineup;

import java.util.ArrayList;

public class SongLineupsListAdapter extends BaseAdapter {

    private ArrayList<Lineup> dataToDisplay;
    private SongLineupsFragment contextFragment;

    public SongLineupsListAdapter(ArrayList<Lineup> dataToDisplay, SongLineupsFragment referredFragment) {
        this.dataToDisplay = dataToDisplay;
        this.contextFragment = referredFragment;
    }

    @Override
    public int getCount() {
        return dataToDisplay.size();
    }

    @Override
    public Object getItem(int position) {
        return dataToDisplay.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = this.contextFragment.getLayoutInflater().inflate(R.layout.lineup_item_layout, null);
        ImageButton menuItemBtn = convertView.findViewById(R.id.list_item_dots_button_menu);
        // imposto il tag del bottone con la posizione della view creata all'intenro della lista
        menuItemBtn.setTag(position);
        // imposto il listener creato per aprire il menu per ciascun elemento della lista
        menuItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contextFragment.showListItemMenu(position);
            }
        });
        TextView description = convertView.findViewById(R.id.description_placeholder);
        description.setText(dataToDisplay.get(position).Description);
        TextView title = convertView.findViewById(R.id.lineup_title_placeholder);
        title.setText(dataToDisplay.get(position).Title);
        TextView lastModified = convertView.findViewById(R.id.last_modified_placeholder);
        lastModified.setText(dataToDisplay.get(position).DateLastModified.toString());
        TextView songs = convertView.findViewById(R.id.songs_placeholder);
        songs.setText(dataToDisplay.get(position).getSongsDescription());
        ImageView preview = convertView.findViewById(R.id.preview_image_placeholder);
        preview.setImageDrawable(this.contextFragment.getResources().getDrawable(dataToDisplay.get(position).PresentationImageID));
        return convertView;
    }
}