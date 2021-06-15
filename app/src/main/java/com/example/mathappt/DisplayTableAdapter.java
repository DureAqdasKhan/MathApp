package com.example.mathappt;

import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DisplayTableAdapter extends RecyclerView.Adapter<DisplayTableAdapter.ProgrammingViewHolder> {
    List<NumberString> nums;
    Context context;
    public DisplayTableAdapter(List<NumberString>num, Context context)
    {

        this.nums=num;
        this.context=context;
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.display_table_view;
    }

    @Override
    public ProgrammingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(viewType,parent,false);
        return new ProgrammingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProgrammingViewHolder holder, int position) {
        holder.textView1.setText(nums.get(position).number);
        holder.textView2.setText(nums.get(position).number2);
        String result=String.valueOf(Integer.parseInt(nums.get(position).number)*Integer.parseInt(nums.get(position).number2));
        holder.textView3.setText(result);
    }

    @Override
    public int getItemCount() {
        return nums.size();
    }

    class ProgrammingViewHolder extends RecyclerView.ViewHolder
    {
        TextView textView1;
        TextView textView2;
        TextView textView3;
        public ProgrammingViewHolder(View itemView) {
            super(itemView);
            textView1=(TextView)itemView.findViewById(R.id.num1);
            textView2=(TextView)itemView.findViewById(R.id.num2);
            textView3=(TextView)itemView.findViewById(R.id.answer);
        }
    }
}


