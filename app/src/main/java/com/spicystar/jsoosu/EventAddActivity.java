package com.spicystar.jsoosu;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EventAddActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etName;
    private EditText etDate;
    private TimePicker startPicker;
    private TimePicker endPicker;
    private EditText etLocation;
    private EditText etDescription;
    private Button btnAdd;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_add);

        etName = findViewById(R.id.etName);
        etDate = findViewById(R.id.etDate);
        startPicker = findViewById(R.id.startPicker);
        endPicker = findViewById(R.id.endPicker);
        etLocation = findViewById(R.id.etLocation);
        etDescription = findViewById(R.id.etDescription);
        btnAdd = findViewById(R.id.btnAdd);

        db = FirebaseFirestore.getInstance();

        //implement onclick listener for button
        btnAdd.setOnClickListener(this);




    }
        //method for data entry
        private void dataEntry() {
            int startHour = startPicker.getCurrentHour();
            int startMin = startPicker.getCurrentMinute();
            int endHour = endPicker.getCurrentHour();
            int endMin = endPicker.getCurrentMinute();

            String startTime = startHour + ":" + startMin;
            String endTime = endHour + ":" + endMin;

            //used for error messages
            int start = (startHour * 60) + startMin;
            int end = (endHour * 60) + endMin;


            if(start > end) {
                Toast.makeText(this, "End time is before start time",Toast.LENGTH_SHORT).show();
            }
            else if(start==end) {
                Toast.makeText(this, "Times are the same", Toast.LENGTH_SHORT).show();
            }
            //if the user gets past all the errors then actual data will be entered in firebase
            else {
                //need a map to store the data
                Map<String,String> eventInfo = new HashMap<>();
                eventInfo.put("Name",etName.getText().toString().trim());
                eventInfo.put("Date",etDate.getText().toString().trim());
                eventInfo.put("startTime",startTime);
                eventInfo.put("endTime",endTime);
                eventInfo.put("Location",etLocation.getText().toString().trim());
                eventInfo.put("Description",etDescription.getText().toString().trim());

                //now put the map into the database
                db.collection("Events").document(etName.getText().toString().trim()).set(eventInfo).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(EventAddActivity.this, "Event Added!", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(EventAddActivity.this, "Unsuccessful.", Toast.LENGTH_SHORT).show();
                    }
                });

            }



        }






    @Override
    public void onClick(View v) {
        if(v == btnAdd){
            dataEntry();
        }
    }
}
