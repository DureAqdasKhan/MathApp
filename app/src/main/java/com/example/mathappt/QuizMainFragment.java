package com.example.mathappt;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuizMainFragment#} factory method to
 * create an instance of this fragment.
 */
public class QuizMainFragment extends Fragment {
    Button add, sub, mul, div;

    String email;

    public QuizMainFragment(String email)
    {
        this.email = email;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quiz_main, container, false);

        add = view.findViewById(R.id.addition);
        sub = view.findViewById(R.id.subtraction);
        mul = view.findViewById(R.id.multiplication);
        div = view.findViewById(R.id.division);

        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),QuizActivity.class);
                intent.putExtra("operation","add");
                intent.putExtra("email",email);
                startActivity(intent);
            }
        });
        sub.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),QuizActivity.class);
                intent.putExtra("operation","sub");
                intent.putExtra("email",email);
                startActivity(intent);
            }
        });
        mul.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),QuizActivity.class);
                intent.putExtra("operation","mul");
                intent.putExtra("email",email);
                startActivity(intent);
            }
        });
        div.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),QuizActivity.class);
                intent.putExtra("operation","div");
                intent.putExtra("email",email);
                startActivity(intent);
            }
        });
        return view;
    }
}