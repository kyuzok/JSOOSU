package com.spicystar.jsoosu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AboutUs extends AppCompatActivity {

    Button bToMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        bToMain = findViewById(R.id.bToMain);
        bToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aboutToMain = new Intent(AboutUs.this,MainActivity.class);
                AboutUs.this.startActivity(aboutToMain);
            }
        });



    }
}
