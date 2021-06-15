package com.example.mathappt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CountingSong extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counting_song);
    }

    public void start(View view) {
        startService(new Intent(getApplicationContext(),MyService.class));
    }

    public void stop(View view) {
        stopService(new Intent(getApplicationContext(),MyService.class));
    }
}