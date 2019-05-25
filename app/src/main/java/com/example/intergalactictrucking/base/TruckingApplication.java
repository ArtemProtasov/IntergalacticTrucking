package com.example.intergalactictrucking.base;

import android.app.Application;

import com.google.firebase.FirebaseApp;

class TruckingApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
    }
}
