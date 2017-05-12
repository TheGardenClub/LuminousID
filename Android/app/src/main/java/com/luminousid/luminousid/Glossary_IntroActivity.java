package com.luminousid.luminousid;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.luminousid.luminousid.R;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Glossary_IntroActivity extends AppCompatActivity implements View.OnClickListener {

    // Get Calibri Font for page. Check FontHelper class for more info.
    @Override
    protected void attachBaseContext(Context newbase){
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newbase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glossary_intro);

        // Set lock to portrait mode.
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        findViewById(R.id.forbsButton).setOnClickListener(this);
        findViewById(R.id.graminoidsButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        int i = v.getId();

        if(i == R.id.forbsButton){
            gotoForbsGlossary();
        }

        else if(i == R.id.graminoidsButton){
            gotoGraminoidsGlossary();
        }
    }

    public void gotoForbsGlossary(){
        Intent intent = new Intent(Glossary_IntroActivity.this, Glossary_ListActivity.class);
        intent.putExtra("glossaryType", "forbs");
        startActivity(intent);
    }

    public void gotoGraminoidsGlossary(){
        Intent intent = new Intent(Glossary_IntroActivity.this, Glossary_ListActivity.class);
        intent.putExtra("glossaryType", "graminoids");
        startActivity(intent);
    }
}
