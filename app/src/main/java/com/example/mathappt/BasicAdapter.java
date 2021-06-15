package com.example.mathappt;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mathappt.R;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class BasicAdapter extends RecyclerView.Adapter<BasicAdapter.ProgrammingViewHolder> {
   String [] content;
   int [] images;
   String videos;
    Context context;
    MediaController mediaController;
    public BasicAdapter(String [] content,int [] images,Context context,String videos)
    {
        this.content=content;
        this.images=images;
        this.context=context;
        this.videos=videos;
        mediaController=new MediaController(context);
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.basic_math_view_holder;
    }

    @Override
    public ProgrammingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(viewType,parent,false);
        return new ProgrammingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProgrammingViewHolder holder, int position) {
        holder.textView.setText(content[position]);
        holder.imageView.setImageResource(images[position]);
        Uri uri = Uri.parse(videos);
        System.out.println(uri.toString());
        holder.videoView.setVideoURI(uri);
        holder.videoView.setMediaController(mediaController);
        mediaController.setAnchorView(holder.videoView);
        holder.videoView.start();
    }

    @Override
    public int getItemCount() {
        return content.length;
    }

    class ProgrammingViewHolder extends RecyclerView.ViewHolder
    {
        TextView textView;
        ImageView imageView;
        VideoView videoView;
        public ProgrammingViewHolder(View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.textholder);
            imageView=itemView.findViewById(R.id.image);
            videoView=itemView.findViewById(R.id.video);
        }
    }
}
