package com.spicystar.jsoosu;

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
import android.widget.Toast;
import android.text.TextUtils;

import org.w3c.dom.Text;

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
                String name = Name.getText().toString().trim();
                String pass = Password.getText().toString().trim();

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pass)) {
                    Toast.makeText(getActivity(),"At least one box is empty", Toast.LENGTH_SHORT).show();
                }

                validate(name,pass);

            }
        });

        return v;

    }

    private void validate(String userName, String userPassword) {
        if ((userName.equals("JSO")) && (userPassword.equals("634556"))) {
            //shows that the login is successful in a message then moves to a newActivity
            Toast.makeText(getActivity(),"Login Successful", Toast.LENGTH_SHORT).show();
            moveToNewActivity();
        }
        else {
            //error message saying that the login was unsuccessful
            Toast.makeText(getActivity(),"Login Unsuccessful", Toast.LENGTH_SHORT).show();
        }

    }

    private void moveToNewActivity () {

        Intent i = new Intent(getActivity(), AboutUs.class);
        startActivity(i);
        getActivity();

    }

}
