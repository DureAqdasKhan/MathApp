package com.example.mathappt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class BasicMathActivity extends AppCompatActivity {
RelativeLayout relativeLayout;
RecyclerView recyclerView;
BasicAdapter basicAdapter;
String [] add={"The addition is taking two or more numbers and adding them together, that is, it is the total sum of 2 or more numbers.Example: if we take 4 cookies and take 4 more cookies we get 8 cookies","How many apples are there in all? There are 7 apples in one basket and 4 apples in the other. So, we add 7 and 4 to find the total number of apples. To add 7 and 4, we can count forward 4 steps from 7. The symbol used to indicate Addition is + (plus symbol). So, 7 and 4 can be written as 7 + 4."};
int [] image={R.drawable.addexmple,R.drawable.appleexample};
String [] sub={"What is to subtract? In math, to subtract means to take away from a group or a number of things. When we subtract, the number of things in the group reduce or become less. Learn that subtraction is the inverse of addition, so, for example, 6 + 4 = 10 and 10 – 4 = 6. 'Eight little blue birds sitting on a tree,\n" +  "Five flew away, and then there were three.'"," The minuend, subtrahend and difference are parts of a subtraction problem. In the subtraction problem, 7 – 3 = 4, the number 7 is the minuend, the number 3 is the subtrahend and the number 4 is the difference" };
int [] sub_image={R.drawable.subt,R.drawable.subexample};
String [] div={"Division is breaking a number up into an equal number of parts. Example: 20 divided by 4 = ? If you take 20 things and put them into four equal sized groups, there will be 5 things in each group. The answer is 5. 20 divided by 4 = 5.","Each part of a division equation has a name. The three main names are the dividend, the divisor, and the quotient. Dividend - The dividend is the number you are dividing up Divisor - The divisor is the number you are dividing by Quotient - The quotient is the answer Dividend ÷ Divisor = Quotient Example: In the problem 75 ÷ 9 = 8 Dividend = 75 Divisor = 9 Quotient = 8 and Remainder= 3"};
int [] div_image={R.drawable.divexmple,R.drawable.download};
String [] mul={"Multiplication is when you take one number and add it together a number of times. Example: 2 multiplied by 5 = 2 + 2 + 2 + 2 = 10. We took the number 2 and added it together 5 times. This is why multiplication is sometimes called times. here are a few different signs that people use to indicate multiplication. The most common is the 'x' sign, but sometimes people use a '*' sign or other symbols.","Lets see another example of 4x2=8."};
int [] mul_image={R.drawable.mulexmple1,R.drawable.mulexmple2};
String [] sqrt={"1      1.000 " + "\n" + "2     1.414" + "\n" + "3     1.732" + "4     2.000" + "5     2.236" + "6     2.449" + "7     2.646" + "8     2.828" + "9     3.000" + "10        3.162"};
int [] sqrt_image = {R.drawable.sqrt, 0};


@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_basic_math);
        Intent intent=getIntent();
        String op=intent.getStringExtra("operation");
        recyclerView=findViewById(R.id.basic_math_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        if(op.contentEquals("add"))
        {
            relativeLayout=findViewById(R.id.basic_math);
            relativeLayout.setBackground(ContextCompat.getDrawable(this,R.drawable.addition));
            String vid="android.resource://"+getPackageName()+"/"+R.raw.addition_tutorial;
            basicAdapter=new BasicAdapter(add,image,this,vid);
            recyclerView.setAdapter(basicAdapter);
        }
        else if(op.contentEquals("sub"))
        {
            relativeLayout=findViewById(R.id.basic_math);
            relativeLayout.setBackground(ContextCompat.getDrawable(this,R.drawable.subtraction));
            String vid="android.resource://"+getPackageName()+"/"+R.raw.sub_tut;
            basicAdapter=new BasicAdapter(sub,sub_image,this,vid);
            recyclerView.setAdapter(basicAdapter);
        }
        else if(op.contentEquals("div"))
        {

            relativeLayout=findViewById(R.id.basic_math);
            relativeLayout.setBackground(ContextCompat.getDrawable(this,R.drawable.div));
            String vid="android.resource://"+getPackageName()+"/"+R.raw.short_div;
            basicAdapter=new BasicAdapter(div,div_image,this,vid);
            recyclerView.setAdapter(basicAdapter);
        }
        else if(op.contentEquals("mul"))
        {
            relativeLayout=findViewById(R.id.basic_math);
            relativeLayout.setBackground(ContextCompat.getDrawable(this,R.drawable.mul));
            String vid="android.resource://"+getPackageName()+"/"+R.raw.mul;
            basicAdapter=new BasicAdapter(mul,mul_image,this,vid);
            recyclerView.setAdapter(basicAdapter);
        }
        else
        {
            relativeLayout=findViewById(R.id.basic_math);
            relativeLayout.setBackground(ContextCompat.getDrawable(this,R.drawable.sqrt_bg));
            String vid="android.resource://"+getPackageName()+"/"+R.raw.sqrt;
            basicAdapter=new BasicAdapter(sqrt,sqrt_image,this,vid);
            recyclerView.setAdapter(basicAdapter);
        }
    }
}