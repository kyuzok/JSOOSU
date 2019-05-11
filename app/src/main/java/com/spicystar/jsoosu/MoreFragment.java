package com.spicystar.jsoosu;

import android.app.AlertDialog;
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
        Name = (EditText)v.findViewById(R.id.etName);
        Password = (EditText)v.findViewById(R.id.etPass);
        Login = (Button)v.findViewById(R.id.btnLogin);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = Name.getText().toString().trim();
                String pass = Password.getText().toString().trim();

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pass)) {
                    Toast.makeText(getActivity(),"At least one box is empty", Toast.LENGTH_SHORT).show();
                }

                else if (validate(Name.getText().toString(), Password.getText().toString())) {
                    Toast.makeText(getActivity(),"Login Successful", Toast.LENGTH_SHORT).show();
                }

                else if (!validate(Name.getText().toString(), Password.getText().toString())) {
                    Toast.makeText(getActivity(),"Login Unsuccessful", Toast.LENGTH_SHORT).show();
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
}
