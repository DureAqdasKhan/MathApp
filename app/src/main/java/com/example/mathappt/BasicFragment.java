package com.example.mathappt;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class BasicFragment extends Fragment {
    Button add;
    Button mul;
    Button sub;
    Button div;
    Button sqrt;
    Button calculator;
    Button count;
    public BasicFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_basic, container, false);
        // Inflate the layout for this fragment
        add=view.findViewById(R.id.add);
        sub=view.findViewById(R.id.sub);
        mul=view.findViewById(R.id.mul);
        div=view.findViewById(R.id.div);
        sqrt = view.findViewById(R.id.sqrt);
        calculator=view.findViewById(R.id.cal);
        count=view.findViewById(R.id.counting);
        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),BasicMathActivity.class);
                intent.putExtra("operation","add");
                startActivity(intent);
            }
        });
        sub.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),BasicMathActivity.class);
                intent.putExtra("operation","sub");
                startActivity(intent);
            }
        });
        mul.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),BasicMathActivity.class);
                intent.putExtra("operation","mul");
                startActivity(intent);
            }
        });
        div.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),BasicMathActivity.class);
                intent.putExtra("operation","div");
                startActivity(intent);
            }
        });
        sqrt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),BasicMathActivity.class);
                intent.putExtra("operation","sqrt");
                startActivity(intent);
            }
        });
        calculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),Calculator.class);
                startActivity(intent);
            }
        });
        count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),CountingSong.class);
                startActivity(intent);
            }
        });

        return view;
    }
}