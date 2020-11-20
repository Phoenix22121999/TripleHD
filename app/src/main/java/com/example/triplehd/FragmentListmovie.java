package com.example.triplehd;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentListmovie extends Fragment {
    Button btn_add,btn_edit,btn_delete;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout =  inflater.inflate(R.layout.fragment_list_movie,container,false);
        btn_add = layout.findViewById(R.id.btn_add);
        btn_delete = layout.findViewById(R.id.btn_delete);
        btn_edit = layout.findViewById(R.id.btn_edit);

        onclickButton();
        return layout;
    }

    private void onclickButton() {
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"Add",Toast.LENGTH_SHORT).show();
            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"Delete",Toast.LENGTH_SHORT).show();
            }
        });
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"Edit",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
