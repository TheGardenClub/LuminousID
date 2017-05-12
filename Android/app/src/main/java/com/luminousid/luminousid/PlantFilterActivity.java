package com.luminousid.luminousid;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.StrictMode;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class PlantFilterActivity extends AppCompatActivity {

    Context context;

    // Get Snippet Font for page. Check FontHelper class for more info.
    @Override
    protected void attachBaseContext(Context newbase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newbase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getApplicationContext();

        // Set lock to portrait mode.
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // See what plant list you're coming from.
        // This will allow the activity to display the correct layout with the correct info.
        Intent previousIntent = getIntent();
        String plantType = previousIntent.getStringExtra("plantType");

        if(plantType.equalsIgnoreCase("forbs")){
            setContentView(R.layout.activity_forbs_filter);

            // Setup intent to go back to list view of forbs
            final Intent forbsIntent = new Intent(PlantFilterActivity.this, Forbs_FieldGuideActivity.class);

            // Get all spinner menus so we can pull their values.
            final Spinner familyMenu = (Spinner) findViewById(R.id.familyMenu);
            familyMenu.setOnItemSelectedListener(new filterOnItemSelectedListener());

            final Spinner flowercolorMenu = (Spinner) findViewById(R.id.flowercolorMenu);
            flowercolorMenu.setOnItemSelectedListener(new filterOnItemSelectedListener());

            final Spinner flowershapeMenu = (Spinner) findViewById(R.id.flowershapeMenu);
            flowershapeMenu.setOnItemSelectedListener(new filterOnItemSelectedListener());

            final Spinner petalnumberMenu = (Spinner) findViewById(R.id.petalnumberMenu);
            petalnumberMenu.setOnItemSelectedListener(new filterOnItemSelectedListener());

            final Spinner leafarrangementMenu = (Spinner) findViewById(R.id.leafarrangementMenu);
            leafarrangementMenu.setOnItemSelectedListener(new filterOnItemSelectedListener());

            final Spinner leafshapeMenu = (Spinner) findViewById(R.id.leafshapeMenu);
            leafshapeMenu.setOnItemSelectedListener(new filterOnItemSelectedListener());

            final Spinner habitatMenu = (Spinner) findViewById(R.id.habitatMenu);
            habitatMenu.setOnItemSelectedListener(new filterOnItemSelectedListener());

            // Apply Button is found as well
            Button applyButton = (Button) findViewById(R.id.applyButton);
            applyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    forbsIntent.putExtra("filterStatus", "true");
                    forbsIntent.putExtra("family", String.valueOf(familyMenu.getSelectedItem()));
                    forbsIntent.putExtra("flowerColor", String.valueOf(flowercolorMenu.getSelectedItem()));
                    forbsIntent.putExtra("flowerShape", String.valueOf(flowershapeMenu.getSelectedItem()));
                    forbsIntent.putExtra("petalNumber", String.valueOf(petalnumberMenu.getSelectedItem()));
                    forbsIntent.putExtra("leafArrangement", String.valueOf(leafarrangementMenu.getSelectedItem()));
                    forbsIntent.putExtra("leafShape", String.valueOf(leafshapeMenu.getSelectedItem()));
                    forbsIntent.putExtra("habitat", String.valueOf(habitatMenu.getSelectedItem()));

                    startActivity(forbsIntent);

                    if(Forbs_FieldGuideActivity.forbsFGObj != null){
                        Forbs_FieldGuideActivity.forbsFGObj.finish();
                    }

                    PlantFilterActivity.super.finish();

                }
            });

        }

        else if(plantType.equalsIgnoreCase("cyperaceae")){
            setContentView(R.layout.activity_cyper_filter);

            // Setup intent to go back to list view of forbs
            final Intent cyperIntent = new Intent(PlantFilterActivity.this, Cyperaceae_FieldGuideActivity.class);

            // Get all spinner menus so we can pull their values.
            final Spinner stemcrosssectionMenu = (Spinner) findViewById(R.id.stemcrosssectionMenu);
            stemcrosssectionMenu.setOnItemSelectedListener(new filterOnItemSelectedListener());

            final Spinner leafbladeMenu = (Spinner) findViewById(R.id.leafbladeMenu);
            leafbladeMenu.setOnItemSelectedListener(new filterOnItemSelectedListener());

            final Spinner inflorescenceMenu = (Spinner) findViewById(R.id.inflorescenceMenu);
            inflorescenceMenu.setOnItemSelectedListener(new filterOnItemSelectedListener());

            final Spinner spikecolorMenu = (Spinner) findViewById(R.id.spikecolorMenu);
            spikecolorMenu.setOnItemSelectedListener(new filterOnItemSelectedListener());

            final Spinner habitatMenu = (Spinner) findViewById(R.id.habitatMenu);
            habitatMenu.setOnItemSelectedListener(new filterOnItemSelectedListener());

            // Apply Button is found as well
            Button applyButton = (Button) findViewById(R.id.applyButton);
            applyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cyperIntent.putExtra("filterStatus", "true");
                    cyperIntent.putExtra("stemcrossSection", String.valueOf(stemcrosssectionMenu.getSelectedItem()));
                    cyperIntent.putExtra("leafBlade", String.valueOf(leafbladeMenu.getSelectedItem()));
                    cyperIntent.putExtra("inflorescence", String.valueOf(inflorescenceMenu.getSelectedItem()));
                    cyperIntent.putExtra("spikeColor", String.valueOf(spikecolorMenu.getSelectedItem()));
                    cyperIntent.putExtra("habitat", String.valueOf(habitatMenu.getSelectedItem()));

                    startActivity(cyperIntent);

                    if(Cyperaceae_FieldGuideActivity.cyperFGObj != null){
                        Cyperaceae_FieldGuideActivity.cyperFGObj.finish();
                    }

                    PlantFilterActivity.super.finish();

                }
            });

        }

        else if(plantType.equalsIgnoreCase("juncaceae")){
            setContentView(R.layout.activity_junca_filter);

            // Setup intent to go back to list view of forbs
            final Intent juncaIntent = new Intent(PlantFilterActivity.this, Juncaceae_FieldGuideActivity.class);

            // Get all spinner menus so we can pull their values.
            final Spinner stemcrosssectionMenu = (Spinner) findViewById(R.id.stemcrosssectionMenu);
            stemcrosssectionMenu.setOnItemSelectedListener(new filterOnItemSelectedListener());

            final Spinner leafbladeMenu = (Spinner) findViewById(R.id.leafbladeMenu);
            leafbladeMenu.setOnItemSelectedListener(new filterOnItemSelectedListener());

            final Spinner habitatMenu = (Spinner) findViewById(R.id.habitatMenu);
            habitatMenu.setOnItemSelectedListener(new filterOnItemSelectedListener());

            // Apply Button is found as well
            Button applyButton = (Button) findViewById(R.id.applyButton);
            applyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    juncaIntent.putExtra("filterStatus", "true");
                    juncaIntent.putExtra("stemcrossSection", String.valueOf(stemcrosssectionMenu.getSelectedItem()));
                    juncaIntent.putExtra("leafBlade", String.valueOf(leafbladeMenu.getSelectedItem()));
                    juncaIntent.putExtra("habitat", String.valueOf(habitatMenu.getSelectedItem()));

                    startActivity(juncaIntent);

                    if(Juncaceae_FieldGuideActivity.juncaFGObj != null){
                        Juncaceae_FieldGuideActivity.juncaFGObj.finish();
                    }

                    PlantFilterActivity.super.finish();

                }
            });

        }

        else if(plantType.equalsIgnoreCase("poaceae")){
            setContentView(R.layout.activity_poa_filter);

            // Setup intent to go back to list view of forbs
            final Intent poaIntent = new Intent(PlantFilterActivity.this, Poaceae_FieldGuideActivity.class);

            // Get all spinner menus so we can pull their values.
            final Spinner stemcrosssectionMenu = (Spinner) findViewById(R.id.stemcrosssectionMenu);
            stemcrosssectionMenu.setOnItemSelectedListener(new filterOnItemSelectedListener());

            final Spinner leafbladeMenu = (Spinner) findViewById(R.id.leafbladeMenu);
            leafbladeMenu.setOnItemSelectedListener(new filterOnItemSelectedListener());

            final Spinner inflorescenceMenu = (Spinner) findViewById(R.id.inflorescenceMenu);
            inflorescenceMenu.setOnItemSelectedListener(new filterOnItemSelectedListener());

            final Spinner floretsperspikeletMenu = (Spinner) findViewById(R.id.floretsperspikeletMenu);
            floretsperspikeletMenu.setOnItemSelectedListener(new filterOnItemSelectedListener());

            final Spinner awnsMenu = (Spinner) findViewById(R.id.awnsMenu);
            awnsMenu.setOnItemSelectedListener(new filterOnItemSelectedListener());

            final Spinner habitatMenu = (Spinner) findViewById(R.id.habitatMenu);
            habitatMenu.setOnItemSelectedListener(new filterOnItemSelectedListener());

            // Apply Button is found as well
            Button applyButton = (Button) findViewById(R.id.applyButton);
            applyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    poaIntent.putExtra("filterStatus", "true");
                    poaIntent.putExtra("stemcrossSection", String.valueOf(stemcrosssectionMenu.getSelectedItem()));
                    poaIntent.putExtra("leafBlade", String.valueOf(leafbladeMenu.getSelectedItem()));
                    poaIntent.putExtra("inflorescence", String.valueOf(inflorescenceMenu.getSelectedItem()));
                    poaIntent.putExtra("floretsperSpikelet", String.valueOf(floretsperspikeletMenu.getSelectedItem()));
                    poaIntent.putExtra("awns", String.valueOf(awnsMenu.getSelectedItem()));
                    poaIntent.putExtra("habitat", String.valueOf(habitatMenu.getSelectedItem()));

                    startActivity(poaIntent);

                    if(Poaceae_FieldGuideActivity.poaFGObj != null){
                        Poaceae_FieldGuideActivity.poaFGObj.finish();
                    }

                    PlantFilterActivity.super.finish();

                }
            });

        }

        else if(plantType.equalsIgnoreCase("deciduous")){
            setContentView(R.layout.activity_deci_filter);

            // Setup intent to go back to list view of forbs
            final Intent deciIntent = new Intent(PlantFilterActivity.this, Deciduous_FieldGuideActivity.class);

            // Get all spinner menus so we can pull their values.
            final Spinner familyMenu = (Spinner) findViewById(R.id.familyMenu);
            familyMenu.setOnItemSelectedListener(new filterOnItemSelectedListener());

            final Spinner leafarrangementMenu = (Spinner) findViewById(R.id.leafarrangementMenu);
            leafarrangementMenu.setOnItemSelectedListener(new filterOnItemSelectedListener());

            final Spinner leafshapeMenu = (Spinner) findViewById(R.id.leafshapeMenu);
            leafshapeMenu.setOnItemSelectedListener(new filterOnItemSelectedListener());

            // Apply Button is found as well
            Button applyButton = (Button) findViewById(R.id.applyButton);
            applyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deciIntent.putExtra("filterStatus", "true");
                    deciIntent.putExtra("family", String.valueOf(familyMenu.getSelectedItem()));
                    deciIntent.putExtra("leafArrangement", String.valueOf(leafarrangementMenu.getSelectedItem()));
                    deciIntent.putExtra("leafShape", String.valueOf(leafshapeMenu.getSelectedItem()));

                    startActivity(deciIntent);

                    if(Deciduous_FieldGuideActivity.deciFGObj != null){
                        Deciduous_FieldGuideActivity.deciFGObj.finish();
                    }

                    PlantFilterActivity.super.finish();

                }
            });

        }

        else if(plantType.equalsIgnoreCase("needle")){
            setContentView(R.layout.activity_needle_filter);

            // Setup intent to go back to list view of forbs
            final Intent needleIntent = new Intent(PlantFilterActivity.this, Needle_FieldGuideActivity.class);

            // Get all spinner menus so we can pull their values.
            final Spinner familyMenu = (Spinner) findViewById(R.id.familyMenu);
            familyMenu.setOnItemSelectedListener(new filterOnItemSelectedListener());

            final Spinner leaftypeMenu = (Spinner) findViewById(R.id.leaftypeMenu);
            leaftypeMenu.setOnItemSelectedListener(new filterOnItemSelectedListener());

            final Spinner needlearrangementMenu = (Spinner) findViewById(R.id.needlearrangementMenu);
            needlearrangementMenu.setOnItemSelectedListener(new filterOnItemSelectedListener());

            final Spinner needlesperfascicleMenu = (Spinner) findViewById(R.id.needlesperfascicleMenu);
            needlesperfascicleMenu.setOnItemSelectedListener(new filterOnItemSelectedListener());

            final Spinner needleapexMenu = (Spinner) findViewById(R.id.needleapexMenu);
            needleapexMenu.setOnItemSelectedListener(new filterOnItemSelectedListener());

            final Spinner coneMenu = (Spinner) findViewById(R.id.coneMenu);
            coneMenu.setOnItemSelectedListener(new filterOnItemSelectedListener());

            // Apply Button is found as well
            Button applyButton = (Button) findViewById(R.id.applyButton);
            applyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    needleIntent.putExtra("filterStatus", "true");
                    needleIntent.putExtra("family", String.valueOf(familyMenu.getSelectedItem()));
                    needleIntent.putExtra("leafType", String.valueOf(leaftypeMenu.getSelectedItem()));
                    needleIntent.putExtra("needleArrangement", String.valueOf(needlearrangementMenu.getSelectedItem()));
                    needleIntent.putExtra("needlesperFascicle", String.valueOf(needlesperfascicleMenu.getSelectedItem()));
                    needleIntent.putExtra("needleApex", String.valueOf(needleapexMenu.getSelectedItem()));
                    needleIntent.putExtra("cone", String.valueOf(coneMenu.getSelectedItem()));

                    startActivity(needleIntent);

                    if(Needle_FieldGuideActivity.needleFGObj != null){
                        Needle_FieldGuideActivity.needleFGObj.finish();
                    }

                    PlantFilterActivity.super.finish();

                }
            });

        }
    }
}
