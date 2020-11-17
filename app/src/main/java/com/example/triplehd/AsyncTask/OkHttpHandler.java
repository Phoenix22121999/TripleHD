package com.example.triplehd.AsyncTask;

import android.os.AsyncTask;
import android.util.Log;

import com.example.triplehd.ObjectClass.TheLoai;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpHandler extends AsyncTask<String,String,String> {

    OkHttpClient client = new OkHttpClient();

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
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Gson gson =new Gson();
//            Log.e("TAG", "onPostExecute: "+s );

        TheLoai theLoai= gson.fromJson(s,TheLoai.class);
        Log.e("TAG", "onPostExecute: "+ theLoai.getHanhDong().getMovie_1().toString());

    }
}