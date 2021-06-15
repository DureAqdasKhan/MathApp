package com.example.mathappt;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class TablesFragment extends Fragment implements TableProgrammingAdapter.OnRowListener{
    List<NumberString> numberStringList;
    RecyclerView recyclerView;
    public TablesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        numberStringList=new ArrayList<>();

        NumberString numberString1=new NumberString("ONE");
        numberStringList.add(numberString1);
        NumberString numberString2=new NumberString("TWO");
        numberStringList.add(numberString2);
        NumberString numberString3=new NumberString("THREE");
        numberStringList.add(numberString3);
        NumberString numberString4=new NumberString("FOUR");
        numberStringList.add(numberString4);
        NumberString numberString5=new NumberString("FIVE");
        numberStringList.add(numberString5);
        NumberString numberString6=new NumberString("SIX");
        numberStringList.add(numberString6);
        NumberString numberString7=new NumberString("SEVEN");
        numberStringList.add(numberString7);
        NumberString numberString8=new NumberString("EIGHT");
        numberStringList.add(numberString8);
        NumberString numberString9=new NumberString("NINE");
        numberStringList.add(numberString9);
        NumberString numberString10=new NumberString("TEN");
        numberStringList.add(numberString10);

        View view=inflater.inflate(R.layout.fragment_tables, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.tables_recycler);
        TableProgrammingAdapter programmingAdapter=new TableProgrammingAdapter(numberStringList,this,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(programmingAdapter);
        return view;
        // Inflate the layout for this fragment
    }

    @Override
    public void OnRowClick(int position) {
        System.out.println("POSITION INCOMING  *************"+position);
        Intent intent=new Intent(getContext(),DisplayTables.class);
        intent.putExtra("table_num",position+1);
        startActivity(intent);
    }
}