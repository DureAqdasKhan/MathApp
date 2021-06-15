package com.example.mathappt;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import javax.crypto.spec.OAEPParameterSpec;

public class DialogBox extends AppCompatDialogFragment {

    ArrayList<String> options = new ArrayList<>();
    List<Scores> progress = new ArrayList<>();
    FirebaseUser firebaseUser;
    DatabaseReference reference;
    String msg;
    int progress_total=0;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        final String current_id = firebaseUser.getUid();

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
                        progress_total=progress_total+Integer.parseInt(scores.score);
                        progress.add(scores);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        options.add("EMAIL");
        options.add("FACEBOOK");

        builder.setTitle("Select Option")
                .setItems(options.toArray(new String[0]), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if(which == 0)
                        {
                            Intent i = new Intent(Intent.ACTION_SEND);
                            i.setType("text/plain");
                            String[] array = {"aizanaveed24@gmail.com"};
                            i.putExtra(Intent.EXTRA_EMAIL,array);
                            i.putExtra(Intent.EXTRA_SUBJECT, "Math App Score");
                            if(progress.isEmpty())
                            {
                                msg = "Hey i just joined MathApp and have'nt done any quiz as of yet! Join me!";
                            }
                            else
                            {
                                msg = "Hey! My total score on Math App is " + progress_total;
                                        //progress.get(progress.size()-1).score;
                            }
                            i.putExtra(Intent.EXTRA_TEXT, msg);
                            if(i.resolveActivity((getActivity().getPackageManager()))!=null)
                            {
                                startActivity(i);
                            }
                        }
                        if(which == 1)
                        {
                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setData(Uri.parse("http://www.facebook.com/"));
                            startActivity(i);
                        }
                    }
                });

        return builder.create();
    }
}
