package com.example.libraconnect;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

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
        Button submitbooksbutton = view.findViewById(R.id.submitbooksbutton);

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

        submitbooksbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input1 = editText1.getText().toString();
                String input2 = editText2.getText().toString();
                String input3 = editText3.getText().toString();

                ArrayList<String> dataArray = new ArrayList<>();

                // Add non-empty inputs to the array
                if (!input1.trim().isEmpty()) {
                    dataArray.add(input1);
                }
                if (!input2.trim().isEmpty()) {
                    dataArray.add(input2);
                }
                if (!input3.trim().isEmpty()) {
                    dataArray.add(input3);
                }

                FirebaseFirestore db = FirebaseFirestore.getInstance();
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                String uid = user.getUid();

                DocumentReference docRef = db.collection("students").document(uid);

                docRef.update("books_borrowing", dataArray)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                //QR CODE CLASSS
                                librarianconfirmation fragment2 = new librarianconfirmation();
                                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                fragmentTransaction.replace(R.id.student_frameLayout, fragment2);
                                fragmentTransaction.addToBackStack(null);
                                fragmentTransaction.commit();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.e("TAG", "Error updating data", e);
                            }
                        });

            }
        });

        return view;
    }

}