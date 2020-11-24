package com.example.triplehd.LiveModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.triplehd.ObjectClass.Phim;

import java.util.ArrayList;

public class SearchViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Phim>> movieList = new MutableLiveData<ArrayList<Phim>>();

    public LiveData<ArrayList<Phim>> getMovieList() {

        return movieList;
    }
    public void setMovieList(ArrayList<Phim> data) {
        movieList.setValue(data);
    }
}
