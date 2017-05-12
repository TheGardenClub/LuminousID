package com.luminousid.luminousid;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.luminousid.luminousid.R;

import java.io.File;
import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static com.luminousid.luminousid.R.id.filterButton;
import static com.luminousid.luminousid.R.id.syncButton;

public class MyObservationsActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<observationDetails> tempObsArray;

    private DatabaseReference mObsRef;

    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference storageRef = storage.getReference();

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
        setContentView(R.layout.activity_my_observations);

        // Set firebase reference to observations
        mObsRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://speciesid-ca814.firebaseio.com/speciesid/observations");

        // Set lock to portrait mode.
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Set sync button on screen
        Button syncButton = (Button) findViewById(R.id.syncButton);
        syncButton.setOnClickListener(this);

        // Get reference to our RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.observationListView);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mDividerItemDecoration = new plantDividerItemDecoration(this, R.drawable.plantdivider);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(mDividerItemDecoration);

        tempObsArray = PlantArrayManager.getInstance().getGlobalObservationArray();

        observationAdapter mobservationAdapter = new observationAdapter(this, tempObsArray);
        mRecyclerView.setAdapter(mobservationAdapter);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();

        mRecyclerView.clearDisappearingChildren();
    }

    @Override
    public void onClick(View v){
        int i = v.getId();

        if(i == syncButton){
            syncObservations();
        }

    }

    public void syncObservations() {
        for(int i = 0; i < tempObsArray.size(); i++){
            observationDetails thisObs = tempObsArray.get(i);

            if(thisObs.getIs_synced() == false) {
                thisObs.setIs_synced(true);

                mObsRef.child(thisObs.getKey()).setValue(thisObs);
                mObsRef.child(thisObs.getKey()).child("key").removeValue();
                mObsRef.child(thisObs.getKey()).child("imagePath").removeValue();

                Uri file = Uri.fromFile(new File(thisObs.getImagePath()));
                StorageReference obsRef = storageRef.child("observations/" + thisObs.getKey() + ".jpg");
                UploadTask uploadTask = obsRef.putFile(file);

                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Sadly we failed.
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        //Uri downloadUrl = taskSnapshot.getDownloadUrl();
                    }
                });

                mRecyclerView.getAdapter().notifyItemChanged(i);
            }
        }
    }

}
