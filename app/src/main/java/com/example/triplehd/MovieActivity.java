package com.example.triplehd;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

import com.example.triplehd.Adapter.AdapterMovie;
import com.example.triplehd.AsyncTask.LoadMovieInfoTask;
import com.example.triplehd.AsyncTask.LoadRelateMovieInfoTask;
import com.example.triplehd.Contant.Contant;
import com.example.triplehd.LiveModel.RelateMovieViewModel;
import com.example.triplehd.ObjectClass.Phim;

import java.util.ArrayList;

public class MovieActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView description ;
    RecyclerView rv;
    VideoView videoView;
    RelateMovieViewModel model;
    ArrayList<Phim> data = new ArrayList<>();
     AdapterMovie adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_movie);
        toolbar = findViewById(R.id.toolbar_movie);
        description =findViewById(R.id.description_movie);
        rv = findViewById(R.id.ryclerRelate);
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


        LinearLayoutManager layoutManager = new  LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);

        model = new ViewModelProvider(this).get(RelateMovieViewModel.class);
        adapter = new AdapterMovie(this, R.layout.layout_movie_home, data);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);

        RelateMovieViewModel model = new ViewModelProvider(this).get(RelateMovieViewModel.class);
        model.getMovieList().observe(this, new Observer<ArrayList<Phim>>() {
            @Override
            public void onChanged(ArrayList<Phim> phims) {
//                Log.e("TAG", "onChanged: "+phims);
                data.clear();
                data.addAll(phims);
                adapter.notifyDataSetChanged();
            }
        });


        //Lấy id intent
        Integer id = getIntent().getIntExtra("Id",1);
        Integer genre = getIntent().getIntExtra("Genre",1);
//        Log.e("TAG", "ID Poster: "+ id +","+genre);
        //
        LoadRelateMovieInfoTask loadRelateMovieInfoTask = new LoadRelateMovieInfoTask(model);
        loadRelateMovieInfoTask.execute(genre.toString(),id.toString());
        //
        LoadMovieInfoTask loadMovieInfoTask =new LoadMovieInfoTask(description);
        loadMovieInfoTask.execute(genre.toString(),id.toString());

        Uri uri = Uri.parse(Contant.URL_MOVIE_LINK+id.toString()+".mp4");
        Log.e("TAG", "onCreate: "+uri );
        videoView.setVideoURI(uri);
        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
        try {
            videoView.start();
        }catch (Exception e){
            Log.e("TAG", "onCreate: ",e);
        }
    }
}
