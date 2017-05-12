package com.luminousid.luminousid;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

/**
 * Created by chase on 4/15/2017.
 */

public class filterOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        //Toast.makeText(parent.getContext(),
        //        "OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
        //        Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0){
        // Nothing
    }

}
