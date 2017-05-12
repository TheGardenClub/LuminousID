package com.luminousid.luminousid;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class HowToActivity extends AppCompatActivity implements View.OnClickListener {

    // Get Calibri Font for page. Check FontHelper class for more info.
    @Override
    protected void attachBaseContext(Context newbase){
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newbase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to);

        // Set lock to portrait mode.
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Get the buttons
        Button aboutButton = (Button) findViewById(R.id.howtoButton);
        aboutButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();

        if(i == R.id.howtoButton) {
            gotoAbout();
        }
    }

    public void gotoAbout() {
        Intent intent = new Intent(HowToActivity.this, AboutActivity.class);
        startActivity(intent);
        super.finish();
    }
}
