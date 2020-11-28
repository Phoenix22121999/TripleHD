package com.example.triplehd.AsyncTask;

import android.os.AsyncTask;
import android.util.Log;

import com.example.triplehd.Contant.Contant;
import com.example.triplehd.LiveModel.CommentViewModel;
import com.example.triplehd.ObjectClass.Comment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SetCommentTask extends AsyncTask<String, String, String> {
    OkHttpClient client = new OkHttpClient();
    CommentViewModel model;
    String movieId;
    public SetCommentTask(CommentViewModel model) {
        this.model = model;
    }


    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        movieId=params[1];
        try {
            RequestBody formBody = new FormBody.Builder()
                    .add("user_id", params[0])
                    .add("movie_id", params[1])
                    .add("comment", params[2])
                    .build();
            Request.Builder builder = new Request.Builder();
            builder.url(Contant.URL_SET_COMMENT).post(formBody);
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
//        Gson gson = new Gson();
//        ArrayList<Comment> comments = gson.fromJson(s, new TypeToken<ArrayList<Comment>>() {
//        }.getType());
////        Log.e("TAG", "onPostExecute: " + movies);
//
//        model.setCommentList(comments);
        if(s =="1"){
            GetCommentTask getCommentTask = new GetCommentTask(model);
            getCommentTask.execute(movieId);
        }else{
            Log.e("TAG", "onPostExecute: " +s );
        }
    }
}
