package com.example.libraconnect;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class confirm_reading extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      //  // Inflate the layout for this fragment
        return inflater.inflate(R.layout.confirmation, container, false);
    }
}