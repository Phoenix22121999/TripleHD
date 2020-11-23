package com.example.triplehd.LiveModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.triplehd.ObjectClass.User;

public class UserViewModel extends ViewModel {
   private MutableLiveData<User> user = new MutableLiveData<User>();

    public MutableLiveData<User> getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user.setValue(user);
    }
}
