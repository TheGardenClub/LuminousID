package com.luminousid.luminousid;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener {

    // Get Calibri Font for page. Check FontHelper class for more info.
    @Override
    protected void attachBaseContext(Context newbase){
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newbase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // Set lock to portrait mode.
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Get buttons
        Button howtoButton = (Button) findViewById(R.id.howtoButton);
        howtoButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();

        if(i == R.id.howtoButton) {
            Intent intent = new Intent(AboutActivity.this, HowToActivity.class);
            startActivity(intent);
            super.finish();
        }
    }

}
