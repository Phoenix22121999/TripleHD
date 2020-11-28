package com.example.triplehd.Adapter;

import android.content.Context;
import android.content.Intent;
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
import com.example.triplehd.ObjectClass.Comment;
import com.example.triplehd.ObjectClass.Phim;
import com.example.triplehd.R;

import java.util.List;

public class AdapterComment extends RecyclerView.Adapter<AdapterComment.ViewHolder> {
    Context ctx;
    List<Comment> data_comment;
    int img_id;

    public AdapterComment(Context context, int resouce, List<Comment> object) {
        this.ctx = context;
        this.data_comment = object;
    }


    @NonNull
    @Override
    public AdapterComment.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View layout = inflater.inflate(R.layout.comment, parent, false);
        ViewHolder holder = new ViewHolder(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterComment.ViewHolder holder, final int position) {
        final Comment comment = data_comment.get(position);

        holder.cmtUser.setText(comment.getUsername());
        holder.cmtContent.setText(comment.getComment());

    }

    @Override
    public int getItemCount() {
        return data_comment.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView cmtUser;
        TextView cmtContent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cmtUser = itemView.findViewById(R.id.userCmt);
            cmtContent = itemView.findViewById(R.id.cmtContent);

        }
    }
}
