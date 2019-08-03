package com.spicystar.jsoosu;

import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Calendar;
import java.util.List;

public class EventsFragment extends Fragment {

    private LinearLayout linear;
    private String[] eventsArray;
    private FirebaseFirestore db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_events, container, false);

        linear = v.findViewById(R.id.linearLayout);

        db = FirebaseFirestore.getInstance();



        db.collection("Events").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty()) {
                    List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                    for (DocumentSnapshot d : list) {
                        String name = d.getString("Name");
                        String date = d.getString("Date");
                        String startTime = d.getString("startTime");
                        String endTime = d.getString("endTime");
                        String location = d.getString("Location");
                        String description = d.getString("Description");

                        createEvent(name,date,startTime,endTime,location,description);


                        eventNotification(name,date,startTime,endTime,location,description);
                    }

                }
            }
        });


        return v;


    }
        private void createEvent(String name, String date, String startTime, String endTime, String location, String description) {

            //sets the parameters and everything for the line/border
            View line = new View(this.getActivity());
            DisplayMetrics dm = getResources().getDisplayMetrics();
            float height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, dm);
            line.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,Math.round(height)));
            line.setBackgroundColor(Color.BLACK);
            linear.addView(line);

            //making TextViews for each part of the event.
            TextView eventTxt = new TextView(this.getActivity());
            eventTxt.setText("Name:" + name + "\n" + "Date:" + date + "\n" + "StartTime:" + startTime
                    + "\n" + "EndTime:" + endTime + "\n" + "Location:" + location + "\n" + "Description:" + description + "\n");
            linear.addView(eventTxt);
        }


        private void eventNotification(String name, String date, String startTime, String endTime, String location, String description) {

            /*the notification should only go through at the right time so need to get the current date and time
             * Need to use Calendar instead of java.time because of minimum API's
             * */

            Calendar rightNow = Calendar.getInstance();
            int currentHour = rightNow.get(Calendar.HOUR_OF_DAY);
            int currentMin = rightNow.get(Calendar.MINUTE);

            int currentDay = rightNow.get(Calendar.DAY_OF_MONTH);
            //starts at 0 so need to add 1 to the month.
            int currentMonth = rightNow.get(Calendar.MONTH) + 1;
            int currentYear = rightNow.get(Calendar.YEAR);

            int startHour = Integer.parseInt(startTime.substring(0, startTime.indexOf(':')));
            int startMin = Integer.parseInt(startTime.substring(startTime.indexOf(':') + 1));

            //use dateSub to reduce the size of String date every step.
            String dateSub = date;
            int dateMonth = Integer.parseInt(dateSub.substring(0, dateSub.indexOf("/")));
            dateSub = dateSub.substring(dateSub.indexOf("/") + 1);
            int dateDay = Integer.parseInt(dateSub.substring(0, dateSub.indexOf("/")));
            dateSub = dateSub.substring(dateSub.indexOf("/") + 1);
            int dateYear = Integer.parseInt(dateSub);

            System.out.println(currentHour + " " + startHour);
            System.out.println(currentMin + " " + startMin);
            System.out.println(currentDay + " " + dateDay);
            System.out.println(currentMonth + " " + dateMonth);
            System.out.println(currentYear + " " + dateYear);


            if ((currentHour == startHour) && (currentMin == startMin) && (currentDay == dateDay) && (currentMonth == dateMonth) /*&& (currentYear == dateYear)*/) {


                System.out.println("in the if statement");
                NotificationCompat.Builder builder = new NotificationCompat.Builder(this.getActivity())
                        .setSmallIcon(R.drawable.ic_event_available_black_24dp)
                        .setContentTitle("Notifications Example")
                        .setContentText("This is a test notification")
                        //removes the notification automatically when the user taps it.
                        .setAutoCancel(true);

                NotificationManager mNotificationManager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);

                // notificationID allows you to update the notification later on.
                mNotificationManager.notify(0, builder.build());



            }


        }



}
