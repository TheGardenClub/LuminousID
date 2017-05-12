package com.luminousid.luminousid;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.parceler.Parcels;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by chase on 4/21/2017.
 * This will send the user to a bigger image of the glossary photo.
 */

public class glossaryDetailActivity extends AppCompatActivity {

    // Get Calibri Font for page. Check FontHelper class for more info.
    @Override
    protected void attachBaseContext(Context newbase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newbase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // See what glossary list you're coming from.
        // This will allow the activity to display the correct layout with the correct info.
        Intent previousIntent = getIntent();
        String glossaryType = previousIntent.getStringExtra("glossaryType");

        // Set lock to portrait mode.
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        if (glossaryType.equalsIgnoreCase("forbs")) {
            setContentView(R.layout.activity_glossary_detail);

            // Plant info unwrapped using Parcel library.
            glossaryDetails glossaryEntry = Parcels.unwrap(previousIntent.getParcelableExtra("glossaryInfo"));

            // Find all text views and the image view.
            ImageView glossaryPicture = (ImageView) findViewById(R.id.glossaryImage);

            TextView glossaryEntryName = (TextView) findViewById(R.id.glossary_nameText);

            // Set information and picture.
            // Using Glide library for image loading.
            String pictureFile = glossaryEntry.getGlossary_imagename() + ".png";
            Glide.with(this).load(Uri.parse("file:///android_asset/glossaryphotos/forbs_small_keyed/" + pictureFile)).into(glossaryPicture);

            glossaryEntryName.setText(glossaryEntry.getGlossary_name());

        }

        if (glossaryType.equalsIgnoreCase("graminoids")) {
            setContentView(R.layout.activity_glossary_detail);

            // Plant info unwrapped using Parcel library.
            glossaryDetails glossaryEntry = Parcels.unwrap(previousIntent.getParcelableExtra("glossaryInfo"));

            // Find all text views and the image view.
            ImageView glossaryPicture = (ImageView) findViewById(R.id.glossaryImage);

            TextView glossaryEntryName = (TextView) findViewById(R.id.glossary_nameText);

            // Set information and picture.
            // Using Glide library for image loading.
            String pictureFile = glossaryEntry.getGlossary_imagename() + ".png";
            Glide.with(this).load(Uri.parse("file:///android_asset/glossaryphotos/grams_small_keyed/" + pictureFile)).into(glossaryPicture);

            glossaryEntryName.setText(glossaryEntry.getGlossary_name());

        }
    }
}
