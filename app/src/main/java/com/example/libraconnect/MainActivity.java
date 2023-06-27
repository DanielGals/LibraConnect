package com.example.libraconnect;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import android.widget.Toast;

import com.google.firebase.auth.OAuthProvider;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        Button signInButton = findViewById(R.id.signInButton);

        // Set an onClickListener to handle button clicks
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the sign-in flow when the button is clicked
                // Check if the user is already signed in
                if (firebaseAuth.getCurrentUser() != null) {
                    // User is already signed in, handle accordingly
                    handleSignInResult(firebaseAuth.getCurrentUser());
                } else {
                    // User is not signed in, start the sign-in flow
                    startSignIn();
                }
            }
        });


    }

    // Method for initiating the sign-in flow
    private void startSignIn() {
        OAuthProvider.Builder provider = OAuthProvider.newBuilder("microsoft.com");

        firebaseAuth.startActivityForSignInWithProvider(this, provider.build())
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        // User is signed in.
                        handleSignInResult(authResult.getUser());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        String errorMessage = e.getMessage();
                        // You can display the error message using a Toast or log it for debugging

                        Toast.makeText(MainActivity.this, "Sign-in failed: " + errorMessage, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // Method for handling the sign-in result
    private void handleSignInResult(FirebaseUser user) {
        // Handle the sign-in result based on the user object
        if (user != null) {
            // User is signed in, handle accordingly
            // Access user information using user.getDisplayName(), user.getEmail(), etc.
            // Redirect to the desired activity
            Intent intent = new Intent(MainActivity.this, StudentHomeActivity.class);
            startActivity(intent);
            finish(); // Optional: Finish the current activity to prevent going back to the sign-in screen
        } else {
            // Sign-in failed, handle accordingly
        }
    }
    //FirebaseAuth.getInstance().signOut();
}
