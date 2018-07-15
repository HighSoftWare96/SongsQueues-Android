package com.highsoftware96.songsqueues.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.highsoftware96.songsqueues.R;
import com.highsoftware96.songsqueues.services.LoginManager;

public class EntryActivity extends AppCompatActivity {

    private LoginManager loginManager = LoginManager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent launchIntent = new Intent();
        Class<?> launchActivity;
        // quale activity devo far partire??
        String className = chooseTheActivityToShow(launchIntent);
        try {
            // cerco la activity inserita
            launchActivity = Class.forName(className);
        } catch (ClassNotFoundException e) {
            // non trova la classe apro di default l'activity di login
            launchActivity = LoginAccessActivity.class;
        }
        // imposto l'activity dell'intent
        launchIntent.setClass(getApplicationContext(), launchActivity);
        // faccio partire l'activity con animazione alterniva
        startActivity(launchIntent);
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
        finish();
    }

    private String chooseTheActivityToShow(Intent lauchIntent) {
        // se è loggato apro la home page
        if (loginManager.isLoggedIn()) {
            return MainActivity.class.getName();
        } else
            // altrimenti apro l'activity di login
            return LoginAccessActivity.class.getName();
    }
}
