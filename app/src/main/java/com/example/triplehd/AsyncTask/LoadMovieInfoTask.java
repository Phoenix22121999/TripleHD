package com.example.triplehd.AsyncTask;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.example.triplehd.Contant.Contant;
import com.example.triplehd.LiveModel.CategoryViewModel;
import com.example.triplehd.ObjectClass.Phim;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoadMovieInfoTask extends AsyncTask<String, String, String> {
    OkHttpClient client = new OkHttpClient();
    CategoryViewModel model;
    TextView description;

    public LoadMovieInfoTask(CategoryViewModel model) {
        this.model = model;
    }

    public LoadMovieInfoTask(TextView description) {
        this.description = description;
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
            builder.url(Contant.URL_GET_MOVIE_DESCRIPTION).post(formBody);
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
        Phim movies = gson.fromJson(s, Phim.class);
//        Log.e("TAG", "onPostExecute: " + movies);
        description.setText(movies.getDescription());
//        model.setMovieList(movies);
    }
}
