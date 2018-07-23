package com.highsoftware96.songsqueues.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.highsoftware96.songsqueues.R;
import com.highsoftware96.songsqueues.adapter.LineupPresentationTabsAdapter;
import com.highsoftware96.songsqueues.models.local.Lineup;

public class LineupPresentationActivity extends AppCompatActivity {

    private Intent originalIntent;
    private Lineup selectedLineupToShow;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lineup_presentation);

        TabLayout tabLayout = findViewById(R.id.content_lineup_details_tab);
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.details_tab_name)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.lineup_component_tab_name)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.songs_tab_name)));
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);

        final ViewPager viewPager = findViewById(R.id.content_lineup_details_pager);
        final PagerAdapter adapter = new LineupPresentationTabsAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.originalIntent = getIntent();
        this.selectedLineupToShow = (Lineup) originalIntent.getSerializableExtra(MainActivity.LINEUP_REFERRED_INTENT_EXTRA);
        this.toolbar.setTitle(selectedLineupToShow.getTitle());
        setSupportActionBar(toolbar);
        // mostro il pulsante di back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.lineup_details_menu, menu);
        if (selectedLineupToShow.getFavourite()) {
            menu.findItem(R.id.actionDetails_favourite_lineup).setIcon(R.drawable.favourite_full_icon);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // TODO: implementare i metodi: eliminazione lineup, refresh e play della lineup
            case R.id.actionDetails_refresh_lineup:
                break;
            case R.id.actionDetails_delete_lineup:
                break;
            case R.id.actionDetails_play_lineup:
                break;
            case R.id.actionDetails_favourite_lineup:
                // TODO: applicare il cambiamento dei dati in senso globale!
                if (!this.selectedLineupToShow.getFavourite()) {
                    this.selectedLineupToShow.setFavourite(true);
                    item.setIcon(R.drawable.favourite_full_icon);
                } else {
                    this.selectedLineupToShow.setFavourite(false);
                    item.setIcon(R.drawable.favourite_empty_icon_light);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        // TODO: se l'utente ha modificato i dati del lineup!
        setResult(MainActivity.LINEUP_DETAILS_MODIFIED_REFRESH_DATA_REQUIRED);
        finish();
        overridePendingTransition(R.anim.bounce, R.anim.slide_out_down);
    }

    public Lineup getSelectedLineupToShow() {
        return selectedLineupToShow;
    }
}
