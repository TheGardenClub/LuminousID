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

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static com.luminousid.luminousid.R.id.filterButton;
import static com.luminousid.luminousid.R.id.snap;
import static com.luminousid.luminousid.R.id.species_nameText;
import static com.luminousid.luminousid.R.layout.speciesnamelist;

/*
* Creates a listview of all forbs from the Firebase server
* First gets all filter choices from the Filter page, if that was the last page.
* If the filter was chosen, we use FilterPopulate() to loop through all the entries from our global array.
*   We add all the ones that passed the filter to a new array, and return that to populate our list.
* Else, just populate our list view with the global array.
* The adapter is used to get our desired appearance with image, species name, and common name.
*   The adapter will also send us to a details page when a plant is clicked.
*/

public class Forbs_FieldGuideActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private plantDividerItemDecoration mDividerItemDecoration;

    // So we can close this later.
    public static Forbs_FieldGuideActivity forbsFGObj;

    // Get Calibri Font for page. Check FontHelper class for more info.
    @Override
    protected void attachBaseContext(Context newbase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newbase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forbs_field_guide);

        // Set lock to portrait mode.
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // For closing later
        forbsFGObj = this;

        Intent previousIntent = getIntent();
        String filterStatus = previousIntent.getStringExtra("filterStatus");
        String family = previousIntent.getStringExtra("family");
        String flowercolor = previousIntent.getStringExtra("flowerColor");
        String flowershape = previousIntent.getStringExtra("flowerShape");
        String petalnumber = previousIntent.getStringExtra("petalNumber");
        String leafarrangement = previousIntent.getStringExtra("leafArrangement");
        String leafshape = previousIntent.getStringExtra("leafShape");
        String habitat = previousIntent.getStringExtra("habitat");

        // Set filter button on screen
        Button filterButton = (Button) findViewById(R.id.filterButton);
        filterButton.setOnClickListener(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.fieldguideListView);
        mRecyclerView.setHasFixedSize(true);

        // Set layout manager. Use the custom item divider, plantdivider class.
        mLayoutManager = new LinearLayoutManager(this);
        mDividerItemDecoration = new plantDividerItemDecoration(this, R.drawable.plantdivider);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(mDividerItemDecoration);

        ArrayList<forbsDetails> tempForbsArray = PlantArrayManager.getInstance().getglobalForbsArray();

        // If the last screen was the filter options, we then attempt to filter with the chosen categories.
        // Else, just populate the screen with the global forbs array.
        if(filterStatus == null){
            System.out.println("No filters selected, using global array");
            forbsFilterableAdapter speciesFilterAdapter = new forbsFilterableAdapter(this, tempForbsArray);
            mRecyclerView.setAdapter(speciesFilterAdapter);
        }
        else {
            System.out.println("Filters selected, attempting to filter the array");
            ArrayList<forbsDetails> filteredForbsArray = FilterPopulate(tempForbsArray, family, flowercolor,
                    flowershape, petalnumber, leafarrangement, leafshape, habitat);
            forbsFilterableAdapter speciesFilterAdapter = new forbsFilterableAdapter(this, filteredForbsArray);
            speciesFilterAdapter.notifyItemRangeChanged(0, speciesFilterAdapter.getItemCount());
            mRecyclerView.setAdapter(speciesFilterAdapter);
        }

    }

    @Override
    public void onClick(View v){
        int i = v.getId();

        if(i == filterButton){
            gotoFilter();
        }

    }

    @Override
    public void onDestroy(){
        super.onDestroy();

        mRecyclerView.clearDisappearingChildren();
    }

    public ArrayList<forbsDetails> FilterPopulate(ArrayList<forbsDetails> filterForbsArray, final String family, final String flowercolor,
                                  final String flowershape, final String petalnumber,
                                  final String leafarrangement, final String leafshape, final String habitat){

        ArrayList<forbsDetails> filteredForbsArray = new ArrayList<>();

        System.out.println("Attempting to start filtering");

        for(int i = 0; i < filterForbsArray.size(); i++){

            System.out.println("Filtering plant at index: " + i);

            forbsDetails tofilterForb = filterForbsArray.get(i);

            if(!family.equalsIgnoreCase("All") && !tofilterForb.getFamily_name().toLowerCase().contains(family.toLowerCase())){
                // Do nothing
            }
            else if(!flowercolor.equalsIgnoreCase("All") && !tofilterForb.getFlower_color().toLowerCase().contains(flowercolor.toLowerCase())){
                // Do nothing
            }
            else if(!flowershape.equalsIgnoreCase("All") && !tofilterForb.getFlower_shape().toLowerCase().contains(flowershape.toLowerCase())){
                // Do nothing
            }
            else if(!petalnumber.equalsIgnoreCase("All") && !tofilterForb.getPetal_number().toLowerCase().contains(petalnumber.toLowerCase())){
                // Do nothing
            }
            else if(!leafarrangement.equalsIgnoreCase("All") && !tofilterForb.getLeaf_arrangement().toLowerCase().contains(leafarrangement.toLowerCase())){
                // Do nothing
            }
            else if(!leafshape.equalsIgnoreCase("All") && !tofilterForb.getLeaf_shape_filter().toLowerCase().contains(leafshape.toLowerCase())){
                // Do nothing
            }
            else if(!habitat.equalsIgnoreCase("All") && !tofilterForb.getHabitat().toLowerCase().contains(habitat.toLowerCase())){
                // Do nothing
            }
            else{
                filteredForbsArray.add(tofilterForb);
            }

        }

        System.out.println("Returning filtered array");
        return filteredForbsArray;
    }

    public void gotoFilter(){
        Intent intent = new Intent(Forbs_FieldGuideActivity.this, PlantFilterActivity.class);
        intent.putExtra("plantType", "forbs");
        startActivity(intent);
    }

}
