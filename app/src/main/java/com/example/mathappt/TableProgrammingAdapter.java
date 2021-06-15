package com.example.mathappt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TableProgrammingAdapter extends RecyclerView.Adapter<TableProgrammingAdapter.TableProgrammingViewHolder> {
    List<NumberString> nums;
    TablesFragment context;
    OnRowListener onRowListener;
    public TableProgrammingAdapter(List<NumberString>num, TablesFragment context,OnRowListener onRowListener)
    {
        //this.messageList=messageList;
        this.nums=num;
        this.context=context;
        this.onRowListener=onRowListener;
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.tables_view;
    }

    @Override
    public TableProgrammingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(viewType,parent,false);
        return new TableProgrammingViewHolder(view,onRowListener);
    }

    @Override
    public void onBindViewHolder(TableProgrammingViewHolder holder, int position) {
        holder.button.setText(nums.get(position).number);
    }

    @Override
    public int getItemCount() {
        return nums.size();
    }

    class TableProgrammingViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener
    {
        Button button;
        OnRowListener onRowListener;
        public TableProgrammingViewHolder(View itemView,OnRowListener onRowListener) {
            super(itemView);
            button=(Button)itemView.findViewById(R.id.table);
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


