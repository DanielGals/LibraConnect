package com.example.libraconnect;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
public class StudentHomeFragment extends Fragment implements View.OnClickListener {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student_home, container, false);

        CardView borrowBookCardView = view.findViewById(R.id.borrowbookCd);
        CardView readBookCardView = view.findViewById(R.id.readbookCd);

        borrowBookCardView.setOnClickListener(this);
        readBookCardView.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.borrowbookCd:
                Navigation.findNavController(v).navigate(R.id.);
                break;
            case R.id.readbookCd:
                Navigation.findNavController(v).navigate(R.id.);
                break;
        }
    }
}