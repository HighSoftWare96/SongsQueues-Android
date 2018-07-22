package com.highsoftware96.songsqueues.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.highsoftware96.songsqueues.R;
import com.highsoftware96.songsqueues.constants.DrawerMenuFragmentsTag;
import com.highsoftware96.songsqueues.fragments.songlineups.SongLineupsTabContainer;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final int LINEUP_DETAILS_SEEN_INTENT_HAS_TO_REFRESH = 1;
    public static final String LINEUP_REFERRED_INTENT_EXTRA = "LINEUP_REFERRED_INTENT_EXTRA";
    public static final int LINEUP_DETAILS_MODIFIED_REFRESH_DATA_REQUIRED = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: implementare l'aggiunta di un elemento
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // imposto il fragment di default da visualizzare nella homepage della applicazione
        navigationView.setCheckedItem(R.id.nav_lineups);
        setHomeFragmnetOnline();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        // TODO: implementare fragment da inserire
        if (id == R.id.nav_lineups) {
            // controllo di non essere già nella mia homepage per evitare di ricaricare ogni cosa
            setHomeFragmnetOnline();
            Snackbar.make(findViewById(android.R.id.content), "Lineups", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        } else if (id == R.id.nav_songs) {
            Snackbar.make(findViewById(android.R.id.content), "Songs", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        } else if (id == R.id.nav_events) {
            Snackbar.make(findViewById(android.R.id.content), "Events", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        } else if (id == R.id.nav_account) {
            Snackbar.make(findViewById(android.R.id.content), "Account", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        } else if (id == R.id.nav_settings) {
            Snackbar.make(findViewById(android.R.id.content), "Settings", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        } else if (id == R.id.nav_share_app) {
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.shareapp_subject_text));
            sharingIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.shareapp_body_text));
            startActivity(Intent.createChooser(sharingIntent, "Share SongQueues"));
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setHomeFragmnetOnline() {
        // imposto il fragment di default da visualizzare nella homepage della applicazione
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_fragment_container, new SongLineupsTabContainer(), DrawerMenuFragmentsTag.HOME_FRAGMENT);
        transaction.commit();
    }

}
