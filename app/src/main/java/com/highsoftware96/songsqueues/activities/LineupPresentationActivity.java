package com.highsoftware96.songsqueues.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.highsoftware96.songsqueues.R;
import com.highsoftware96.songsqueues.models.local.Lineup;

public class LineupPresentationActivity extends AppCompatActivity {
    private Intent originalIntent;
    private Lineup selectedLineupToShow;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lineup_presentation);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.originalIntent = getIntent();
        this.selectedLineupToShow = (Lineup) originalIntent.getSerializableExtra(MainActivity.LINEUP_REFERRED_INTENT_EXTRA);
        this.toolbar.setTitle(selectedLineupToShow.Title);
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
}
