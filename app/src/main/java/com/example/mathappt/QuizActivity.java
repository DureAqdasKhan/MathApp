package com.example.mathappt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class QuizActivity extends AppCompatActivity {
    TextView q;
    Button a1, a2, a3;
    List<String> addQ = new ArrayList<>();
    List<String> subQ = new ArrayList<>();
    List<String> mulQ = new ArrayList<>();
    List<String> divQ = new ArrayList<>();
    List<String> addA = new ArrayList<>();
    List<String> subA = new ArrayList<>();
    List<String> mulA = new ArrayList<>();
    List<String> divA = new ArrayList<>();

    Random r = new Random();
    int rand, rand2, count = 0, index = 0, score = 0;
    String rs, rs2;

    //DatabaseHelper databaseHelper;

    FirebaseUser firebaseUser;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Intent intent=getIntent();
        String op = intent.getStringExtra("operation");
        final String email = intent.getStringExtra("email");

        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        final String current_id=firebaseUser.getUid();
        reference= FirebaseDatabase.getInstance().getReference("Scores").child(current_id);

        reference.keepSynced(true);

        final Intent intent2 = new Intent(this, Topics.class);
        intent2.putExtra("name",email);
        q = findViewById(R.id.question);
        a1 = findViewById(R.id.a1);
        a2 = findViewById(R.id.a2);
        a3 = findViewById(R.id.a3);

        addQ.add("2 + 5 = "); addQ.add("4 + 8 = "); addQ.add("9 + 0 = "); addQ.add("8 + 3 = "); addQ.add("12 + 5 = ");
        addA.add("7"); addA.add("12"); addA.add("9"); addA.add("11"); addA.add("17");

        subQ.add("5 - 2 = "); subQ.add("9 - 8 = "); subQ.add("10 - 6 = "); subQ.add("7 - 5 = "); subQ.add("14 - 7 = ");
        subA.add("3"); subA.add("1"); subA.add("4"); subA.add("2"); subA.add("7");

        mulQ.add("10 x 8 = "); mulQ.add("6 x 4 = "); mulQ.add("9 x 7 = "); mulQ.add("3 x 7 = "); mulQ.add("2 x 5 = ");
        mulA.add("80"); mulA.add("24"); mulA.add("63"); mulA.add("21"); mulA.add("10");

        divQ.add("6 / 2 = "); divQ.add("2 / 1 = "); divQ.add("8 / 4 = "); divQ.add("12 / 3 = "); divQ.add("25 / 5 = ");
        divA.add("3"); divA.add("2"); divA.add("2"); divA.add("4"); divA.add("5");

        //databaseHelper = new DatabaseHelper(this);

        if(op.equals("add"))
        {
            q.setText(addQ.get(index));
            rand = r.nextInt(21);

            rand2 = r.nextInt(21);
            rs = Integer.toString(rand);
            rs2 = Integer.toString(rand2);
            if(rs.equals(addA.get(index)))
            {
                rand=r.nextInt(21);
                rs=Integer.toString(rand);
            }
            if(rs2.equals(addA.get(index)))
            {
                rand2=r.nextInt(21);
                rs2=Integer.toString(rand2);
            }
            a1.setText(rs);
            a2.setText(rs2);
            a3.setText(addA.get(index));
            count++;

            a1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(a1.getText().equals(addA.get(index)))
                    {
                        Context context = getApplicationContext();
                        score++;
                        Toast toast = Toast.makeText(context, "Correct Answer!", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    else
                    {
                        Context context = getApplicationContext();

                        Toast toast = Toast.makeText(context, "Wrong Answer!", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    index++;
                    if(index > 4)
                    {
                        Context context = getApplicationContext();
                        String s = Integer.toString(score);
                        Toast toast = Toast.makeText(context, "You scored: " + s + "/5", Toast.LENGTH_LONG);
                        toast.show();
                        Store_Quiz_Details(current_id,"Addition",s);
                        //databaseHelper.insertScores(email ,"Addition", s);
                        startActivity(intent2);
                    }
                    else
                    {
                        q.setText(addQ.get(index));
                        rand = r.nextInt(21);
                        rand2 = r.nextInt(21);
                        rs = Integer.toString(rand);
                        rs2 = Integer.toString(rand2);
                        if(rs.equals(addA.get(index)))
                        {
                            rand=r.nextInt(21);
                            rs=Integer.toString(rand);
                        }
                        if(rs2.equals(addA.get(index)))
                        {
                            rand2=r.nextInt(21);
                            rs2=Integer.toString(rand2);
                        }
                        if(count == 0)
                        {
                            a1.setText(rs);
                            a2.setText(rs2);
                            a3.setText(addA.get(index));
                            count++;
                        }
                        else if(count == 1)
                        {
                            a1.setText(addA.get(index));
                            a2.setText(rs);
                            a3.setText(rs2);
                            count++;
                        }
                        else if(count == 2)
                        {
                            a1.setText(rs);
                            a2.setText(addA.get(index));
                            a3.setText(rs2);
                            count = 0;
                        }
                    }
                }
            });
            a2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(a2.getText().equals(addA.get(index)))
                    {
                        Context context = getApplicationContext();
                        score++;
                        Toast toast = Toast.makeText(context, "Correct Answer!", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    else
                    {
                        Context context = getApplicationContext();

                        Toast toast = Toast.makeText(context, "Wrong Answer!", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    index++;
                    if(index > 4)
                    {
                        Context context = getApplicationContext();
                        String s = Integer.toString(score);
                        Toast toast = Toast.makeText(context, "You scored: " + s + "/5", Toast.LENGTH_LONG);
                        toast.show();
                        //databaseHelper.insertScores(email ,"Addition", s);
                        Store_Quiz_Details(current_id,"Addition",s);
                        startActivity(intent2);
                    }
                    else
                    {
                        q.setText(addQ.get(index));
                        rand = r.nextInt(21);
                        rand2 = r.nextInt(21);
                        rs = Integer.toString(rand);
                        rs2 = Integer.toString(rand2);
                        if(rs.equals(addA.get(index)))
                        {
                            rand=r.nextInt(21);
                            rs=Integer.toString(rand);
                        }
                        if(rs2.equals(addA.get(index)))
                        {
                            rand2=r.nextInt(21);
                            rs2=Integer.toString(rand2);
                        }
                        if(count == 0)
                        {
                            a1.setText(rs);
                            a2.setText(rs2);
                            a3.setText(addA.get(index));
                            count++;
                        }
                        else if(count == 1)
                        {
                            a1.setText(addA.get(index));
                            a2.setText(rs);
                            a3.setText(rs2);
                            count++;
                        }
                        else if(count == 2)
                        {
                            a1.setText(rs);
                            a2.setText(addA.get(index));
                            a3.setText(rs2);
                            count = 0;
                        }
                    }
                }
            });
            a3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(a3.getText().equals(addA.get(index)))
                    {
                        Context context = getApplicationContext();
                        score++;
                        Toast toast = Toast.makeText(context, "Correct Answer!", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    else
                    {
                        Context context = getApplicationContext();

                        Toast toast = Toast.makeText(context, "Wrong Answer!", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    index++;
                    if(index > 4)
                    {
                        Context context = getApplicationContext();
                        String s = Integer.toString(score);
                        Toast toast = Toast.makeText(context, "You scored: " + s + "/5", Toast.LENGTH_LONG);
                        toast.show();
                        //databaseHelper.insertScores(email ,"Addition", s);
                        Store_Quiz_Details(current_id,"Addition",s);
                        startActivity(intent2);
                    }
                    else
                    {
                        q.setText(addQ.get(index));
                        rand = r.nextInt(21);
                        rand2 = r.nextInt(21);
                        rs = Integer.toString(rand);
                        rs2 = Integer.toString(rand2);
                        if(rs.equals(addA.get(index)))
                        {
                            rand=r.nextInt(21);
                            rs=Integer.toString(rand);
                        }
                        if(rs2.equals(addA.get(index)))
                        {
                            rand2=r.nextInt(21);
                            rs2=Integer.toString(rand2);
                        }
                        if(count == 0)
                        {
                            a1.setText(rs);
                            a2.setText(rs2);
                            a3.setText(addA.get(index));
                            count++;
                        }
                        else if(count == 1)
                        {
                            a1.setText(addA.get(index));
                            a2.setText(rs);
                            a3.setText(rs2);
                            count++;
                        }
                        else if(count == 2)
                        {
                            a1.setText(rs);
                            a2.setText(addA.get(index));
                            a3.setText(rs2);
                            count = 0;
                        }
                    }
                }
            });
        }
        if(op.equals("sub"))
        {
            q.setText(subQ.get(index));
            rand = r.nextInt(21);
            rand2 = r.nextInt(21);
            rs = Integer.toString(rand);
            rs2 = Integer.toString(rand2);
            if(rs.equals(addA.get(index)))
            {
                rand=r.nextInt(21);
                rs=Integer.toString(rand);
            }
            if(rs2.equals(addA.get(index)))
            {
                rand2=r.nextInt(21);
                rs2=Integer.toString(rand2);
            }
            a1.setText(rs);
            a2.setText(rs2);
            a3.setText(subA.get(index));
            count++;

            a1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(a1.getText().equals(subA.get(index)))
                    {
                        Context context = getApplicationContext();
                        score++;
                        Toast toast = Toast.makeText(context, "Correct Answer!", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    else
                    {
                        Context context = getApplicationContext();

                        Toast toast = Toast.makeText(context, "Wrong Answer!", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    index++;
                    if(index > 4)
                    {
                        Context context = getApplicationContext();
                        String s = Integer.toString(score);
                        Toast toast = Toast.makeText(context, "You scored: " + s + "/5", Toast.LENGTH_LONG);
                        toast.show();
                        //databaseHelper.insertScores(email ,"Subtraction", s);
                        Store_Quiz_Details(current_id,"Subtraction",s);
                        startActivity(intent2);
                    }
                    else
                    {
                        q.setText(subQ.get(index));
                        rand = r.nextInt(21);
                        rand2 = r.nextInt(21);
                        rs = Integer.toString(rand);
                        rs2 = Integer.toString(rand2);
                        if(rs.equals(addA.get(index)))
                        {
                            rand=r.nextInt(21);
                            rs=Integer.toString(rand);
                        }
                        if(rs2.equals(addA.get(index)))
                        {
                            rand2=r.nextInt(21);
                            rs2=Integer.toString(rand2);
                        }
                        if(count == 0)
                        {
                            a1.setText(rs);
                            a2.setText(rs2);
                            a3.setText(subA.get(index));
                            count++;
                        }
                        else if(count == 1)
                        {
                            a1.setText(subA.get(index));
                            a2.setText(rs);
                            a3.setText(rs2);
                            count++;
                        }
                        else if(count == 2)
                        {
                            a1.setText(rs);
                            a2.setText(subA.get(index));
                            a3.setText(rs2);
                            count = 0;
                        }
                    }
                }
            });
            a2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(a2.getText().equals(subA.get(index)))
                    {
                        Context context = getApplicationContext();
                        score++;
                        Toast toast = Toast.makeText(context, "Correct Answer!", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    else
                    {
                        Context context = getApplicationContext();

                        Toast toast = Toast.makeText(context, "Wrong Answer!", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    index++;
                    if(index > 4)
                    {
                        Context context = getApplicationContext();
                        String s = Integer.toString(score);
                        Toast toast = Toast.makeText(context, "You scored: " + s + "/5", Toast.LENGTH_LONG);
                        toast.show();
                        //databaseHelper.insertScores(email ,"Subtraction", s);
                        Store_Quiz_Details(current_id,"Subtraction",s);
                        startActivity(intent2);
                    }
                    else
                    {
                        q.setText(subQ.get(index));
                        rand = r.nextInt(21);
                        rand2 = r.nextInt(21);
                        rs = Integer.toString(rand);
                        rs2 = Integer.toString(rand2);
                        if(rs.equals(addA.get(index)))
                        {
                            rand=r.nextInt(21);
                            rs=Integer.toString(rand);
                        }
                        if(rs2.equals(addA.get(index)))
                        {
                            rand2=r.nextInt(21);
                            rs2=Integer.toString(rand2);
                        }
                        if(count == 0)
                        {
                            a1.setText(rs);
                            a2.setText(rs2);
                            a3.setText(subA.get(index));
                            count++;
                        }
                        else if(count == 1)
                        {
                            a1.setText(subA.get(index));
                            a2.setText(rs);
                            a3.setText(rs2);
                            count++;
                        }
                        else if(count == 2)
                        {
                            a1.setText(rs);
                            a2.setText(subA.get(index));
                            a3.setText(rs2);
                            count = 0;
                        }
                    }
                }
            });
            a3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(a3.getText().equals(subA.get(index)))
                    {
                        Context context = getApplicationContext();
                        score++;
                        Toast toast = Toast.makeText(context, "Correct Answer!", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    else
                    {
                        Context context = getApplicationContext();

                        Toast toast = Toast.makeText(context, "Wrong Answer!", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    index++;
                    if(index > 4)
                    {
                        Context context = getApplicationContext();
                        String s = Integer.toString(score);
                        Toast toast = Toast.makeText(context, "You scored: " + s + "/5", Toast.LENGTH_LONG);
                        toast.show();
                        //databaseHelper.insertScores(email ,"Subtraction", s);
                        Store_Quiz_Details(current_id,"Subtraction",s);
                        startActivity(intent2);
                    }
                    else
                    {
                        q.setText(subQ.get(index));
                        rand = r.nextInt(21);
                        rand2 = r.nextInt(21);
                        rs = Integer.toString(rand);
                        rs2 = Integer.toString(rand2);
                        if(rs.equals(addA.get(index)))
                        {
                            rand=r.nextInt(21);
                            rs=Integer.toString(rand);
                        }
                        if(rs2.equals(addA.get(index)))
                        {
                            rand2=r.nextInt(21);
                            rs2=Integer.toString(rand2);
                        }
                        if(count == 0)
                        {
                            a1.setText(rs);
                            a2.setText(rs2);
                            a3.setText(subA.get(index));
                            count++;
                        }
                        else if(count == 1)
                        {
                            a1.setText(subA.get(index));
                            a2.setText(rs);
                            a3.setText(rs2);
                            count++;
                        }
                        else if(count == 2)
                        {
                            a1.setText(rs);
                            a2.setText(subA.get(index));
                            a3.setText(rs2);
                            count = 0;
                        }
                    }
                }
            });
        }
        if(op.equals("mul"))
        {
            q.setText(mulQ.get(index));
            rand = r.nextInt(21);
            rand2 = r.nextInt(21);
            rs = Integer.toString(rand);
            rs2 = Integer.toString(rand2);
            if(rs.equals(addA.get(index)))
            {
                rand=r.nextInt(21);
                rs=Integer.toString(rand);
            }
            if(rs2.equals(addA.get(index)))
            {
                rand2=r.nextInt(21);
                rs2=Integer.toString(rand2);
            }
            a1.setText(rs);
            a2.setText(rs2);
            a3.setText(mulA.get(index));
            count++;

            a1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(a1.getText().equals(mulA.get(index)))
                    {
                        Context context = getApplicationContext();
                        score++;
                        Toast toast = Toast.makeText(context, "Correct Answer!", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    else
                    {
                        Context context = getApplicationContext();

                        Toast toast = Toast.makeText(context, "Wrong Answer!", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    index++;
                    if(index > 4)
                    {
                        Context context = getApplicationContext();
                        String s = Integer.toString(score);
                        Toast toast = Toast.makeText(context, "You scored: " + s + "/5", Toast.LENGTH_LONG);
                        toast.show();
                        //databaseHelper.insertScores(email ,"Multiplication", s);
                        Store_Quiz_Details(current_id,"Multiplication",s);
                        startActivity(intent2);
                    }
                    else
                    {
                        q.setText(mulQ.get(index));
                        rand = r.nextInt(21);
                        rand2 = r.nextInt(21);
                        rs = Integer.toString(rand);
                        rs2 = Integer.toString(rand2);
                        if(rs.equals(addA.get(index)))
                        {
                            rand=r.nextInt(21);
                            rs=Integer.toString(rand);
                        }
                        if(rs2.equals(addA.get(index)))
                        {
                            rand2=r.nextInt(21);
                            rs2=Integer.toString(rand2);
                        }
                        if(count == 0)
                        {
                            a1.setText(rs);
                            a2.setText(rs2);
                            a3.setText(mulA.get(index));
                            count++;
                        }
                        else if(count == 1)
                        {
                            a1.setText(mulA.get(index));
                            a2.setText(rs);
                            a3.setText(rs2);
                            count++;
                        }
                        else if(count == 2)
                        {
                            a1.setText(rs);
                            a2.setText(mulA.get(index));
                            a3.setText(rs2);
                            count = 0;
                        }
                    }
                }
            });
            a2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(a2.getText().equals(mulA.get(index)))
                    {
                        Context context = getApplicationContext();
                        score++;
                        Toast toast = Toast.makeText(context, "Correct Answer!", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    else
                    {
                        Context context = getApplicationContext();

                        Toast toast = Toast.makeText(context, "Wrong Answer!", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    index++;
                    if(index > 4)
                    {
                        Context context = getApplicationContext();
                        String s = Integer.toString(score);
                        Toast toast = Toast.makeText(context, "You scored: " + s + "/5", Toast.LENGTH_LONG);
                        toast.show();
                        //databaseHelper.insertScores(email ,"Multiplication", s);
                        Store_Quiz_Details(current_id,"Multiplication",s);
                        startActivity(intent2);
                    }
                    else
                    {
                        q.setText(mulQ.get(index));
                        rand = r.nextInt(21);
                        rand2 = r.nextInt(21);
                        rs = Integer.toString(rand);
                        rs2 = Integer.toString(rand2);
                        if(rs.equals(addA.get(index)))
                        {
                            rand=r.nextInt(21);
                            rs=Integer.toString(rand);
                        }
                        if(rs2.equals(addA.get(index)))
                        {
                            rand2=r.nextInt(21);
                            rs2=Integer.toString(rand2);
                        }
                        if(count == 0)
                        {
                            a1.setText(rs);
                            a2.setText(rs2);
                            a3.setText(mulA.get(index));
                            count++;
                        }
                        else if(count == 1)
                        {
                            a1.setText(mulA.get(index));
                            a2.setText(rs);
                            a3.setText(rs2);
                            count++;
                        }
                        else if(count == 2)
                        {
                            a1.setText(rs);
                            a2.setText(mulA.get(index));
                            a3.setText(rs2);
                            count = 0;
                        }
                    }
                }
            });
            a3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(a3.getText().equals(mulA.get(index)))
                    {
                        Context context = getApplicationContext();
                        score++;
                        Toast toast = Toast.makeText(context, "Correct Answer!", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    else
                    {
                        Context context = getApplicationContext();

                        Toast toast = Toast.makeText(context, "Wrong Answer!", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    index++;
                    if(index > 4)
                    {
                        Context context = getApplicationContext();
                        String s = Integer.toString(score);
                        Toast toast = Toast.makeText(context, "You scored: " + s + "/5", Toast.LENGTH_LONG);
                        toast.show();
                        //databaseHelper.insertScores(email ,"Multiplication", s);
                        Store_Quiz_Details(current_id,"Multiplication",s);
                        startActivity(intent2);
                    }
                    else
                    {
                        q.setText(mulQ.get(index));
                        rand = r.nextInt(21);
                        rand2 = r.nextInt(21);
                        rs = Integer.toString(rand);
                        rs2 = Integer.toString(rand2);
                        if(rs.equals(addA.get(index)))
                        {
                            rand=r.nextInt(21);
                            rs=Integer.toString(rand);
                        }
                        if(rs2.equals(addA.get(index)))
                        {
                            rand2=r.nextInt(21);
                            rs2=Integer.toString(rand2);
                        }
                        if(count == 0)
                        {
                            a1.setText(rs);
                            a2.setText(rs2);
                            a3.setText(mulA.get(index));
                            count++;
                        }
                        else if(count == 1)
                        {
                            a1.setText(mulA.get(index));
                            a2.setText(rs);
                            a3.setText(rs2);
                            count++;
                        }
                        else if(count == 2)
                        {
                            a1.setText(rs);
                            a2.setText(mulA.get(index));
                            a3.setText(rs2);
                            count = 0;
                        }
                    }
                }
            });
        }
        if(op.equals("div"))
        {
            q.setText(divQ.get(index));
            rand = r.nextInt(21);
            rand2 = r.nextInt(21);
            rs = Integer.toString(rand);
            rs2 = Integer.toString(rand2);
            if(rs.equals(addA.get(index)))
            {
                rand=r.nextInt(21);
                rs=Integer.toString(rand);
            }
            if(rs2.equals(addA.get(index)))
            {
                rand2=r.nextInt(21);
                rs2=Integer.toString(rand2);
            }
            a1.setText(rs);
            a2.setText(rs2);
            a3.setText(divA.get(index));
            count++;

            a1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(a1.getText().equals(divA.get(index)))
                    {
                        Context context = getApplicationContext();
                        score++;
                        Toast toast = Toast.makeText(context, "Correct Answer!", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    else
                    {
                        Context context = getApplicationContext();

                        Toast toast = Toast.makeText(context, "Wrong Answer!", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    index++;
                    if(index > 4)
                    {
                        Context context = getApplicationContext();
                        String s = Integer.toString(score);
                        Toast toast = Toast.makeText(context, "You scored: " + s + "/5", Toast.LENGTH_LONG);
                        toast.show();
                        //databaseHelper.insertScores(email ,"Division", s);
                        Store_Quiz_Details(current_id,"Division",s);
                        startActivity(intent2);
                    }
                    else
                    {
                        q.setText(divQ.get(index));
                        rand = r.nextInt(21);
                        rand2 = r.nextInt(21);
                        rs = Integer.toString(rand);
                        rs2 = Integer.toString(rand2);
                        if(rs.equals(addA.get(index)))
                        {
                            rand=r.nextInt(21);
                            rs=Integer.toString(rand);
                        }
                        if(rs2.equals(addA.get(index)))
                        {
                            rand2=r.nextInt(21);
                            rs2=Integer.toString(rand2);
                        }
                        if(count == 0)
                        {
                            a1.setText(rs);
                            a2.setText(rs2);
                            a3.setText(divA.get(index));
                            count++;
                        }
                        else if(count == 1)
                        {
                            a1.setText(divA.get(index));
                            a2.setText(rs);
                            a3.setText(rs2);
                            count++;
                        }
                        else if(count == 2)
                        {
                            a1.setText(rs);
                            a2.setText(divA.get(index));
                            a3.setText(rs2);
                            count = 0;
                        }
                    }
                }
            });
            a2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(a2.getText().equals(divA.get(index)))
                    {
                        Context context = getApplicationContext();
                        score++;
                        Toast toast = Toast.makeText(context, "Correct Answer!", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    else
                    {
                        Context context = getApplicationContext();

                        Toast toast = Toast.makeText(context, "Wrong Answer!", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    index++;
                    if(index > 4)
                    {
                        Context context = getApplicationContext();
                        String s = Integer.toString(score);
                        Toast toast = Toast.makeText(context, "You scored: " + s + "/5", Toast.LENGTH_LONG);
                        toast.show();
                        //databaseHelper.insertScores(email ,"Division", s);
                        Store_Quiz_Details(current_id,"Division",s);
                        startActivity(intent2);
                    }
                    else
                    {
                        q.setText(divQ.get(index));
                        rand = r.nextInt(21);
                        rand2 = r.nextInt(21);
                        rs = Integer.toString(rand);
                        rs2 = Integer.toString(rand2);
                        if(rs.equals(addA.get(index)))
                        {
                            rand=r.nextInt(21);
                            rs=Integer.toString(rand);
                        }
                        if(rs2.equals(addA.get(index)))
                        {
                            rand2=r.nextInt(21);
                            rs2=Integer.toString(rand2);
                        }
                        if(count == 0)
                        {
                            a1.setText(rs);
                            a2.setText(rs2);
                            a3.setText(divA.get(index));
                            count++;
                        }
                        else if(count == 1)
                        {
                            a1.setText(divA.get(index));
                            a2.setText(rs);
                            a3.setText(rs2);
                            count++;
                        }
                        else if(count == 2)
                        {
                            a1.setText(rs);
                            a2.setText(divA.get(index));
                            a3.setText(rs2);
                            count = 0;
                        }
                    }
                }
            });
            a3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(a3.getText().equals(divA.get(index)))
                    {
                        Context context = getApplicationContext();
                        score++;
                        Toast toast = Toast.makeText(context, "Correct Answer!", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    else
                    {
                        Context context = getApplicationContext();

                        Toast toast = Toast.makeText(context, "Wrong Answer!", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    index++;
                    if(index > 4)
                    {
                        Context context = getApplicationContext();
                        String s = Integer.toString(score);
                        Toast toast = Toast.makeText(context, "You scored: " + s + "/5", Toast.LENGTH_LONG);
                        toast.show();
                        //databaseHelper.insertScores(email ,"Division", s);
                        Store_Quiz_Details(current_id,"Division",s);
                        startActivity(intent2);
                    }
                    else
                    {
                        q.setText(divQ.get(index));
                        rand = r.nextInt(21);
                        rand2 = r.nextInt(21);
                        rs = Integer.toString(rand);
                        rs2 = Integer.toString(rand2);
                        if(rs.equals(addA.get(index)))
                        {
                            rand=r.nextInt(21);
                            rs=Integer.toString(rand);
                        }
                        if(rs2.equals(addA.get(index)))
                        {
                            rand2=r.nextInt(21);
                            rs2=Integer.toString(rand2);
                        }
                        if(count == 0)
                        {
                            a1.setText(rs);
                            a2.setText(rs2);
                            a3.setText(divA.get(index));
                            count++;
                        }
                        else if(count == 1)
                        {
                            a1.setText(divA.get(index));
                            a2.setText(rs);
                            a3.setText(rs2);
                            count++;
                        }
                        else if(count == 2)
                        {
                            a1.setText(rs);
                            a2.setText(divA.get(index));
                            a3.setText(rs2);
                            count = 0;
                        }
                    }
                }
            });
        }
    }
    void Store_Quiz_Details(String id,String type,String score)
    {
        DatabaseReference reference=FirebaseDatabase.getInstance().getReference();
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("id",id);
        hashMap.put("type",type);
        hashMap.put("score",score);
        reference.child("Scores").push().setValue(hashMap);
    }
}