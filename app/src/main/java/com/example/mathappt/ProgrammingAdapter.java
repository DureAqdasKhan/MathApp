package com.example.mathappt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProgrammingAdapter extends RecyclerView.Adapter<ProgrammingAdapter.ProgrammingViewHolder> {
    List<numbers> nums;
    CountingFragment context;
    OnRowListener onRowListener;
    public ProgrammingAdapter(List<numbers>num, CountingFragment context,OnRowListener onRowListener)
    {
        //this.messageList=messageList;
        this.nums=num;
        this.context=context;
        this.onRowListener=onRowListener;
    }

    @Override
    public int getItemViewType(int position) {
            return R.layout.counting_view;
    }

    @Override
    public ProgrammingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(viewType,parent,false);
        return new ProgrammingViewHolder(view,onRowListener);
    }

    @Override
    public void onBindViewHolder(ProgrammingViewHolder holder, int position) {
        holder.textView.setText(String.valueOf(nums.get(position).num));
    }

    @Override
    public int getItemCount() {
        return nums.size();
    }

    class ProgrammingViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener
    {
        TextView textView;
        OnRowListener onRowListener;
        public ProgrammingViewHolder(View itemView,OnRowListener onRowListener) {
            super(itemView);
            textView=(TextView)itemView.findViewById(R.id.counting_view_holder);
            this.onRowListener=onRowListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onRowListener.OnRowClick(getAdapterPosition());
        }

    }

    public interface OnRowListener{
        void OnRowClick(int position);
    }
}


