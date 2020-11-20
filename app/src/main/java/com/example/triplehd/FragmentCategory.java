package com.example.triplehd;

import android.os.Bundle;
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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.triplehd.Adapter.AdapterMovie;
import com.example.triplehd.ObjectClass.Phim;
import com.example.triplehd.ObjectClass.myPoster;

import java.util.ArrayList;

public class FragmentCategory extends Fragment {
    RecyclerView recyclerView;
    ArrayList<Phim> data;
    AdapterMovie adapter;
    Button action,horror, kungfu , legend;
    // action : Hanh Dong
    // horror: Kinh Di
    // kungfu: Vo Thuat
    // Legend : Than Thoai


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout =  inflater.inflate(R.layout.fragment_category,container,false);
        //Lấy dữ liệu từ fragment_home
        Bundle bundle = getArguments();
        Toast.makeText(getActivity(),bundle.getString("theloai"),Toast.LENGTH_SHORT).show();
        recyclerView = layout.findViewById(R.id.grv_category);
        initPoster_SlideShow();
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),3);

        recyclerView.setLayoutManager(layoutManager);
        adapter = new AdapterMovie(getContext(),R.layout.layout_movie_home,data);
        recyclerView.setAdapter(adapter);
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
                Toast.makeText(getActivity(),"Action",Toast.LENGTH_SHORT).show();
            }
        });
        horror.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"Horror",Toast.LENGTH_SHORT).show();
            }
        });
        kungfu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"KungFu",Toast.LENGTH_SHORT).show();
            }
        });
        legend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"Legend",Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void initPoster_SlideShow(){
        data = new ArrayList<>();
        for(int i =1 ; i < 20 ; i++){
//            data.add(new myPoster(R.drawable.img_1,"Ready Player One"));
//            data.add(new myPoster(R.drawable.img_2,"Avenger: Infinity War"));
//            data.add(new myPoster(R.drawable.img_3,"The Incredible"));
//            data.add(new myPoster(R.drawable.img_4,"Avatar"));
        }
    }
}
