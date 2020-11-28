package com.example.triplehd;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.triplehd.Adapter.AdapterComment;
import com.example.triplehd.Adapter.AdapterMovie;
import com.example.triplehd.AsyncTask.GetCommentTask;
import com.example.triplehd.AsyncTask.LoadMovieInfoTask;
import com.example.triplehd.AsyncTask.LoadRelateMovieInfoTask;
import com.example.triplehd.AsyncTask.SetCommentTask;
import com.example.triplehd.Contant.Contant;
import com.example.triplehd.LiveModel.CommentViewModel;
import com.example.triplehd.LiveModel.RelateMovieViewModel;
import com.example.triplehd.ObjectClass.Comment;
import com.example.triplehd.ObjectClass.Phim;

import java.util.ArrayList;

public class MovieActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView description;
    RecyclerView rv, rvCmt;
    VideoView videoView;
    RelateMovieViewModel model;
    CommentViewModel modelComment;
    ArrayList<Phim> data = new ArrayList<>();
    ArrayList<Comment> dataCmt = new ArrayList<>();
    AdapterMovie adapter;
    AdapterComment adapterComment;
    Button buttonCmt;
    Boolean isLogin;
    String username, email, role, idUser;
    private SharedPreferences pref;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_movie);
        toolbar = findViewById(R.id.toolbar_movie);
        description = findViewById(R.id.description_movie);
        buttonCmt = findViewById(R.id.btnComment);
        rv = findViewById(R.id.ryclerRelate);
        rvCmt = findViewById(R.id.ryclerCmt);
        videoView = findViewById(R.id.movie);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_action_back);
        toolbar.setLogo(R.drawable.logo);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MovieActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        //
        pref = getSharedPreferences("UserPref", MODE_PRIVATE);
        username = pref.getString("username", "username");
        email = pref.getString("email", "email");
        role = pref.getString("role", "2");
        idUser = pref.getString("id", "");
        isLogin = pref.getBoolean("isLogin", false);

        //relate move
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);

        model = new ViewModelProvider(this).get(RelateMovieViewModel.class);
        adapter = new AdapterMovie(this, R.layout.layout_movie_home, data);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);

//        RelateMovieViewModel model = new ViewModelProvider(this).get(RelateMovieViewModel.class);
        model.getMovieList().observe(this, new Observer<ArrayList<Phim>>() {
            @Override
            public void onChanged(ArrayList<Phim> phims) {
//                Log.e("TAG", "onChanged: "+phims);
                data.clear();
                data.addAll(phims);
                adapter.notifyDataSetChanged();
            }
        });
        //comment
        LinearLayoutManager layoutManagerComment = new LinearLayoutManager(this);
        layoutManagerComment.setOrientation(RecyclerView.VERTICAL);

        modelComment = new ViewModelProvider(this).get(CommentViewModel.class);
        adapterComment = new AdapterComment(this, R.layout.comment, dataCmt);
        rvCmt.setLayoutManager(layoutManagerComment);
        rvCmt.setAdapter(adapterComment);

        modelComment.getComment().observe(this, new Observer<ArrayList<Comment>>() {
            @Override
            public void onChanged(ArrayList<Comment> comments) {
//                Log.e("TAG", "onChanged: "+phims);
                dataCmt.clear();
                dataCmt.addAll(comments);
                adapterComment.notifyDataSetChanged();
            }
        });


        //Lấy id intent
        final Integer id = getIntent().getIntExtra("Id", 1);
        Integer genre = getIntent().getIntExtra("Genre", 1);
//        Log.e("TAG", "ID Poster: "+ id +","+genre);
        //
        LoadRelateMovieInfoTask loadRelateMovieInfoTask = new LoadRelateMovieInfoTask(model);
        loadRelateMovieInfoTask.execute(genre.toString(), id.toString());
        //
        LoadMovieInfoTask loadMovieInfoTask = new LoadMovieInfoTask(description);
        loadMovieInfoTask.execute(genre.toString(), id.toString());
        //
        GetCommentTask getCommentTask = new GetCommentTask(modelComment);
        getCommentTask.execute(id.toString());

        Uri uri = Uri.parse(Contant.URL_MOVIE_LINK + id.toString() + ".mp4");
        Log.e("TAG", "onCreate: " + uri);
        videoView.setVideoURI(uri);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                    @Override
                    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                        MediaController mediaController = new MediaController(MovieActivity.this);
                        videoView.setMediaController(mediaController);
                        mediaController.setAnchorView(videoView);
                    }
                });
            }
        });

        try {
            videoView.start();
        } catch (Exception e) {
            Log.e("TAG", "onCreate: ", e);
        }
        buttonCmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MovieActivity.this);
                View viewLayout = LayoutInflater.from(MovieActivity.this).inflate(R.layout.add_comment_layout, null);
                builder.setView(viewLayout);
                final Dialog dialog = builder.create();
//                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.show();
                Button addCommnet = viewLayout.findViewById(R.id.addCmt);
                final EditText editText = viewLayout.findViewById(R.id.etCmt);
                final SetCommentTask setCommentTask = new SetCommentTask(modelComment);
                addCommnet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (idUser.isEmpty()) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(MovieActivity.this);
                            builder.setTitle("Chưa Đăng Nhập");
                            builder.setMessage("Bạn Cần Phải Dăng Nhập Để Thực Hiện Nình Luận");
                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }

                            });
                            Dialog mess = builder.create();
                            mess.show();
                        } else {
                            setCommentTask.execute(idUser, id.toString(), editText.getText().toString());
                            dialog.dismiss();
                        }
                    }
                });
            }
        });
    }
}
