package com.example.mathappt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void Sign_In_Click(View v)
    {
        Intent intent=new Intent(this,SignIn.class);
        startActivity(intent);
        //Examplethread examplethread=new Examplethread(10);
        //examplethread.start();
    }
    public void Sign_Up_Click(View v)
    {
        Intent intent=new Intent(this,SignUp.class);
        startActivity(intent);
    }
    class Examplethread extends Thread{
        int seconds;
        Examplethread(int seconds)
        {
            this.seconds=seconds;
        }
        public void run()
        {
            for(int i=0;i<seconds;i++)
            {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}