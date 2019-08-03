package com.spicystar.jsoosu;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


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
