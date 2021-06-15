package com.example.mathappt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SignUp extends AppCompatActivity {
    //DatabaseHelper databaseHelper;
    EditText email;
    EditText password;
    EditText name;
    Spinner spinner;
    String gender;
    String email1;
    String pass1;
    String name1;
    FirebaseAuth auth;
    DatabaseReference reference;
    Handler handler=new Handler();
    final String mes1="All fields are required";
    final String mes2="password must be at least 6 characters";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //databaseHelper=new DatabaseHelper(this);
        email=(EditText)findViewById(R.id.edit_signup_email);
        password=(EditText)findViewById(R.id.edit_signup_password);
        password.setTransformationMethod(new AsteriskPasswordTransformationMethod());
        name=(EditText)findViewById(R.id.full_name);
        spinner=(Spinner)findViewById(R.id.spinner1);
        auth=FirebaseAuth.getInstance();
    }
    public void Sign_Up_Process(View v)
    {
        try {
            Thread thread=new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("inside thread");
                        email1=email.getText().toString();
                        name1=name.getText().toString();
                        pass1=password.getText().toString();
                        gender=spinner.getSelectedItem().toString();
                        if (TextUtils.isEmpty(name1) || TextUtils.isEmpty(email1) || TextUtils.isEmpty(pass1)||TextUtils.isEmpty(gender)){
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), mes1, Toast.LENGTH_LONG).show();
                                }
                            });


                        } else if (pass1.length() < 6 ){
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(),mes2 , Toast.LENGTH_LONG).show();
                                }
                            });

                        } else {
                            register(name1,email1,pass1,gender);
                        }
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
    private void register(final String name, String email, final String password, final String gender)
    {
        //Examplethread examplethread=new Examplethread(10);
        //examplethread.start();
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    FirebaseUser firebaseUser=auth.getCurrentUser();
                    assert firebaseUser != null;
                    String userid=firebaseUser.getUid();
                    reference= FirebaseDatabase.getInstance().getReference("Users").child(userid);
                    HashMap<String,String> hashMap=new HashMap<>();
                    hashMap.put("id",userid);
                    hashMap.put("email",email1);
                    hashMap.put("password",password);
                    hashMap.put("gender",gender);
                    hashMap.put("username",name);

                    reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Intent intent=new Intent(getApplicationContext(),Topics.class);
                                intent.putExtra("name",name);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"You cant register this email or password",Toast.LENGTH_LONG).show();
                }
            }
        });
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
                System.out.println(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}