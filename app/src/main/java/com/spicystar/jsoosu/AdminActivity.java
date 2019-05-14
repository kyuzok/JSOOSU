package com.spicystar.jsoosu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class AdminActivity extends AppCompatActivity implements View.OnClickListener {
//

    private Button AddEvent;
    private Button EditEvent;
    private Button DeleteEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

            AddEvent = findViewById(R.id.btnAddEvent);
            EditEvent = findViewById(R.id.btnEditEvent);
            DeleteEvent = findViewById(R.id.btnDeleteEvent);

            AddEvent.setOnClickListener(this);
            EditEvent.setOnClickListener(this);
            DeleteEvent.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {

       switch (v.getId()) {
           case R.id.btnAddEvent:
               Intent addPage = new Intent (this, EventAddActivity.class);
               startActivity(addPage);
               break;
           case R.id.btnEditEvent:
               Intent editPage = new Intent (this, EventEditActivity.class);
               startActivity(editPage);
               break;
           case R.id.btnDeleteEvent:
               Intent deletePage = new Intent(this, EventDeleteActivity.class);
               startActivity(deletePage);
               break;
       }



    }
}
