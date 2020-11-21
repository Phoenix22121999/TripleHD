package com.example.triplehd.AsyncTask;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.triplehd.Contant.Contant;
import com.example.triplehd.LiveModel.CategoryViewModel;
import com.example.triplehd.LiveModel.MainViewModel;
import com.example.triplehd.ObjectClass.CategoryMovie;
import com.example.triplehd.ObjectClass.Phim;
import com.example.triplehd.ObjectClass.TheLoai;
import com.example.triplehd.ObjectClass.test;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

public class GetCategoryMovieTask extends AsyncTask<String, String, String> {
    OkHttpClient client = new OkHttpClient();
    CategoryViewModel model;

    public GetCategoryMovieTask(CategoryViewModel model) {
        this.model = model;
    }

    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("genre", params[0]);
            RequestBody formBody = new FormBody.Builder()
                    .add("genre", params[0])
                    .build();
            Request.Builder builder = new Request.Builder();
            builder.url(Contant.URL_CATEGORY).post(formBody);
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
//            Log.e("TAG", "onPostExecute: "+movies.get(1) );

        model.setMovieList(movies);
    }
}
