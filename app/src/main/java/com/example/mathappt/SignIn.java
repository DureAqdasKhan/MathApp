package com.example.mathappt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignIn extends AppCompatActivity {
//DatabaseHelper databaseHelper;
EditText editText1;
EditText editText2;
String email;
String password;
FirebaseAuth auth;
DatabaseReference reference;
String name1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        //databaseHelper=new DatabaseHelper(this);
        editText1=findViewById(R.id.edit_email);
        editText2=findViewById(R.id.edit_password);
        editText2.setTransformationMethod(new AsteriskPasswordTransformationMethod());
        auth=FirebaseAuth.getInstance();
        reference= FirebaseDatabase.getInstance().getReference("Users");

    }

    public void Sign_In_Process(View view) {

        //Examplethread examplethread=new Examplethread(10);
        //examplethread.start();

        email=editText1.getText().toString();
        password=editText2.getText().toString();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Users user = dataSnapshot.getValue(Users.class);

                    //assert user != null;
                    if (user.email.equals(email)) {
                        name1=user.username;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        if (TextUtils.isEmpty(email)|| TextUtils.isEmpty(password))
        {
            Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_LONG).show();
        }
       else {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Intent intent = new Intent(getApplicationContext(), Topics.class);
                        intent.putExtra("name",name1);
                        startActivity(intent);

                    } else {
                        Toast.makeText(getApplicationContext(), "You cant login with this email or password", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

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