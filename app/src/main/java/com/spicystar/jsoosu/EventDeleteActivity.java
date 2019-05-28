package com.spicystar.jsoosu;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class EventDeleteActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private LinearLayout linear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_delete);

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
            public boolean onLongClick(final View v) {
                AlertDialog.Builder confirmer = new AlertDialog.Builder(EventDeleteActivity.this);
                confirmer.setMessage("Are you sure you want to delete this event?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //removes button from view
                        linear.removeView(v);
                        //removes the data from database
                        db.collection("Events").document(name).delete();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                return true;
            }
        });

    }

}
