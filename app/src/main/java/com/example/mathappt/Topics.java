package com.example.mathappt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.AlarmManagerCompat;
import androidx.core.app.NotificationCompatSideChannelService;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class Topics extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    String current_email;
    TimePicker timePicker;
    String name;
    String temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics);
        Intent intent=getIntent();
        name=intent.getStringExtra("name");

       // current_email=intent.getStringExtra("email");
        System.out.println("CURRENT EMAIL*****************************"+current_email);
        bottomNavigationView=findViewById(R.id.nav_bar);
        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavigationMethod);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,new CountingFragment()).commit();
        System.out.println(name);
        Topics.this.setTitle(name);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_bar, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case  R.id.logout:
                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(this,MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.settings:
                Intent intent1=new Intent(this,ChangePassword.class);
                intent1.putExtra("name",name);
                startActivity(intent1);
                break;
            case R.id.share_progress:
                openDialog();
                break;
            case R.id.ps:
                Intent intent2 = new Intent(this, Alarm.class);
                startActivity(intent2);
                break;

                /*Calendar calendar = Calendar.getInstance();

                if(Build.VERSION.SDK_INT >= 23) {
                    calendar.set(
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH),
                            timePicker.getHour(),
                            timePicker.getMinute(),
                            0);
                }
                else
                {
                    calendar.set(
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH),
                            timePicker.getCurrentHour(),
                            timePicker.getCurrentMinute(),
                            0);
                }
                setAlarm(calendar.getTimeInMillis());
                break;**/
        }
        return true;
    }

    public void openDialog()
    {
        DialogBox dialogBox = new DialogBox();
        dialogBox.show(getSupportFragmentManager(), "Dialog");
    }

    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavigationMethod=new
            BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    System.out.println(name);
                    Topics.this.setTitle(name);
                    Fragment fragment=null;
                    switch(item.getItemId())
                    {
                        case R.id.counting:
                            fragment=new CountingFragment();
                            break;
                        case R.id.tables:
                            fragment=new TablesFragment();
                            break;
                        case R.id.quiz:
                            fragment=new QuizMainFragment(name);
                            break;
                        case R.id.basic:
                            fragment=new BasicFragment();
                            break;
                        case R.id.progress:
                            fragment=new Progress(current_email);
                            break;
                    }
                    if (fragment != null) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
                    }
                    return true;
                }
            };
}