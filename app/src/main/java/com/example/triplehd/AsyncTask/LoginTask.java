package com.example.triplehd.AsyncTask;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.triplehd.Contant.Contant;
import com.example.triplehd.LiveModel.RelateMovieViewModel;
import com.example.triplehd.LiveModel.UserViewModel;
import com.example.triplehd.ObjectClass.User;
import com.google.gson.Gson;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginTask extends AsyncTask<String, Void, String> {
    OkHttpClient client = new OkHttpClient();
    UserViewModel model;
    TextView textError;

    public LoginTask(UserViewModel model, TextView textError) {
        this.model = model;
        this.textError = textError;
    }



    protected String doInBackground(String... params) {
//        String ussername = params[0];
//        String pass = params[1];
        try {
            RequestBody formBody = new FormBody.Builder()
                    .add("email", params[0])
                    .add("pwd", params[1])
                    .build();
            Request.Builder builder = new Request.Builder();
            builder.url(Contant.URL_LOGIN).post(formBody);
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
        try {
            Gson gson = new Gson();
//        ArrayList<Phim> movies = gson.fromJson(s, new TypeToken<ArrayList<Phim>>() {
//        }.getType());
            User user = gson.fromJson(s, User.class);
            model.setUser(user);
//            Log.e("TAG", "onPostExecute: " + user);

//        model.setMovieList(movies);
        }catch (Exception e){
            Log.e("TAG", "onPostExecute: "+e);
            textError.setText(s);
        }

    }
}
