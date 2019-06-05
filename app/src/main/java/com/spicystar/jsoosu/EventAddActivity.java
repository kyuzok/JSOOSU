package com.spicystar.jsoosu;

import android.app.DatePickerDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
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

//https://stackoverflow.com/questions/14933330/datepicker-how-to-popup-datepicker-when-click-on-edittext

        final Calendar myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                etDate.setText(sdf.format(myCalendar.getTime()));
            }

        };

        etDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(EventAddActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


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
        if(v == btnAdd) {
            dataEntry();
        }
    }
}
