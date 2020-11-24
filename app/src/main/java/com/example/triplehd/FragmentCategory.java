package com.example.triplehd;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.triplehd.Adapter.AdapterMovie;
import com.example.triplehd.AsyncTask.GetCategoryMovieTask;
import com.example.triplehd.LiveModel.CategoryViewModel;
import com.example.triplehd.LiveModel.MainViewModel;
import com.example.triplehd.ObjectClass.Phim;
import com.example.triplehd.ObjectClass.myPoster;

import java.util.ArrayList;

public class FragmentCategory extends Fragment {
    RecyclerView recyclerView;
    ArrayList<Phim> data = new ArrayList<>();
    AdapterMovie adapter;
    Button action, horror, kungfu, legend;
    CategoryViewModel model;
    // action : Hanh Dong
    // horror: Kinh Di
    // kungfu: Vo Thuat
    // Legend : Than Thoai

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_category, container, false);
        //Lấy dữ liệu từ fragment_home
//        model = new ViewModelProvider(requireActivity()).get(CategoryViewModel.class);
        Bundle bundle = getArguments();
        Toast.makeText(getActivity(), bundle.getString("genre"), Toast.LENGTH_SHORT).show();
        model = new ViewModelProvider(requireActivity()).get(CategoryViewModel.class);


        Log.e("TAG", "onCreateView: " + bundle.getString("genre"));

        GetCategoryMovieTask getCategoryMovieTask = new GetCategoryMovieTask(model);
        getCategoryMovieTask.execute(bundle.getString("genre"));


        recyclerView = layout.findViewById(R.id.grv_category);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new AdapterMovie(getContext(), R.layout.layout_movie_home, data);
        recyclerView.setAdapter(adapter);

        model.getMovieList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Phim>>() {
            @Override
            public void onChanged(ArrayList<Phim> phims) {
                data.clear();
                data.addAll(phims);
                adapter.notifyDataSetChanged();
            }
        });

        //Lấy id của button
        action = layout.findViewById(R.id.btn_action);
        horror = layout.findViewById(R.id.btn_horror);
        kungfu = layout.findViewById(R.id.btn_kungfu);
        legend = layout.findViewById(R.id.btn_legend);
        //Sự kiện click button
        clickButton();
        return layout;
    }

    private void clickButton() {
        action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Action", Toast.LENGTH_SHORT).show();
                GetCategoryMovieTask getCategoryMovieTask = new GetCategoryMovieTask(model);
                getCategoryMovieTask.execute("1");
            }
        });
        horror.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Horror", Toast.LENGTH_SHORT).show();
                GetCategoryMovieTask getCategoryMovieTask = new GetCategoryMovieTask(model);
                getCategoryMovieTask.execute("2");

            }
        });
        kungfu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "KungFu", Toast.LENGTH_SHORT).show();
                GetCategoryMovieTask getCategoryMovieTask = new GetCategoryMovieTask(model);
                getCategoryMovieTask.execute("3");

            }
        });
        legend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Legend", Toast.LENGTH_SHORT).show();
                GetCategoryMovieTask getCategoryMovieTask = new GetCategoryMovieTask(model);
                getCategoryMovieTask.execute("4");

            }
        });
    }


}
