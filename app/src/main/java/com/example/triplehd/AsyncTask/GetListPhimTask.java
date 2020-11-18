package com.example.triplehd.AsyncTask;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.triplehd.LiveModel.MainViewModel;
import com.example.triplehd.ObjectClass.Phim;
import com.example.triplehd.ObjectClass.TheLoai;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetListPhimTask extends AsyncTask<String,String,String>  {

    OkHttpClient client = new OkHttpClient();
    MainViewModel model;

    public GetListPhimTask(MainViewModel model) {
    this.model=model;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String...params) {

        Request.Builder builder = new Request.Builder();
        builder.url(params[0]);
        Request request = builder.build();

        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        }catch (Exception e){
            e.printStackTrace();
            Log.e("TAG", "doInBackground: " + e );
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Gson gson =new Gson();
//            Log.e("TAG", "onPostExecute: "+s );

        TheLoai theLoai= gson.fromJson(s,TheLoai.class);
//        Log.e("TAG", "onPostExecute: "+ theLoai.getHanhDong().getMovie_1().toString());
        ArrayList<Phim> listHanhDong = new ArrayList<Phim>();
        ArrayList<Phim> listKinhDi = new ArrayList<Phim>();
        ArrayList<Phim> listThanThoai = new ArrayList<Phim>();
        ArrayList<Phim> listVoThuat = new ArrayList<Phim>();

        listHanhDong.add(theLoai.getHanhDong().getMovie_1());
        listHanhDong.add(theLoai.getHanhDong().getMovie_2());
        listHanhDong.add(theLoai.getHanhDong().getMovie_3());
        listHanhDong.add(theLoai.getHanhDong().getMovie_4());
        listKinhDi.add(theLoai.getKinhDi().getMovie_1());
        listKinhDi.add(theLoai.getKinhDi().getMovie_2());
        listKinhDi.add(theLoai.getKinhDi().getMovie_3());
        listKinhDi.add(theLoai.getKinhDi().getMovie_4());
        listThanThoai.add(theLoai.getThanThoai().getMovie_1());
        listThanThoai.add(theLoai.getThanThoai().getMovie_2());
        listThanThoai.add(theLoai.getThanThoai().getMovie_3());
        listThanThoai.add(theLoai.getThanThoai().getMovie_4());
        listVoThuat.add(theLoai.getVoThuat().getMovie_1());
        listVoThuat.add(theLoai.getVoThuat().getMovie_2());
        listVoThuat.add(theLoai.getVoThuat().getMovie_3());
        listVoThuat.add(theLoai.getVoThuat().getMovie_4());

        model.setHanhDong(listHanhDong);
        model.setkinhDi(listKinhDi);
        model.setThanThoai(listThanThoai);
        model.setVoThuat(listVoThuat);

    }
}