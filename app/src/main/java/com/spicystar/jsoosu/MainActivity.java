package com.spicystar.jsoosu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    Button bToAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bToAbout = findViewById(R.id.bToAbout);

        bToAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainToAbout = new Intent(MainActivity.this,AboutUs.class);
                MainActivity.this.startActivity(mainToAbout);
            }
        });



    }
}
