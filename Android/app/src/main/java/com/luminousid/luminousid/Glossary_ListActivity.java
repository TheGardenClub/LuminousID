package com.luminousid.luminousid;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Glossary_ListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private plantDividerItemDecoration mDividerItemDecoration;

    // Get Calibri Font for page. Check FontHelper class for more info.
    @Override
    protected void attachBaseContext(Context newbase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newbase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent previousIntent = getIntent();
        String glossaryType = previousIntent.getStringExtra("glossaryType");

        // Set lock to portrait mode.
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        if(glossaryType.equalsIgnoreCase("forbs")){
            setContentView(R.layout.activity_glossaryforbs_list);

            mRecyclerView = (RecyclerView) findViewById(R.id.glossaryForbsListView);
            mRecyclerView.setHasFixedSize(true);

            // Set layout manager. Use the custom item divider, plantdivider class.
            mLayoutManager = new LinearLayoutManager(this);
            mDividerItemDecoration = new plantDividerItemDecoration(this, R.drawable.plantdivider);

            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.addItemDecoration(mDividerItemDecoration);

            glossaryForbsAdapter glossaryAdapter = new glossaryForbsAdapter(this, PlantArrayManager.getInstance().getGlobalGlossaryForbsArray());
            mRecyclerView.setAdapter(glossaryAdapter);
        }

        else if(glossaryType.equalsIgnoreCase("graminoids")){
            setContentView(R.layout.activity_glossarygraminoids_list);

            mRecyclerView = (RecyclerView) findViewById(R.id.glossaryGraminoidsListView);
            mRecyclerView.setHasFixedSize(true);

            // Set layout manager. Use the custom item divider, plantdivider class.
            mLayoutManager = new LinearLayoutManager(this);
            mDividerItemDecoration = new plantDividerItemDecoration(this, R.drawable.plantdivider);

            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.addItemDecoration(mDividerItemDecoration);

            glossaryGraminoidsAdapter glossaryAdapter = new glossaryGraminoidsAdapter(this, PlantArrayManager.getInstance().getGlobalGlossaryGraminoidsArray());
            mRecyclerView.setAdapter(glossaryAdapter);
        }


    }

    @Override
    public void onDestroy(){
        super.onDestroy();

        mRecyclerView.clearDisappearingChildren();
    }

}
