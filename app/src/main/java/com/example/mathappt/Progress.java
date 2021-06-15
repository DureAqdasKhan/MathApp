package com.example.mathappt;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Progress extends Fragment {

    RecyclerView recyclerView;
    List<Scores> progress = new ArrayList<>();
    String email;
    DatabaseHelper databaseHelper;
    FirebaseUser firebaseUser;
    DatabaseReference reference;

    public Progress(String email) {
        this.email = email;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_progress, container, false);
        Examplethread examplethread=new Examplethread(10);
        examplethread.start();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        final String current_id = firebaseUser.getUid();

        /*databaseHelper = new DatabaseHelper(getContext());
        Cursor c = databaseHelper.getScores(email);
        String temp_email;
        String temp_type;
        String temp_score;
        if(c.getCount()>0)
        {
            if(c.moveToFirst())
            {
                do
                {
                    temp_email = c.getString(0);
                    temp_type = c.getString(1);
                    temp_score = c.getString(2);
                    if(email.contentEquals(temp_email))
                    {
                        Scores s = new Scores(current_id,temp_type, temp_score);
                        progress.add(s);
                    }
                }while(c.moveToNext());
            }
        }
        if(progress.size() == 0)
        {
            Scores s=new Scores(current_id,"no progress yet","0");
            progress.add(s);
        }**/

        reference = FirebaseDatabase.getInstance().getReference("Scores");
        reference.keepSynced(true);

        reference.addValueEventListener(new ValueEventListener() {
            boolean flag = true;

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                progress.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Scores scores = snapshot.getValue(Scores.class);
                    System.out.println("ID:"+scores.id+" SCORE: "+scores.score+" TYPE: "+scores.type);
                    if (scores.id.equals(current_id)) {
                        progress.add(scores);
                    }
                }
                if(progress.size()==0)
                {
                    Scores scores=new Scores("0","No Progress Yet!","0");
                    progress.add(scores);
                }
                recyclerView = (RecyclerView) view.findViewById(R.id.recycler_progress);
                ProgressAdapter progressAdapter = new ProgressAdapter(progress);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                recyclerView.setAdapter(progressAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
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