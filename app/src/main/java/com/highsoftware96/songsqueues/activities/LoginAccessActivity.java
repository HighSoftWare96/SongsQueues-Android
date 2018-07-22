package com.highsoftware96.songsqueues.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.highsoftware96.songsqueues.R;
import com.highsoftware96.songsqueues.services.LoginManager;

public class LoginAccessActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText pswEditText;
    private Button facebookLoginBtn;
    private Button googleLoginBtn;
    private Button loginBtn;
    private ProgressBar progressBar;
    private LinearLayout loginFormLayout;
    private LoginManager loginManager = LoginManager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // imposto il login in modo che non abbia l'action bar in alto
        this.setTitle(getString(R.string.login_title));
        setContentView(R.layout.activity_login_access);
        usernameEditText = findViewById(R.id.usernameInput);
        pswEditText = findViewById(R.id.passwordInput);
        facebookLoginBtn = findViewById(R.id.facebookLoginBtn);
        googleLoginBtn = findViewById(R.id.googleLoginBtn);
        loginBtn = findViewById(R.id.loginBtn);
        progressBar = findViewById(R.id.loginProgressBar);
        loginFormLayout = findViewById(R.id.loginFormLayout);
    }


    // PRIMITIVE per le views
    /// Il buttone di login è stato clickato
    public void loginIntoApp(View view) {
        // TODO: controllare le credenziali per loggarsi
        setOperationLogRunning(true);
        Intent intentToStartMainActivity = new Intent(this, MainActivity.class);
        // CHIAMO QUESTO INTENT SENZA FAR MEMORIZZARE CHE è STATO INSERITO QUINDI NON TORNERò PIU
        // SU QUESTA SCHERMATA COL BACK
        startActivity(intentToStartMainActivity);
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
        this.finish();
    }

    // PRIMITIVE DI LAYOUT
    private void setOperationLogRunning(boolean somethingRunnning) {
        if (somethingRunnning) {
            this.loginFormLayout.setVisibility(View.INVISIBLE);
            this.progressBar.setVisibility(View.VISIBLE);
        } else {
            this.loginFormLayout.setVisibility(View.VISIBLE);
            this.progressBar.setVisibility(View.INVISIBLE);
        }
    }
}
