package com.example.mathappt;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class OfflineFirebaseHandler extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
