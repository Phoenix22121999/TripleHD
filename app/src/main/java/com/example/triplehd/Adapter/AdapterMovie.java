package com.example.triplehd.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.triplehd.AsyncTask.DownloadImageTask;
import com.example.triplehd.Contant.Contant;
import com.example.triplehd.MovieActivity;
import com.example.triplehd.ObjectClass.Phim;
import com.example.triplehd.R;
import com.example.triplehd.ObjectClass.myPoster;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class AdapterMovie extends RecyclerView.Adapter<AdapterMovie.ViewHolder> {
    Context ctx;
    List<Phim> data_movive;
    int img_id;

    public AdapterMovie(Context context, int resouce, List<Phim> object) {
        this.ctx = context;
        this.data_movive = object;
    }


    @NonNull
    @Override
    public AdapterMovie.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View layout = inflater.inflate(R.layout.layout_movie_home, parent, false);
        ViewHolder holder = new ViewHolder(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMovie.ViewHolder holder, final int position) {
        final Phim poster = data_movive.get(position);
        // name = name
        // poster = img
        holder.name.setText(poster.getTitle());
//        holder.poster.setImageResource(poster.getImage());
        DownloadImageTask downloadImageTask = new DownloadImageTask(holder.poster);

        String url = Contant.URL_IMAGE_LINK + poster.getLink();
        downloadImageTask.execute(url);
//        Log.e("TAG", "onBindViewHolder: " + poster.getId() );
        holder.poster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.e("TAG", "onClick: "+ poster.getId() );
                Intent intent = new Intent(ctx, MovieActivity.class);
                intent.putExtra("Id",poster.getId());
                intent.putExtra("Genre",poster.getGenre());
                ctx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data_movive.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView poster;
        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.poster_movive_home);
            name = itemView.findViewById(R.id.name_movive_home);

        }
    }
}
