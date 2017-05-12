package com.luminousid.luminousid;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class open_screenActivity extends AppCompatActivity {

    // So we can finish this later.
    public static open_screenActivity openScreenObj;

    // Objects for Firebase login authentication
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private FirebaseAuth user = null;

    private static final String TAG = "open_screenActivity";

    // Get Snippet Font for page. Check FontHelper class for more info.
    @Override
    protected void attachBaseContext(Context newbase){
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newbase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_screen);

        // Set lock to portrait mode.
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        if(Build.VERSION.SDK_INT < 16){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        else{
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
            //ActionBar actionBar = getActionBar();
            //actionBar.hide();
        }

        // For finishing purposes.
        openScreenObj = this;

        // Firebase authentication
        mAuth = FirebaseAuth.getInstance();

        // Firebase authentication code pulled from Firebase tutorial
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                } else {
                    // User is signed out
                }
            }
        };

        if (mAuth.getCurrentUser() != null){
            System.out.println("Already logged in, skipped open screen");
            gotoHomeFromLoggedIn();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();

        if(Build.VERSION.SDK_INT < 16){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        else{
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
            //ActionBar actionBar = getActionBar();
            //actionBar.hide();
        }
    }

    public void gotoLogin(View view) {
        Intent intent = new Intent(open_screenActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public void gotoSignup(View view) {
        Intent intent = new Intent(open_screenActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    public void gotoHomeScreen(View view) {
        Intent intent = new Intent(open_screenActivity.this, Home_screenActivity.class);
        startActivity(intent);
        super.finish();
    }

    public void gotoHomeFromLoggedIn(){
        Intent intent = new Intent(open_screenActivity.this, Home_screenActivity.class);
        startActivity(intent);
        super.finish();
    }

}
