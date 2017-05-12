package com.luminousid.luminousid;

import android.app.Application;
import android.content.Context;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by chase on 3/10/2017.
 * This is used to help change the app's font to Snippet by using the Calligraphy library.
 * The font path is default to assets folder. Snippet.tff is located in assets/fonts.
 * This can be changed to any font, just need the .tff file.
 * More documentation can be seen with actual Calligraphy library documentation.
 * attachBaseContext(Context context) must be created in each activity to use this.
 */

public class FontHelper extends Application {

    @Override
    protected void attachBaseContext(Context newbase){
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newbase));
    }

    @Override
    public void onCreate() {
        super.onCreate();


        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                                                .setDefaultFontPath("fonts/calibri.ttf")
                                                .setFontAttrId(R.attr.fontPath)
                                                .build()
        );

    }

}
