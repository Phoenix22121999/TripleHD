package com.example.triplehd;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.triplehd.Adapter.AdapterMovie;
import com.example.triplehd.Adapter.SlideshowAdapter;
import com.example.triplehd.AsyncTask.GetListPhimTask;
import com.example.triplehd.Contant.Contant;
import com.example.triplehd.LiveModel.MainViewModel;
import com.example.triplehd.ObjectClass.Phim;
import com.example.triplehd.ObjectClass.myPoster;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class FragmentHome extends Fragment {
    SliderView slideImg;
    SlideshowAdapter adapterSlide;
    AdapterMovie adapterMovie;
    TextView showMoreHanhDong,showMoreKinhDi,showMoreVoThuat,showMoreThanThoai;
    RecyclerView rvHanhDong, rvKinhDi,rvVoThuat,rvThanThoai;
    MainViewModel model;
    ArrayList<myPoster> dataSlide = new ArrayList<>();
    ArrayList<Phim> listPhimHanhDong = new ArrayList<>();
    ArrayList<Phim> listPhimKinhDi = new ArrayList<>();
    ArrayList<Phim> listPhimVoThuat = new ArrayList<>();
    ArrayList<Phim> listPhimThanThoai = new ArrayList<>();
    AdapterMovie adapterHanhDong, adapterKinhDi,adapterVoThuat,adapterThanThoai;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout_slideshow = inflater.inflate(R.layout.fragment_home,container,false);
        slideImg = layout_slideshow.findViewById(R.id.slider);
        rvHanhDong = layout_slideshow.findViewById(R.id.ryclerHanhDong);
        rvKinhDi = layout_slideshow.findViewById(R.id.rycler);
        rvThanThoai = layout_slideshow.findViewById(R.id.ryclerThanThoai);
        rvVoThuat = layout_slideshow.findViewById(R.id.ryclerVoThuat);
        showMoreHanhDong = layout_slideshow.findViewById(R.id.showMoreHanhDong);
        showMoreKinhDi = layout_slideshow.findViewById(R.id.showMoreKinhDi);
        showMoreThanThoai = layout_slideshow.findViewById(R.id.showMoreThanThoai);
        showMoreVoThuat = layout_slideshow.findViewById(R.id.showMoreVoThuat);

        model = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        // Lấy phim
        GetListPhimTask getListPhimTask =new GetListPhimTask(model);
        getListPhimTask.execute(Contant.URL+"/php/loadPage.php");


        //Click show all
        ShowAllCaterogy();
        //Add các phim vào slideshow
        initPoster_SlideShow();
        //Setting cho slideshow
        adapterSlide = new SlideshowAdapter(this, dataSlide);
        slideImg.setSliderAdapter(adapterSlide);
        slideImg.setIndicatorAnimation(IndicatorAnimationType.WORM);
        slideImg.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        slideImg.setIndicatorSelectedColor(Color.RED);
        slideImg.setIndicatorUnselectedColor(Color.GRAY);
        slideImg.setScrollTimeInSec(4); //set scroll delay in seconds :
        slideImg.startAutoCycle();

        //Setting cho list phim theo loại
//        initPoster_List();
        LinearLayoutManager layoutManagerHanhDong = new  LinearLayoutManager(getContext());
        layoutManagerHanhDong.setOrientation(RecyclerView.HORIZONTAL);
        LinearLayoutManager layoutManagerKinhDi = new  LinearLayoutManager(getContext());
        layoutManagerKinhDi.setOrientation(RecyclerView.HORIZONTAL);
        LinearLayoutManager layoutManagerVoThuat = new  LinearLayoutManager(getContext());
        layoutManagerVoThuat.setOrientation(RecyclerView.HORIZONTAL);
        LinearLayoutManager layoutManagerThanThoai = new  LinearLayoutManager(getContext());
        layoutManagerThanThoai.setOrientation(RecyclerView.HORIZONTAL);

        rvHanhDong.setLayoutManager(layoutManagerHanhDong);
        rvKinhDi.setLayoutManager(layoutManagerKinhDi);
        rvVoThuat.setLayoutManager(layoutManagerVoThuat);
        rvThanThoai.setLayoutManager(layoutManagerThanThoai);

        adapterHanhDong = new AdapterMovie(getContext(),R.layout.layout_movie_home,listPhimHanhDong);
        adapterKinhDi = new AdapterMovie(getContext(),R.layout.layout_movie_home,listPhimKinhDi);
        adapterVoThuat = new AdapterMovie(getContext(),R.layout.layout_movie_home,listPhimVoThuat);
        adapterThanThoai = new AdapterMovie(getContext(),R.layout.layout_movie_home,listPhimThanThoai);
        rvHanhDong.setAdapter(adapterHanhDong);
        rvKinhDi.setAdapter(adapterKinhDi);
        rvVoThuat.setAdapter(adapterVoThuat);
        rvThanThoai.setAdapter(adapterThanThoai);



        //Observer data
        model.getHanhDong().observe(getViewLifecycleOwner(), new Observer<ArrayList<Phim>>() {
            @Override
            public void onChanged(ArrayList<Phim> phims) {
                listPhimHanhDong.addAll( phims);
                adapterHanhDong.notifyDataSetChanged();
            }
        });

        model.getKinhDi().observe(getViewLifecycleOwner(), new Observer<ArrayList<Phim>>() {
            @Override
            public void onChanged(ArrayList<Phim> phims) {
                listPhimKinhDi.addAll(phims);
                adapterKinhDi.notifyDataSetChanged();
            }
        });

        model.getVoThuat().observe(getViewLifecycleOwner(), new Observer<ArrayList<Phim>>() {
            @Override
            public void onChanged(ArrayList<Phim> phims) {
                listPhimVoThuat.addAll( phims);
                adapterVoThuat.notifyDataSetChanged();
            }
        });

        model.getThanThoai().observe(getViewLifecycleOwner(), new Observer<ArrayList<Phim>>() {
            @Override
            public void onChanged(ArrayList<Phim> phims) {
                listPhimThanThoai.addAll( phims);
                adapterThanThoai.notifyDataSetChanged();
            }
        });
        /////////

        return layout_slideshow;
    }




    private void ShowAllCaterogy() {
        showMoreVoThuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //menuItem.setChecked(true);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Fragment fragment = null;
                fragment = new FragmentCategory();
                //Gửi dữ liệu sang fragmentCategory
                Bundle bundle = new Bundle();
                bundle.putString("theloai","Hanh Dong");
                fragment.setArguments(bundle);

                fragmentTransaction.replace(R.id.fragmentMain,fragment);
                fragmentTransaction.commit();
            }
        });
    }

    // hàm khởi tạo poster ngang cho slideShow
    private void initPoster_SlideShow(){
        dataSlide.add(new myPoster(R.drawable.img_slideshow_01,"Ready Player One"));
        dataSlide.add(new myPoster(R.drawable.img_slideshow_02,"Avenger: Infinity War"));
        dataSlide.add(new myPoster(R.drawable.img_slideshow_03,"The Incredible"));
        dataSlide.add(new myPoster(R.drawable.img_slideshow_04,"Avatar"));
    }
//    private void initPoster_List(){
//        datalist.add(new myPoster(R.drawable.img_1,"Tên Phim 1"));
//        datalist.add(new myPoster(R.drawable.img_2,"Tên Phim 2"));
//        datalist.add(new myPoster(R.drawable.img_3,"Tên Phim 3"));
//        datalist.add(new myPoster(R.drawable.img_4,"Tên Phim 4"));
//        datalist.add(new myPoster(R.drawable.img_5,"Tên Phim 5"));
//    }


}
