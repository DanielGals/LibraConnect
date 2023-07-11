package com.example.libraconnect;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class StudentHomeFragment extends Fragment {

    private Button cancelborrowbookBtn, checkborrowbookBtn;
    private CardView borrowbookCd;
    private CardView readbookCd;

    confirm_borrowing confirmBorrow = new confirm_borrowing();
    private CardView confirmBorrowDialog;
    private FirebaseAuth mAuth;
    private TextView displayUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_home, container, false);

        displayUser = view.findViewById(R.id.usernameTV);
        borrowbookCd = view.findViewById(R.id.borrowbookCd);
        readbookCd = view.findViewById(R.id.readbookCd);
        confirmBorrowDialog = view.findViewById(R.id.confirmBorrowDialog);
        mAuth = FirebaseAuth.getInstance();


        // Move this line here
        confirmBorrowDialog.setVisibility(View.INVISIBLE);

        borrowbookCd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmBorrowDialog.setVisibility(View.VISIBLE);

                cancelborrowbookBtn = view.findViewById(R.id.cancelborrowbookBtn);
                checkborrowbookBtn = view.findViewById(R.id.checkborrowbookBtn);

                cancelborrowbookBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        confirmBorrowDialog.setVisibility(View.INVISIBLE);
                    }
                });

                checkborrowbookBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.student_frameLayout, confirmBorrow).commit();
                    }
                });
            }
        });

        readbookCd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        displayUserName(view);
        return view;
    }

    public void displayUserName(View view)
    {
        TextView displayUser = view.findViewById(R.id.usernameTV);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String uid = user.getUid();
        DocumentReference userRef = db.collection("students").document(uid);
        userRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        // DocumentSnapshot contains the data
                        String username = document.getString("name");

                        // Use the username string as needed
                        displayUser.setText(username);
                    } else {
                        Log.d("TAG", "No such document");
                    }
                } else {
                    Log.d("TAG", "get failed with ", task.getException());
                }
            }
        });

    }

}
