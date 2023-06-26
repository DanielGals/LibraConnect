package com.example.libraconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Tae malaki

        Intent intent = new Intent(MainActivity.this, StudentHomeActivity.class);
        startActivity(intent);
    }
}
