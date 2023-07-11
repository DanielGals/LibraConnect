package com.example.libraconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class StudentHomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    StudentHomeFragment studentHomeFragment = new StudentHomeFragment();
    StudentBookmarkFragment studentBookmarkFragment = new StudentBookmarkFragment();
    StudentMailFragment studentMailFragment = new StudentMailFragment();
    StudentSettingsFragment studentSettingsFragment = new StudentSettingsFragment();
    private FirebaseAuth mAuth;

    private TextView displayUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);

        mAuth = FirebaseAuth.getInstance();



        Log.d("Heys", "Name:asdasdasdddddddd");

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
