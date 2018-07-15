package com.highsoftware96.songsqueues.services;

public class LoginManager {
    // TODO: implementare la logica di login
    private static LoginManager instance = null;
    private boolean isLoggedIn = false;

    private LoginManager() {
        // TODO: ctor della classe
    }

    public static LoginManager getInstance() {
        if (instance == null)
            instance = new LoginManager();
        return instance;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }
}
