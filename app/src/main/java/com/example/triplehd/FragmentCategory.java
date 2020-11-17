package com.example.triplehd;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FragmentCategory extends Fragment {
    RecyclerView recyclerView;
    ArrayList<myPoster> data;
    AdapterMovie adapter;
    Button ac,hor, kf ,catoo;
    // ac : Hanh Dong
    // hor: Kinh Di
    // kf : Vo Thuat
    // catoo : Hoat Hinh


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout =  inflater.inflate(R.layout.fragment_category,container,false);
        recyclerView = layout.findViewById(R.id.grv_category);
        initPoster_SlideShow();
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),3);

        recyclerView.setLayoutManager(layoutManager);
        adapter = new AdapterMovie(getContext(),R.layout.layout_movie_home,data);
        recyclerView.setAdapter(adapter);

        return layout;
    }
    private void initPoster_SlideShow(){
        data = new ArrayList<>();
        for(int i =1 ; i < 20 ; i++){
            data.add(new myPoster(R.drawable.img_1,"Ready Player One"));
            data.add(new myPoster(R.drawable.img_2,"Avenger: Infinity War"));
            data.add(new myPoster(R.drawable.img_3,"The Incredible"));
            data.add(new myPoster(R.drawable.img_4,"Avatar"));
        }
    }
}
