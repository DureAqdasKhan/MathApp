package com.example.mathappt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.EventListener;

public class ChangePassword extends AppCompatActivity {
    MaterialEditText password,updated_password,email;
    DatabaseReference reference;
    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth;
    String current_id;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        password=findViewById(R.id.password);
        password.setTransformationMethod(new AsteriskPasswordTransformationMethod());
        updated_password=findViewById(R.id.up_password);
        updated_password.setTransformationMethod(new AsteriskPasswordTransformationMethod());
        email=findViewById(R.id.email);
        email.setTransformationMethod(new AsteriskPasswordTransformationMethod());
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        current_id = firebaseUser.getUid();
        reference=FirebaseDatabase.getInstance().getReference("Users");
        Intent intent=getIntent();
        name=intent.getStringExtra("name");
    }

    public void Update(View view) {
        final String pass = password.getText().toString();
        // System.out.println(pass);
        final String update_pass = updated_password.getText().toString();
        final String email1 = email.getText().toString();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Users users = dataSnapshot.getValue(Users.class);
                    if (users.id.equals(current_id))
                    {
                        if (!TextUtils.isEmpty(pass) && !TextUtils.isEmpty(update_pass) && !TextUtils.isEmpty(email1)) {
                            if (users.password.equals(pass))
                            {
                                if (!update_pass.equals(email1)) {
                                    Toast.makeText(getApplicationContext(), "Passwords dont match", Toast.LENGTH_LONG).show();
                                }
                                else
                                {
                                    firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                                    firebaseUser.updatePassword(update_pass).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            String id = firebaseUser.getUid();
                                            reference.child(id).child("password").setValue(update_pass); //updating in db also
                                            Toast.makeText(getApplicationContext(), "Password Updated", Toast.LENGTH_LONG).show();
                                            Intent intent = new Intent(getApplicationContext(), Topics.class);
                                            intent.putExtra("name",name);
                                            startActivity(intent);
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(getApplicationContext(), "Password could not be updated", Toast.LENGTH_LONG).show();
                                        }
                                    });

                                }
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(), "Current Password is not correct", Toast.LENGTH_LONG).show();
                            }
                        }
                        else
                        {
                                Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}