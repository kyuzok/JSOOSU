package com.spicystar.jsoosu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.text.TextUtils;


public class MoreFragment extends Fragment {

    private Button Login;
    private Button Contact;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_more, container, false);

        Login = (Button)v.findViewById(R.id.btnLoginPage);
        Contact = (Button)v.findViewById(R.id.btnContactPage);

        Contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent contactPage = new Intent (getActivity(), ContactActivity.class);
                startActivity(contactPage);
            }
        });


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginPage = new Intent (getActivity(), LoginPage.class);
                startActivity(loginPage);
            }
        });


        return v;

    }

}
