package com.example.triplehd.AsyncTask;

import android.os.AsyncTask;
import android.util.Log;

import com.example.triplehd.Contant.Contant;
import com.example.triplehd.LiveModel.RelateMovieViewModel;
import com.example.triplehd.ObjectClass.Phim;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoadRelateMovieInfoTask extends AsyncTask<String, String, String> {
    OkHttpClient client = new OkHttpClient();
    RelateMovieViewModel model;

    public LoadRelateMovieInfoTask(RelateMovieViewModel model) {
        this.model = model;
    }


    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            RequestBody formBody = new FormBody.Builder()
                    .add("genre", params[0])
                    .add("id", params[1])
                    .build();
            Request.Builder builder = new Request.Builder();
            builder.url(Contant.URL_GET_MOVIE_RELATE).post(formBody);
            Request request = builder.build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("TAG", "doInBackground: " + e);

        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Gson gson = new Gson();
        ArrayList<Phim> movies = gson.fromJson(s, new TypeToken<ArrayList<Phim>>() {
        }.getType());
//        Log.e("TAG", "onPostExecute: " + movies);

        model.setMovieList(movies);
    }
}
