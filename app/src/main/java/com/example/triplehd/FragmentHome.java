package com.example.triplehd;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class FragmentHome extends Fragment {
    SliderView slideImg;
    SlideshowAdapter adapterSlide;
    ArrayList<myPoster> dataSlide = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout_slideshow = inflater.inflate(R.layout.fragment_home,container,false);
        slideImg = layout_slideshow.findViewById(R.id.slider);
        //Add các phim vào slideshow
        initPoster();
        //Setting cho slideshow
        adapterSlide = new SlideshowAdapter(this,dataSlide);
        slideImg.setSliderAdapter(adapterSlide);
        slideImg.setIndicatorAnimation(IndicatorAnimationType.WORM);
        slideImg.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        slideImg.setIndicatorSelectedColor(Color.RED);
        slideImg.setIndicatorUnselectedColor(Color.GRAY);
        slideImg.setScrollTimeInSec(4); //set scroll delay in seconds :
        slideImg.startAutoCycle();
        return layout_slideshow;
    }

    private void initPoster(){
        dataSlide.add(new myPoster(R.drawable.img_slideshow_01,"Ready Player One"));
        dataSlide.add(new myPoster(R.drawable.img_slideshow_02,"Avenger: Infinity War"));
        dataSlide.add(new myPoster(R.drawable.img_slideshow_03,"The Incredible"));
        dataSlide.add(new myPoster(R.drawable.img_slideshow_04,"Avatar"));
    }
}
