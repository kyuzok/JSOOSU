package com.spicystar.jsoosu;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class AdminActivity extends AppCompatActivity implements View.OnClickListener {
//

    private Button AddEvent;
    private Button EditEvent;
    private Button DeleteEvent;
    private Button NotifyEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

            AddEvent = findViewById(R.id.btnAddEvent);
            EditEvent = findViewById(R.id.btnEditEvent);
            DeleteEvent = findViewById(R.id.btnDeleteEvent);
            NotifyEvent = findViewById(R.id.btnNotifyEvent);

            AddEvent.setOnClickListener(this);
            EditEvent.setOnClickListener(this);
            DeleteEvent.setOnClickListener(this);
            NotifyEvent.setOnClickListener(this);



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
           case R.id.btnNotifyEvent:
               Intent notifyPage = new Intent(this, NotifyEventActivity.class);
               startActivity(notifyPage);
               break;
       }



    }
}
