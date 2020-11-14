package com.example.triplehd;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class FragmentHome extends Fragment {
    SliderView slideImg;
    SlideshowAdapter adapterSlide;
    AdapterMovie adapterMovie;
    TextView show_all;
    RecyclerView recyclerView_hd, recyclerView_kinhdi;
    ArrayList<myPoster> dataSlide = new ArrayList<>();
    ArrayList<myPoster> datalist = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout_slideshow = inflater.inflate(R.layout.fragment_home,container,false);
        slideImg = layout_slideshow.findViewById(R.id.slider);
        recyclerView_hd = layout_slideshow.findViewById(R.id.rycler_hd);
        recyclerView_kinhdi = layout_slideshow.findViewById(R.id.rycler_kinhdi);
        show_all = layout_slideshow.findViewById(R.id.show_all);
        //Add các phim vào slideshow
        initPoster_SlideShow();
        //Setting cho slideshow
        adapterSlide = new SlideshowAdapter(this,dataSlide);
        slideImg.setSliderAdapter(adapterSlide);
        slideImg.setIndicatorAnimation(IndicatorAnimationType.WORM);
        slideImg.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        slideImg.setIndicatorSelectedColor(Color.RED);
        slideImg.setIndicatorUnselectedColor(Color.GRAY);
        slideImg.setScrollTimeInSec(4); //set scroll delay in seconds :
        slideImg.startAutoCycle();

        //Setting cho list phim theo loại
        initPoster_List();
        LinearLayoutManager layoutManager = new  LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        adapterMovie = new AdapterMovie(getContext(),R.layout.layout_movie_home,datalist);
        recyclerView_hd.setAdapter(adapterMovie);
        recyclerView_hd.setLayoutManager(layoutManager);
        new Thread(new Runnable() {
            @Override
            public void run() {
                LinearLayoutManager layoutManager = new  LinearLayoutManager(getContext());
                layoutManager.setOrientation(RecyclerView.HORIZONTAL);
                recyclerView_kinhdi.setAdapter(adapterMovie);
                recyclerView_kinhdi.setLayoutManager(layoutManager);
            }
        }).start();

        return layout_slideshow;
    }
    // hàm khởi tạo poster ngang cho slideShow
    private void initPoster_SlideShow(){
        dataSlide.add(new myPoster(R.drawable.img_slideshow_01,"Ready Player One"));
        dataSlide.add(new myPoster(R.drawable.img_slideshow_02,"Avenger: Infinity War"));
        dataSlide.add(new myPoster(R.drawable.img_slideshow_03,"The Incredible"));
        dataSlide.add(new myPoster(R.drawable.img_slideshow_04,"Avatar"));
    }
    private void initPoster_List(){
        datalist.add(new myPoster(R.drawable.img_1,"Tên Phim 1"));
        datalist.add(new myPoster(R.drawable.img_2,"Tên Phim 2"));
        datalist.add(new myPoster(R.drawable.img_3,"Tên Phim 3"));
        datalist.add(new myPoster(R.drawable.img_4,"Tên Phim 4"));
        datalist.add(new myPoster(R.drawable.img_5,"Tên Phim 5"));
    }
}
