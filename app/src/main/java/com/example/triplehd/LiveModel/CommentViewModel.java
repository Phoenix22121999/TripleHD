package com.example.triplehd.LiveModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.triplehd.ObjectClass.Comment;
import com.example.triplehd.ObjectClass.Phim;
import com.example.triplehd.ObjectClass.User;

import java.util.ArrayList;

public class CommentViewModel extends ViewModel {
   private MutableLiveData<ArrayList<Comment>> commentList = new MutableLiveData<ArrayList<Comment>>();

    public MutableLiveData<ArrayList<Comment>> getComment() {
        return commentList;
    }

    public void setCommentList(ArrayList<Comment> commentList) {
        this.commentList.setValue(commentList);
    }
}
