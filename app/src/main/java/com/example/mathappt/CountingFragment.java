package com.example.mathappt;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class CountingFragment extends Fragment implements ProgrammingAdapter.OnRowListener{
    List<numbers> nums;
    RecyclerView recyclerView;
    public CountingFragment() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        nums=new ArrayList<>();

        for(int i=1;i<=50;i++)
        {
            numbers num=new numbers(i);
            nums.add(num);
        }
        View view=inflater.inflate(R.layout.fragment_counting, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.counting_recycler);
        ProgrammingAdapter programmingAdapter=new ProgrammingAdapter(nums,this,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));

        recyclerView.setAdapter(programmingAdapter);
        return view;
    }
    @Override
    public void OnRowClick(int position) {
        if(position==0) {
            final MediaPlayer player = MediaPlayer.create(getContext(), R.raw.one);
            player.start();
        }
        if(position==1) {
            final MediaPlayer player = MediaPlayer.create(getContext(), R.raw.two);
            player.start();
        }
        if(position==2) {
            final MediaPlayer player = MediaPlayer.create(getContext(), R.raw.three);
            player.start();
        }
        if(position==3) {
            final MediaPlayer player = MediaPlayer.create(getContext(), R.raw.four);
            player.start();
        }
        if(position==4) {
            final MediaPlayer player = MediaPlayer.create(getContext(), R.raw.five);
            player.start();
        }
        if(position==5) {
            final MediaPlayer player = MediaPlayer.create(getContext(), R.raw.six);
            player.start();
        }
        if(position==6) {
            final MediaPlayer player = MediaPlayer.create(getContext(), R.raw.seven);
            player.start();
        }
        if(position==7) {
            final MediaPlayer player = MediaPlayer.create(getContext(), R.raw.eight);
            player.start();
        }
        if(position==8) {
            final MediaPlayer player = MediaPlayer.create(getContext(), R.raw.nine);
            player.start();
        }
        if(position==9) {
            final MediaPlayer player = MediaPlayer.create(getContext(), R.raw.ten);
            player.start();
        }
    }
}