package com.example.triplehd.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.triplehd.FragmentHome;
import com.example.triplehd.R;
import com.example.triplehd.ObjectClass.myPoster;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class SlideshowAdapter extends SliderViewAdapter<SlideshowAdapter.ViewHolder> {
    private FragmentHome ctx;
    private List<myPoster> data;
    public SlideshowAdapter(FragmentHome context, List<myPoster> object){
        this.ctx = context;
        this.data = object;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        LayoutInflater inf  = LayoutInflater.from(parent.getContext());
        View layout = inf.inflate(R.layout.slideshow_layout,parent,false);
        return new ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        myPoster p = data.get(position);
        viewHolder.name.setText(p.getName());
        viewHolder.img.setImageResource(p.getImage());
        viewHolder.name.setTextSize(16);
        viewHolder.name.setTextColor(Color.WHITE);
    }

    @Override
    public int getCount() {
        return data.size();
    }
    public static class ViewHolder extends SliderViewAdapter.ViewHolder{
        ImageView img;
        TextView name;
        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.name);
        }
    }
}
