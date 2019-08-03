package com.spicystar.jsoosu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class NotifyEventActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private LinearLayout linear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify_event);

        linear = findViewById(R.id.linear);

        db = FirebaseFirestore.getInstance();



        db.collection("Events").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if(!queryDocumentSnapshots.isEmpty()){
                    List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                    for(DocumentSnapshot d: list){
                        String name = d.getString("Name");

                        buttons(name);

                    }

                }
            }
        });

    }


    private void buttons(final String name) {

        //create the buttons to show up for each document
        Button btn = new Button(this);
        btn.setText(name);
        btn.setAllCaps(false);
        linear.addView(btn);

        btn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                notifyEvent(name);

                return true;
            }
        });

    }

    private void notifyEvent(final String name) {



    }

}
