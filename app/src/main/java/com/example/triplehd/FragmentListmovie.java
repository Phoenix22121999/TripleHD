package com.example.triplehd;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.triplehd.Adapter.AdapterListMovie;
import com.example.triplehd.Adapter.AdapterMovie;
import com.example.triplehd.AsyncTask.LoadAllMovieTask;
import com.example.triplehd.LiveModel.AllMovieViewModel;
import com.example.triplehd.ObjectClass.Phim;

import java.util.ArrayList;

public class FragmentListmovie extends Fragment {
    Button btn_add,btn_edit,btn_delete;
    AllMovieViewModel model;
    ArrayList<Phim> data = new ArrayList<>();
    RecyclerView recyclerView;
    AdapterMovie adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout =  inflater.inflate(R.layout.fragment_list_movie,container,false);

        model =new ViewModelProvider(this).get(AllMovieViewModel.class);
        LoadAllMovieTask loadAllMovieTask = new LoadAllMovieTask(model);
        loadAllMovieTask.execute();
        recyclerView = layout.findViewById(R.id.rvListAll);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new AdapterMovie(getContext(), R.layout.layout_movie_home, data);
        recyclerView.setAdapter(adapter);

        model.getMovieList().observe(getActivity(), new Observer<ArrayList<Phim>>() {
            @Override
            public void onChanged(ArrayList<Phim> phims) {
                data.clear();
                data.addAll(phims);
                adapter.notifyDataSetChanged();
            }
        });

//        onclickButton();
        registerForContextMenu(recyclerView);
        return layout;
    }


}
