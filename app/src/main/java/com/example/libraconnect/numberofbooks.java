package com.example.libraconnect;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

public class numberofbooks extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_numberofbooks, container, false);

        // Assuming this code is in your activity or fragment

        RadioGroup radioGroup = view.findViewById(R.id.bookGroup);
        final ScrollView scrollView = view.findViewById(R.id.scrollView2);
        final EditText editText1 = view.findViewById(R.id.nameofbook1);
        final EditText editText2 = view.findViewById(R.id.nameofbook2);
        final EditText editText3 = view.findViewById(R.id.nameofbook3);
        final TextView TextViewBook1 = view.findViewById(R.id.tvBook1);
        final TextView TextViewBook2 = view.findViewById(R.id.tvBook2);
        final TextView TextViewBook3 = view.findViewById(R.id.tvBook3);

        // Set the EditText fields initially to invisible
        editText1.setVisibility(View.GONE);
        editText2.setVisibility(View.GONE);
        editText3.setVisibility(View.GONE);
        TextViewBook1.setVisibility(View.GONE);
        TextViewBook2.setVisibility(View.GONE);
        TextViewBook3.setVisibility(View.GONE);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.book1) {
                    TextViewBook1.setVisibility(View.VISIBLE);
                    TextViewBook2.setVisibility(View.GONE);
                    TextViewBook3.setVisibility(View.GONE);
                    editText1.setVisibility(View.VISIBLE);
                    editText2.setVisibility(View.GONE);
                    editText3.setVisibility(View.GONE);
                } else if (checkedId == R.id.book2) {
                    TextViewBook1.setVisibility(View.VISIBLE);
                    TextViewBook2.setVisibility(View.VISIBLE);
                    TextViewBook3.setVisibility(View.GONE);
                    editText1.setVisibility(View.VISIBLE);
                    editText2.setVisibility(View.VISIBLE);
                    editText3.setVisibility(View.GONE);
                } else if (checkedId == R.id.book3) {
                    TextViewBook1.setVisibility(View.VISIBLE);
                    TextViewBook2.setVisibility(View.VISIBLE);
                    TextViewBook3.setVisibility(View.VISIBLE);
                    editText1.setVisibility(View.VISIBLE);
                    editText2.setVisibility(View.VISIBLE);
                    editText3.setVisibility(View.VISIBLE);
                }
                scrollView.post(new Runnable() {
                    @Override
                    public void run() {
                        scrollView.fullScroll(View.FOCUS_DOWN);
                    }
                });
            }
        });

        return view;
    }

}