package com.luminousid.luminousid;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.luminousid.luminousid.R;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static com.luminousid.luminousid.R.id.filterButton;
import static com.luminousid.luminousid.R.layout.speciesnamelist;

/*
* Creates a listview of all Poaceae from the Firebase server
* First gets all filter choices from the Filter page, if that was the last page.
* If the filter was chosen, we use FilterPopulate() to loop through all the entries from our global array.
*   We add all the ones that passed the filter to a new array, and return that to populate our list.
* Else, just populate our list view with the global array.
* The adapter is used to get our desired appearance with image, species name, and common name.
*   The adapter will also send us to a details page when a plant is clicked.
*/

public class Poaceae_FieldGuideActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private plantDividerItemDecoration mDividerItemDecoration;

    // So we can close this later.
    public static Poaceae_FieldGuideActivity poaFGObj;

    // Get Calibri Font for page. Check FontHelper class for more info.
    @Override
    protected void attachBaseContext(Context newbase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newbase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poaceae_field_guide);

        // Set lock to portrait mode.
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // For closing this later
        poaFGObj = this;

        // If coming from filters, get all the filter category values
        Intent previousIntent = getIntent();
        String filterStatus = previousIntent.getStringExtra("filterStatus");
        String stemcrosssection = previousIntent.getStringExtra("stemcrossSection");
        String leafblade = previousIntent.getStringExtra("leafBlade");
        String inflorescence = previousIntent.getStringExtra("inflorescence");
        String floretsperspikelet = previousIntent.getStringExtra("floretsperSpikelet");
        String awns = previousIntent.getStringExtra("awns");
        String habitat = previousIntent.getStringExtra("habitat");

        // Set filter button on screen
        Button filterButton = (Button) findViewById(R.id.filterButton);
        filterButton.setOnClickListener(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.fieldguideListView);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mDividerItemDecoration = new plantDividerItemDecoration(this, R.drawable.plantdivider);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(mDividerItemDecoration);

        ArrayList<poaceaeDetails> tempPoaArray = PlantArrayManager.getInstance().getGlobalPoaArray();

        // If the last screen was the filter options, we then attempt to filter with the chosen categories.
        // Else, just populate the screen with the global poaceae array.
        if(filterStatus == null){
            System.out.println("No filters selected, using global array");
            poaFilterableAdapter speciesFilterAdapter = new poaFilterableAdapter(this, tempPoaArray);
            mRecyclerView.setAdapter(speciesFilterAdapter);
        }
        else {
            System.out.println("Filters selected, attempting to filter the array");
            ArrayList<poaceaeDetails> filteredPoaArray = FilterPopulate(tempPoaArray, stemcrosssection,
                    leafblade, inflorescence, floretsperspikelet, awns, habitat);
            poaFilterableAdapter speciesFilterAdapter = new poaFilterableAdapter(this, filteredPoaArray);
            speciesFilterAdapter.notifyItemRangeChanged(0, speciesFilterAdapter.getItemCount());
            mRecyclerView.setAdapter(speciesFilterAdapter);
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();

        mRecyclerView.clearDisappearingChildren();
    }

    public ArrayList<poaceaeDetails> FilterPopulate(ArrayList<poaceaeDetails> filterPoaArray, final String stemcrosssection,
                                                       final String leafblade, final String inflorescence,
                                                       final String floretsperspikelet, final String awns, final String habitat){

        ArrayList<poaceaeDetails> filteredPoaArray = new ArrayList<>();

        System.out.println("Attempting to start filtering");

        for(int i = 0; i < filterPoaArray.size(); i++){

            System.out.println("Filtering plant at index: " + i);

            poaceaeDetails tofilterPoa = filterPoaArray.get(i);

            if(!stemcrosssection.equalsIgnoreCase("All") && !tofilterPoa.getStem_cross_section().toLowerCase().contains(stemcrosssection.toLowerCase())){
                // Do nothing
            }
            else if(!leafblade.equalsIgnoreCase("All") && !tofilterPoa.getLeaf_blade().toLowerCase().contains(leafblade.toLowerCase())){
                // Do nothing
            }
            else if(!inflorescence.equalsIgnoreCase("All") && !tofilterPoa.getInflorescence().toLowerCase().contains(inflorescence.toLowerCase())){
                // Do nothing
            }
            else if(!floretsperspikelet.equalsIgnoreCase("All") && !tofilterPoa.getFlorets_per_spikelet().toLowerCase().contains(floretsperspikelet.toLowerCase())){
                // Do nothing
            }
            else if(!awns.equalsIgnoreCase("All") && !tofilterPoa.getAwns().toLowerCase().contains(awns.toLowerCase())){
                // Do nothing
            }
            else if(!habitat.equalsIgnoreCase("All") && !tofilterPoa.getHabitat().toLowerCase().contains(habitat.toLowerCase())){
                // Do nothing
            }
            else{
                filteredPoaArray.add(tofilterPoa);
            }

        }

        System.out.println("Returning filtered array");
        return filteredPoaArray;
    }

    @Override
    public void onClick(View v){
        int i = v.getId();

        if(i == filterButton){
            gotoFilter();
        }

    }

    public void gotoFilter(){
        Intent intent = new Intent(Poaceae_FieldGuideActivity.this, PlantFilterActivity.class);
        intent.putExtra("plantType", "poaceae");
        startActivity(intent);
    }

}
