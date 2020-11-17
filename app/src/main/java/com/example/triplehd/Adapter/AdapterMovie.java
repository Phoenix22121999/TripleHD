package com.example.triplehd.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.triplehd.R;
import com.example.triplehd.ObjectClass.myPoster;

import java.util.List;

public class AdapterMovie extends RecyclerView.Adapter<AdapterMovie.ViewHolder> {
    Context ctx;
    List<myPoster> data_movive;
    int img_id;
    public AdapterMovie(Context context, int resouce , List<myPoster> object){
        this.ctx = context;
        this.data_movive = object;
    }

    @NonNull
    @Override
    public AdapterMovie.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View layout = inflater.inflate(R.layout.layout_movie_home,parent,false);
        ViewHolder holder = new ViewHolder(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMovie.ViewHolder holder, int position) {
        myPoster poster = data_movive.get(position);
        // name = name
        // poster = img
        holder.name.setText(poster.getName());
        holder.poster.setImageResource(poster.getImage());
    }

    @Override
    public int getItemCount() {
        return data_movive.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView poster;
        TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.poster_movive_home);
            name = itemView.findViewById(R.id.name_movive_home);

        }
    }
}
