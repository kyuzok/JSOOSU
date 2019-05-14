package com.spicystar.jsoosu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminEditActivity extends AppCompatActivity implements View.OnClickListener {


    private Button AddEvent;
    private Button EditEvent;
    private Button DeleteEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit);

            AddEvent = findViewById(R.id.btnAddEvent);
            EditEvent = findViewById(R.id.btnEditEvent);
            DeleteEvent = findViewById(R.id.btnDeleteEvent);

            AddEvent.setOnClickListener(this);
            EditEvent.setOnClickListener(this);
            DeleteEvent.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        if()
    }
}
