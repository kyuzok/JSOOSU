package com.spicystar.jsoosu;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.sql.Array;
import java.util.List;

public class EventsFragment extends Fragment {

    private LinearLayout linear;
    private String[] eventsArray;
    private FirebaseFirestore db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_events, container, false);

        linear = v.findViewById(R.id.linear);

        db = FirebaseFirestore.getInstance();


        db.collection("Events").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty()) {
                    List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                    int i = 0;
                    for (DocumentSnapshot d : list) {

                        String date = d.getString("Date");
                        buttons(date);

                    }

                }
            }
        });

        Button butt = new Button(getActivity());
        butt.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        butt.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        linear.addView(butt);


        return v;


    }
        private void buttons(final String date) {

            //create the buttons to show up for each document
            Button btn = new Button(this.getActivity());
            btn.setText(date);
            btn.setAllCaps(false);
            linear.addView(btn);

        }







}
