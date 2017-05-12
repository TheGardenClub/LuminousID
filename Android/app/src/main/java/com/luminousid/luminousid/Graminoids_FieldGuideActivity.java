package com.luminousid.luminousid;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Graminoids_FieldGuideActivity extends AppCompatActivity implements View.OnClickListener {

    // Get Calibri Font for page. Check FontHelper class for more info.
    @Override
    protected void attachBaseContext(Context newbase){
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newbase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graminoids_field_guide);

        // Set lock to portrait mode.
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        findViewById(R.id.cyperButton).setOnClickListener(this);
        findViewById(R.id.juncaButton).setOnClickListener(this);
        findViewById(R.id.poaButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        int i = v.getId();

        if(i == R.id.cyperButton){
            gotoCyperceae();
        }

        else if(i == R.id.juncaButton){
            gotoJuncaceae();
        }

        else if(i == R.id.poaButton){
            gotoPoaceae();
        }
    }

    public void gotoCyperceae(){
        Intent intent = new Intent(Graminoids_FieldGuideActivity.this, Cyperaceae_FieldGuideActivity.class);
        startActivity(intent);
    }

    public void gotoJuncaceae(){
        Intent intent = new Intent(Graminoids_FieldGuideActivity.this, Juncaceae_FieldGuideActivity.class);
        startActivity(intent);
    }

    public void gotoPoaceae(){
        Intent intent = new Intent(Graminoids_FieldGuideActivity.this, Poaceae_FieldGuideActivity.class);
        startActivity(intent);
    }
}
