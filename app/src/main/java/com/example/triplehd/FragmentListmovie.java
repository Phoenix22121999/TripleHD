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
    AdapterListMovie adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout =  inflater.inflate(R.layout.fragment_list_movie,container,false);
        btn_add = layout.findViewById(R.id.btn_add);
        btn_delete = layout.findViewById(R.id.btn_delete);
        btn_edit = layout.findViewById(R.id.btn_edit);
        model =new ViewModelProvider(this).get(AllMovieViewModel.class);
        LoadAllMovieTask loadAllMovieTask = new LoadAllMovieTask(model);
        loadAllMovieTask.execute();
        recyclerView = layout.findViewById(R.id.rvListAll);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new AdapterListMovie(getContext(), R.layout.layout_movie_home, data);
        recyclerView.setAdapter(adapter);

        model.getMovieList().observe(getActivity(), new Observer<ArrayList<Phim>>() {
            @Override
            public void onChanged(ArrayList<Phim> phims) {
                data.clear();
                data.addAll(phims);
                adapter.notifyDataSetChanged();
            }
        });

        onclickButton();
        registerForContextMenu(recyclerView);
        return layout;
    }

    private void onclickButton() {
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"Add",Toast.LENGTH_SHORT).show();
            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"Delete",Toast.LENGTH_SHORT).show();
            }
        });
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"Edit",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo ) menuInfo;
        int position = info.position;
        menu.setHeaderTitle(data.get(position).getTitle());
        getActivity().getMenuInflater().inflate(R.menu.context_menu,menu);
        super.onCreateContextMenu(menu, v, menuInfo);

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete:
                break;
        }
        return super.onContextItemSelected(item);
    }
}
