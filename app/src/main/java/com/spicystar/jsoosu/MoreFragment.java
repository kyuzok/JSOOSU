package com.spicystar.jsoosu;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class MoreFragment extends Fragment {

    private EditText Name;
    private EditText Password;
    private Button Login;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_more, container, false);
        Name = v.findViewById(R.id.etName);
        Password = v.findViewById(R.id.etPass);
        Login = v.findViewById(R.id.btnLogin);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate(Name.getText().toString(), Password.getText().toString())) {

                    moveToNewActivity();

                    final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Login Successful");
                    builder.setMessage("We did it!");
                }
            }
        });

        return v;

    }

    public boolean validate(String userName, String userPassword) {
        boolean ret = false;
        if ((userName.equals("JSO")) && (userPassword.equals("634556"))) {
            ret = true;
        }
        return ret;
    }


    private void moveToNewActivity () {

        Intent i = new Intent(getActivity(), AboutUs.class);
        startActivity(i);
        getActivity();

    }



}


