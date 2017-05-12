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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * A login screen that for Firebase server
 */
public class LoginActivity extends AppCompatActivity
        implements View.OnClickListener{

    // Objects for Firebase login authentication
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private static final String TAG = "LoginActivity";

    private TextView mStatusTextView;
    private TextView mDetailTextView;
    private EditText mEmailField;
    private EditText mPasswordField;

    // Get Snippet Font for page. Check FontHelper class for more info.
    @Override
    protected void attachBaseContext(Context newbase){
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newbase));
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Set lock to portrait mode.
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Text fields on screen
        mEmailField = (EditText) findViewById(R.id.email);
        mPasswordField = (EditText) findViewById(R.id.password);

        // Buttons on screen
        findViewById(R.id.email_sign_in_button).setOnClickListener(this);

        // Firebase authentication
        mAuth = FirebaseAuth.getInstance();

        // Firebase authentication code pulled from Firebase tutorial
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

    // Firebase tutorial sign in code
    private void signIn(String email, String password){

        if (!validateForm()) {
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                // If Sign-in fails, display a message to the user.
                // If sign in succeeds, the auth state listener will be notified
                // and logic to handle the signed in user can be handled in the listener.
                if (!task.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                    mEmailField.setError("Email or password incorrect!");
                    mPasswordField.setError("Email or password incorrect!");
                }
                else {
                    System.out.println("Logged in as: " + mAuth.getCurrentUser().getEmail());
                    gotoOpen();
                }

                if (!task.isSuccessful()) {
                   // mStatusTextView.setText("Authentication failed.");
                    System.out.println("Authentication Failed on step 3.");
                }
            }
        });
    }

    // Make sure email, username, and passwords are entered.
    // Also checks if passwords are the same.
    private boolean validateForm() {
        boolean valid = true;

        String email = mEmailField.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mEmailField.setError("Required.");
            valid = false;
        }
        else {
            mEmailField.setError(null);
        }

        String password = mPasswordField.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mPasswordField.setError("Required.");
            valid = false;
        }
        else {
            mPasswordField.setError(null);
        }

        return valid;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.email_sign_in_button) {

            signIn(mEmailField.getText().toString(), mPasswordField.getText().toString());

        }
    }

    // goto functions for button presses.
    // Will change to a different screen and take out old screen from the stack.

    private void gotoOpen() {
        Intent intent = new Intent(LoginActivity.this, Home_screenActivity.class);
        startActivity(intent);
        open_screenActivity.openScreenObj.finish();
        super.finish();
    }

    public void gotoResetPassword(View view){
        Intent intent = new Intent(LoginActivity.this, ResetPasswordActivity.class);
        startActivity(intent);
        open_screenActivity.openScreenObj.finish();
        super.finish();
    }

}

