package com.example.triplehd;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.triplehd.AsyncTask.LoginTask;
import com.example.triplehd.LiveModel.MainViewModel;
import com.example.triplehd.LiveModel.UserViewModel;
import com.example.triplehd.ObjectClass.User;

public class FragmentLogin extends Fragment {
    EditText editUsername,editPass;
    Button buttonConfirm;
    TextView textError;
    UserViewModel model;
    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_login,container,false);
        editUsername =  layout.findViewById(R.id.edt_user);
        editPass =  layout.findViewById(R.id.edt_password);
        buttonConfirm = layout.findViewById(R.id.btn_Login);
        textError = layout.findViewById(R.id.tvError);

        model = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
        model.getUser().observe(getViewLifecycleOwner(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
//                Log.e("TAG", "onChanged: "+user );
                if (user.getIsLogin()==true){
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    intent.putExtra("username",user.getUsername());
                    intent.putExtra("email",user.getEmail());
                    intent.putExtra("isLogin",true);
                    intent.putExtra("role",user.getRole());
                    getContext().startActivity(intent);
                }
            }
        });
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editUsername.getText().toString().trim();
                String pass = editPass.getText().toString().trim();
                if (username.isEmpty()||pass.isEmpty()){
                    textError.setText("Vui Lòng Nhập Đầy Đủ Thông Tin");
                }
                LoginTask loginTask = new LoginTask(model,textError);
                loginTask.execute(username,pass);
            }
        });

        return layout;
    }
}
