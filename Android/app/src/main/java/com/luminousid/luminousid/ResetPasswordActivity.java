package com.luminousid.luminousid;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

// Here we want to send an email to the user's email allowing them to reset their password.
// It will call a function from the Google Firebase library.
// Code taken from http://stackoverflow.com/questions/39866086/change-password-with-firebase-for-android

public class ResetPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private EditText mEmailField;

    // Get Snippet Font for page. Check FontHelper class for more info.
    @Override
    protected void attachBaseContext(Context newbase){
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newbase));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        // Set lock to portrait mode.
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Text Fields on screen
        mEmailField = (EditText) findViewById(R.id.useremailText);

        // Buttons on screen
        findViewById(R.id.sendresetemail).setOnClickListener(this);

       // Firebase Authentication
       mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                }
                else {
                    // User is signed out
                }
            }
        };

    }

    @Override
    public void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop(){
        super.onStop();
        if(mAuthListener != null){
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    // Send the password reset email using Firebase.
    private void resetEmail(String useremail){

        if(!validateForm()){
            return;
        }

        if (mAuth != null){
            mAuth.sendPasswordResetEmail(mEmailField.getText().toString());
        }
        else{
        }

    }

    // Make sure email is entered.
    private boolean validateForm() {
        boolean valid = true;

        String email = mEmailField.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mEmailField.setError("Required.");
            valid = false;
        } else {
            mEmailField.setError(null);
        }

        return valid;
    }

    @Override
    public void onClick(View v){
        int i = v.getId();
        if (i == R.id.sendresetemail){
            resetEmail(mEmailField.getText().toString());
            gotoLogin();
        }
    }

    private void gotoLogin(){
        Intent intent = new Intent(ResetPasswordActivity.this, LoginActivity.class);
        startActivity(intent);
        super.finish();
    }
}
