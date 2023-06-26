package com.example.libraconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class StudentHomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    StudentHomeFragment studentHomeFragment = new StudentHomeFragment();
    StudentBookmarkFragment studentBookmarkFragment = new StudentBookmarkFragment();
    StudentMailFragment studentMailFragment = new StudentMailFragment();
    StudentSettingsFragment studentSettingsFragment = new StudentSettingsFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);

        bottomNavigationView = findViewById(R.id.studentBottomNav);

        //Start fragment when the app starts
        getSupportFragmentManager().beginTransaction().replace(R.id.student_frameLayout, studentHomeFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                    int itemId = item.getItemId();

                    if (itemId == R.id.homeButton) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.student_frameLayout, studentHomeFragment).commit();
                        return true;
                    } else if (itemId == R.id.bookmarkButton) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.student_frameLayout, studentBookmarkFragment).commit();
                        return true;
                    } else if (itemId == R.id.mailButton) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.student_frameLayout, studentMailFragment).commit();
                        return true;
                    } else if (itemId == R.id.settingsButton) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.student_frameLayout, studentSettingsFragment).commit();
                        return true;
                    }
                return false;
            }
        });

    }
}