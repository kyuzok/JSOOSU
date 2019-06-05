package com.spicystar.jsoosu;

import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(navListener);



    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;


                switch (item.getItemId()) {
                    case R.id.nav_events:
                        selectedFragment = new EventsFragment();
                        break;
                    case R.id.nav_officers:
                        selectedFragment = new OfficersFragment();
                        break;
                    case R.id.nav_photos:
                        selectedFragment = new PhotosFragment();
                        break;
                    case R.id.nav_more:
                        selectedFragment = new MoreFragment();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        selectedFragment).commit();

                return true;
                }
            };

    }



