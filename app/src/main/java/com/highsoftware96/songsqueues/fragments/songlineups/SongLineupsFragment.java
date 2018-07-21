package com.highsoftware96.songsqueues.fragments.songlineups;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.highsoftware96.songsqueues.R;
import com.highsoftware96.songsqueues.exampledata.ExampleDataManager;
import com.highsoftware96.songsqueues.models.local.Lineup;

import java.util.ArrayList;

public class SongLineupsFragment extends Fragment {

    protected ListView lineupsListView = null;
    protected ArrayList<Lineup> testLineups;
    protected SwipeRefreshLayout swipeRefreshLayout;

    public SongLineupsFragment() {
        testLineups = onSetupLineupsListContent(null);
    }

    public ArrayList<Lineup> onSetupLineupsListContent(@Nullable String category) {
        ArrayList<Lineup> result = new ArrayList<>();
        // Required empty public constructor
        for (int i = 0; i < 3; i++) {
            result.add(ExampleDataManager.getOneLineUp());
        }
        return result;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_song_lineups, container, false);
        this.lineupsListView = v.findViewById(R.id.lineups_listview);
        lineupsListView.setAdapter(new SongLineupsListAdapter());
        lineupsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                view.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.bounce));
                Toast.makeText(getContext(), "# " + position, Toast.LENGTH_SHORT).show();
            }
        });
        this.swipeRefreshLayout = v.findViewById(R.id.refresh_lineups_swipe);
        this.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // TODO: faccio il refresh dei dati della app
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        return v;
    }

    class SongLineupsListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return testLineups.size();
        }

        @Override
        public Object getItem(int position) {
            return testLineups.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.lineup_item_layout, null);
            TextView description = convertView.findViewById(R.id.description_placeholder);
            description.setText(testLineups.get(position).Description);
            TextView title = convertView.findViewById(R.id.lineup_title_placeholder);
            title.setText(testLineups.get(position).Title);
            TextView lastModified = convertView.findViewById(R.id.last_modified_placeholder);
            lastModified.setText(testLineups.get(position).DateLastModified.toString());
            TextView songs = convertView.findViewById(R.id.songs_placeholder);
            songs.setText(testLineups.get(position).getSongsDescription());
            ImageView preview = convertView.findViewById(R.id.preview_image_placeholder);
            preview.setImageDrawable(getResources().getDrawable(testLineups.get(position).PresentationImageID));
            return convertView;
        }
    }
}
