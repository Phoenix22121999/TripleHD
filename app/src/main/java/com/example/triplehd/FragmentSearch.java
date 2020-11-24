package com.example.triplehd;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.triplehd.Adapter.AdapterMovie;
import com.example.triplehd.AsyncTask.SearchTask;
import com.example.triplehd.LiveModel.CategoryViewModel;
import com.example.triplehd.LiveModel.SearchViewModel;
import com.example.triplehd.ObjectClass.Phim;

import java.util.ArrayList;

public class FragmentSearch extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    SearchView searchView;
    SearchViewModel model;
    AdapterMovie adapter;
    ArrayList<Phim> data = new ArrayList<Phim>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_search);
        toolbar = findViewById(R.id.toolbar_search);
        recyclerView = findViewById(R.id.grv_search);
        searchView = findViewById(R.id.search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Log.e("TAG", "onQueryTextSubmit: " + s);
                SearchTask searchTask = new SearchTask(model);
                searchTask.execute(s);
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return true;
            }

        });

        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new AdapterMovie(this, R.layout.layout_movie_home, data);
        recyclerView.setAdapter(adapter);
        model = new ViewModelProvider(this).get(SearchViewModel.class);
        model.getMovieList().observe(this, new Observer<ArrayList<Phim>>() {
            @Override
            public void onChanged(ArrayList<Phim> phims) {
                Log.e("TAG", "onChanged: " + phims);
                data.clear();
                data.addAll(phims);
                adapter.notifyDataSetChanged();
            }
        });

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_action_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FragmentSearch.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater();
        return true;
    }
}
