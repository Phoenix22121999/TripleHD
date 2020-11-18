package com.example.triplehd.LiveModel;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.triplehd.AsyncTask.GetListPhimTask;
import com.example.triplehd.ObjectClass.Phim;
import com.example.triplehd.ObjectClass.TheLoai;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Phim>> HanhDong = new MutableLiveData<ArrayList<Phim>>();
    ;
    private MutableLiveData<ArrayList<Phim>> KinhDi = new MutableLiveData<ArrayList<Phim>>();
    ;
    private MutableLiveData<ArrayList<Phim>> ThanThoai = new MutableLiveData<ArrayList<Phim>>();
    ;
    private MutableLiveData<ArrayList<Phim>> VoThuat = new MutableLiveData<ArrayList<Phim>>();
    ;

    public MainViewModel() {
        loadUsers();
    }

    public LiveData<ArrayList<Phim>> getHanhDong() {

        return HanhDong;
    }

    public LiveData<ArrayList<Phim>> getKinhDi() {

        return KinhDi;
    }

    public LiveData<ArrayList<Phim>> getThanThoai() {

        return ThanThoai;
    }

    public LiveData<ArrayList<Phim>> getVoThuat() {

        return VoThuat;
    }

    public void setHanhDong(ArrayList<Phim> data) {
        HanhDong.setValue(data);
    }

    public void setkinhDi(ArrayList<Phim> data) {
        KinhDi.setValue(data);
    }

    public void setThanThoai(ArrayList<Phim> data) {
        ThanThoai.setValue(data);
    }

    public void setVoThuat(ArrayList<Phim> data) {
        VoThuat.setValue(data);
    }

    private void loadUsers() {
        // Do an asynchronous operation to fetch users.

    }

}

