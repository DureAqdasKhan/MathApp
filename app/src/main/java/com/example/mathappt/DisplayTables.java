package com.example.mathappt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class DisplayTables extends AppCompatActivity {
    RecyclerView recyclerView;
    DisplayTableAdapter displayTableAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_tables);
        List<NumberString> table = new ArrayList<>();
        Intent intent=getIntent();
        int number=intent.getIntExtra("table_num",0);
        System.out.println("POSITION IN DISPLAYTSBLES__++++++++++++"+number);
        recyclerView=findViewById(R.id.display_tables);

        int j = 1;
        if(number==1) {
            for (int i = 1; i <= 10; i++) {
                NumberString numberString = new NumberString(String.valueOf(j), String.valueOf(i));
                table.add(numberString);
            }
            displayTableAdapter= new DisplayTableAdapter(table,this);

        }
        else if(number==2) {
            j = 2;

            for (int i = 1; i <= 10; i++) {
                NumberString numberString = new NumberString(String.valueOf(j), String.valueOf(i));
                table.add(numberString);
            }
            displayTableAdapter= new DisplayTableAdapter(table,this);
        }
        else if(number==3) {
            j = 3;

            for (int i = 1; i <= 10; i++) {
                NumberString numberString = new NumberString(String.valueOf(j), String.valueOf(i));
                table.add(numberString);
            }
            displayTableAdapter= new DisplayTableAdapter(table,this);
        }
        else if(number==4) {
            j = 4;

            for (int i = 1; i <= 10; i++) {
                NumberString numberString = new NumberString(String.valueOf(j), String.valueOf(i));
                table.add(numberString);
            }
            displayTableAdapter= new DisplayTableAdapter(table,this);
        }
        else if(number==5) {
            j = 5;
            for (int i = 1; i <= 10; i++) {
                NumberString numberString = new NumberString(String.valueOf(j), String.valueOf(i));
                table.add(numberString);
            }
            displayTableAdapter= new DisplayTableAdapter(table,this);
        }
        else if(number==6) {
            j = 6;
            for (int i = 1; i <= 10; i++) {
                NumberString numberString = new NumberString(String.valueOf(j), String.valueOf(i));
                table.add(numberString);
            }
            displayTableAdapter= new DisplayTableAdapter(table,this);
        }
        else if(number==7) {
            j = 7;
            for (int i = 1; i <= 10; i++) {
                NumberString numberString = new NumberString(String.valueOf(j), String.valueOf(i));
                table.add(numberString);
            }
            displayTableAdapter= new DisplayTableAdapter(table,this);
        }
        else if(number==8) {
            j = 8;

            for (int i = 1; i <= 10; i++) {
                NumberString numberString = new NumberString(String.valueOf(j), String.valueOf(i));
                table.add(numberString);
            }
            displayTableAdapter= new DisplayTableAdapter(table,this);
        }
        else if(number==9) {
            j = 9;

            for (int i = 1; i <= 10; i++) {
                NumberString numberString = new NumberString(String.valueOf(j), String.valueOf(i));
                table.add(numberString);
            }
            displayTableAdapter= new DisplayTableAdapter(table,this);
        }
        else {
            j = 10;

            for (int i = 1; i <= 10; i++) {
                NumberString numberString = new NumberString(String.valueOf(j), String.valueOf(i));
                table.add(numberString);
            }
            displayTableAdapter= new DisplayTableAdapter(table,this);
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(displayTableAdapter);
    }
}