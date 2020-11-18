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
    Toolbar toolbar;
    Button ac,hor, kf ,catoo;
    // ac : Hanh Dong
    // hor: Kinh Di
    // kf : Vo Thuat
    // catoo : Hoat Hinh


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout =  inflater.inflate(R.layout.fragment_category,container,false);
        toolbar = layout.findViewById(R.id.toolbar_category);

        recyclerView = layout.findViewById(R.id.grv_category);
        initPoster_SlideShow();
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),3);

        recyclerView.setLayoutManager(layoutManager);
        adapter = new AdapterMovie(getContext(),R.layout.layout_movie_home,data);
        recyclerView.setAdapter(adapter);
        return layout;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        toolbar.inflateMenu(R.menu.menu_category);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                item.setChecked(true);
                int id = item.getItemId();
                switch (id) {
                    case R.id.ac:
                        Toast.makeText(getActivity(),"Action",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.hor:
                        Toast.makeText(getActivity(),"Horror",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.kf:
                        Toast.makeText(getActivity(),"Kungfu",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.cartoo:
                        Toast.makeText(getActivity(),"Cartoon",Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
//        menu = toolbar.getMenu();
//        inflater.inflate(R.menu.menu_category, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//        switch (id) {
//            case R.id.ac:
//                Toast.makeText(getActivity(),"Action",Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.hor:
//                Toast.makeText(getActivity(),"Horror",Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.kf:
//                Toast.makeText(getActivity(),"Kungfu",Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.cartoo:
//                Toast.makeText(getActivity(),"Cartoon",Toast.LENGTH_SHORT).show();
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }

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
