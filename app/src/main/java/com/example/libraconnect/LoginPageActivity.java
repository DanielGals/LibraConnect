package com.example.libraconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginPageActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private static final String TAG = "MainActivity";
    EditText emailLgn, passLgn;
    Button loginBtn, createAcc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        mAuth = FirebaseAuth.getInstance();

        FirebaseAuth.getInstance().signOut();
        emailLgn = (EditText) findViewById(R.id.emailLgn);
        passLgn = (EditText) findViewById(R.id.passwordLgn);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        createAcc = (Button) findViewById(R.id.createAccBtnR);


        //Create Account Button
        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(MainActivity.this, CreateAccountPage.class);
                //startActivity(intent);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String Semail = emailLgn.getText().toString().trim();
                String Spass = passLgn.getText().toString().trim();

                // Librarian Mode
                if(Semail.equals("Library123") && Spass.equals("Library123"))
                {
                    //Intent intent = new Intent(MainActivity.this, AdminModeActivity.class);
                    //startActivity(intent);
                }
                else
                {
                    signIn(Semail, Spass);
                }
            }
        });
    }
    private void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(LoginPageActivity.this, StudentHomeActivity.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginPageActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        Log.d("IDcheck", "user logged in" + currentUser);
        if(currentUser != null){
            reload();
        }
    }

    private void reload() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(LoginPageActivity.this, StudentHomeActivity.class);
            startActivity(intent);
        }
    }

}
