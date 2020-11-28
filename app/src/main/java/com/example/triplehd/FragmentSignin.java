package com.example.triplehd;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentSignin extends Fragment {
    EditText editEmail, editUsername, editPass;
    Button buttonConfirm;
    TextView textError;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_signin, container, false);
        editUsername = layout.findViewById(R.id.edt_user);
        editPass = layout.findViewById(R.id.edt_password);
        editEmail = layout.findViewById(R.id.edt_email);
        buttonConfirm = layout.findViewById(R.id.btn_Signin);
        textError = layout.findViewById(R.id.tvError);
        //click button Signin
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"Sign In",Toast.LENGTH_SHORT).show();
            }
        });
        return layout;
    }
}
