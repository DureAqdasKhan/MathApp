package com.example.mathappt;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProgressAdapter extends RecyclerView.Adapter<ProgressAdapter.ViewHolder> {

    List<Scores> progress = new ArrayList<>();

    public ProgressAdapter(List<Scores> p)
    {
        progress = p;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.progress_rview, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.type.setText("Quiz type: " + progress.get(position).type);
        holder.score.setText(" Score: " + progress.get(position).score);
    }

    @Override
    public int getItemCount() {
        return progress.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView type, score;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            type = itemView.findViewById(R.id.type);
            score = itemView.findViewById(R.id.score);

        }
    }
}