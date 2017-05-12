package com.luminousid.luminousid;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static com.luminousid.luminousid.R.id.logoutButton;

public class Home_screenActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 100;
    public static final String ALLOW_KEY = "ALLOWED";
    public static final String LOCATION_PREF = "location_pref";

    // Get Snippet Font for page. Check FontHelper class for more info.
    @Override
    protected void attachBaseContext(Context newbase){
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newbase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Get Location Permissions from the user
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (getFromPref(this, ALLOW_KEY)) {
                showAlert();
            } else if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)

                    != PackageManager.PERMISSION_GRANTED) {

                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_FINE_LOCATION)) {
                    showAlert();
                } else {
                    // No explanation needed, we can request the permission.
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            MY_PERMISSIONS_REQUEST_LOCATION);
                }
            }
        }
        else {
            // Do nothing
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        // Set lock to portrait mode.
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Get snapshot of Field Guide database
        System.out.println("Attempting to take snapshot");
        takeFieldGuideSnapshot();
        System.out.println("Snapshot taken");
        System.out.println("Size: " + PlantArrayManager.getInstance().getglobalForbsArray().size());

        // Make all the glossary entries
        makeGlossaryEntries();

        // Buttons on screen
        Button logoutButton = (Button) findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(this);

        findViewById(R.id.fieldguideButton).setOnClickListener(this);
        findViewById(R.id.observationsButton).setOnClickListener(this);
        findViewById(R.id.howtoButton).setOnClickListener(this);
        findViewById(R.id.glossaryButton).setOnClickListener(this);
        findViewById(R.id.howtoButton).setOnClickListener(this);

        //Textview for Login Status
        TextView loginStatus = (TextView) findViewById(R.id.loginStatus);
        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            loginStatus.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());

            // Take snapshot of the account
            takeAccountSnapshot();

        }
        else{
            loginStatus.setText("Guest");
            logoutButton.setText("Log-In/Sign-up");
        }

        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            System.out.println("Currently signed in: " + FirebaseAuth.getInstance().getCurrentUser().getEmail());
        }


    }

    //More Location Permmisions
    public static void saveToPreferences(Context context, String key, Boolean allowed) {
        SharedPreferences myPrefs = context.getSharedPreferences(LOCATION_PREF,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = myPrefs.edit();
        prefsEditor.putBoolean(key, allowed);
        prefsEditor.commit();
    }

    public static Boolean getFromPref(Context context, String key) {
        SharedPreferences myPrefs = context.getSharedPreferences(LOCATION_PREF,
                Context.MODE_PRIVATE);
        return (myPrefs.getBoolean(key, false));
    }

    private void showAlert() {
        AlertDialog alertDialog = new AlertDialog.Builder(Home_screenActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("App needs to access the GPS Location.");

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "DONT ALLOW",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                });

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "ALLOW",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        ActivityCompat.requestPermissions(Home_screenActivity.this,
                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                MY_PERMISSIONS_REQUEST_LOCATION);
                    }
                });
        alertDialog.show();
    }

    private void showSettingsAlert() {
        AlertDialog alertDialog = new AlertDialog.Builder(Home_screenActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("App needs to access the GPS Location.");

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "DONT ALLOW",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        //finish();
                    }
                });

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "SETTINGS",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        alertDialog.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }


    public void onStart(){
        super.onStart();
    }

    public void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v){
        int i = v.getId();

        // Check what button user presses, go there.

        if(i == logoutButton){

            if(FirebaseAuth.getInstance().getCurrentUser() != null){
                FirebaseAuth.getInstance().signOut();
                System.out.println("Previous user signed out.");
                PlantArrayManager.getInstance().setGlobalAccountDetails(new ArrayList<accountDetails>());
                gotoOpen();
                super.finish();
            }
            else{
                gotoOpen();
            }

        }

        else if(i == R.id.howtoButton){
            gotoHowTo();
        }

        else if(i == R.id.fieldguideButton){
            gotoFieldGuide();
        }

        else if(i == R.id.glossaryButton){
            gotoGlossary();
        }

        else if(i == R.id.observationsButton) {
            gotoMyObservations();
        }


    }

    public void gotoOpen(){
        Intent intent = new Intent(Home_screenActivity.this, open_screenActivity.class);
        startActivity(intent);
        super.finish();
    }

    public void gotoHowTo(){
        Intent intent = new Intent(Home_screenActivity.this, HowToActivity.class);
        startActivity(intent);
    }

    public void gotoFieldGuide(){
        Intent intent = new Intent(Home_screenActivity.this, Intro_FieldGuideActivity.class);
        startActivity(intent);
    }

    public void gotoGlossary(){
        Intent intent = new Intent(Home_screenActivity.this, Glossary_IntroActivity.class);
        startActivity(intent);
    }

    public void gotoMyObservations() {
        Intent intent = new Intent(Home_screenActivity.this, MyObservationsActivity.class);
        startActivity(intent);
    }

    public void takeAccountSnapshot() {
        DatabaseReference accountRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://speciesid-ca814.firebaseio.com/speciesid/accounts");

        accountRef.orderByKey().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ArrayList<accountDetails> newAccountDetails = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                    if(snapshot.getKey().equalsIgnoreCase(FirebaseAuth.getInstance().getCurrentUser().getUid())){
                        newAccountDetails.add(snapshot.getValue(accountDetails.class));
                        System.out.println("UID Added: " + FirebaseAuth.getInstance().getCurrentUser().getUid());
                        System.out.println("With email: " + FirebaseAuth.getInstance().getCurrentUser().getEmail());
                    }

                }

                PlantArrayManager.getInstance().setGlobalAccountDetails(newAccountDetails);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    public void takeFieldGuideSnapshot(){

        Toast.makeText(this, "Attempting to read Field Guide from server...", Toast.LENGTH_SHORT).show();

        DatabaseReference forbsRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://speciesid-ca814.firebaseio.com/speciesid/field_guide/forbs");
        DatabaseReference cyperRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://speciesid-ca814.firebaseio.com/speciesid/field_guide/graminoids/cyperaceae");
        DatabaseReference juncaRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://speciesid-ca814.firebaseio.com/speciesid/field_guide/graminoids/juncaceae");
        DatabaseReference poaRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://speciesid-ca814.firebaseio.com/speciesid/field_guide/graminoids/poaceae");
        DatabaseReference deciRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://speciesid-ca814.firebaseio.com/speciesid/field_guide/woody/deciduous");
        DatabaseReference needleRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://speciesid-ca814.firebaseio.com/speciesid/field_guide/woody/needle");


        forbsRef.orderByKey().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<forbsDetails> newForbArray = new ArrayList<forbsDetails>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    newForbArray.add(snapshot.getValue(forbsDetails.class));
                }

                PlantArrayManager.getInstance().setglobalForbsArray(newForbArray);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        cyperRef.orderByKey().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<cyperaceaeDetails> newCyperArray = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    newCyperArray.add(snapshot.getValue(cyperaceaeDetails.class));
                }

                PlantArrayManager.getInstance().setGlobalCyperArray(newCyperArray);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        juncaRef.orderByKey().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<juncaceaeDetails> newJuncaArray = new ArrayList<juncaceaeDetails>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    newJuncaArray.add(snapshot.getValue(juncaceaeDetails.class));
                }

                PlantArrayManager.getInstance().setGlobalJuncaArray(newJuncaArray);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        poaRef.orderByKey().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<poaceaeDetails> newPoaArray = new ArrayList<poaceaeDetails>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    newPoaArray.add(snapshot.getValue(poaceaeDetails.class));
                }

                PlantArrayManager.getInstance().setGlobalPoaArray(newPoaArray);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        deciRef.orderByKey().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<deciduousDetails> newDeciArray = new ArrayList<deciduousDetails>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    newDeciArray.add(snapshot.getValue(deciduousDetails.class));
                }

                PlantArrayManager.getInstance().setGlobalDeciArray(newDeciArray);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        needleRef.orderByKey().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<needleDetails> newNeedleArray = new ArrayList<needleDetails>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    newNeedleArray.add(snapshot.getValue(needleDetails.class));
                }

                PlantArrayManager.getInstance().setGlobalNeedleArray(newNeedleArray);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Toast.makeText(this, "Field Guide can now be accessed!", Toast.LENGTH_SHORT).show();

    }

    public void makeGlossaryEntries() {
        ArrayList<glossaryDetails> newGlossaryForbsArray = new ArrayList<>();
        ArrayList<glossaryDetails> newGlossaryGraminoidsArray = new ArrayList<>();

        glossaryDetails leafArrangementCategory = new glossaryDetails("- Leaf Arrangements -" , "");
        glossaryDetails flowerShapeCategory = new glossaryDetails("- Flower Shapes -", "");
        glossaryDetails leafShapeCategory = new glossaryDetails("- Leaf Shapes -", "");

        glossaryDetails alternate = new glossaryDetails("Alternate", "alternate");
        glossaryDetails basal = new glossaryDetails("Basal", "basal");
        glossaryDetails campanulate = new glossaryDetails("Campanulate", "campanulate");
        glossaryDetails composite = new glossaryDetails("Composite", "composite");
        glossaryDetails cushion = new glossaryDetails("Cushion", "cushion");
        glossaryDetails funnelform = new glossaryDetails("Funnelform", "funnelform");
        glossaryDetails labiate = new glossaryDetails("Labiate", "labiate");
        glossaryDetails oblong = new glossaryDetails("Oblong", "oblong");
        glossaryDetails opposite = new glossaryDetails("Opposite", "opposite");
        glossaryDetails palmate = new glossaryDetails("Palmate", "palmate");
        glossaryDetails papilionaceous = new glossaryDetails("Papilionaceous", "papilionaceous");
        glossaryDetails radial = new glossaryDetails("Radial", "radial");
        glossaryDetails reflexed = new glossaryDetails("Reflexed", "reflexed");
        glossaryDetails round = new glossaryDetails("Round", "round");
        glossaryDetails ternate = new glossaryDetails("Ternate", "ternate");
        glossaryDetails urceolate = new glossaryDetails("Urceolate", "urceolate");
        glossaryDetails whorled = new glossaryDetails("Whorled", "whorled");

        newGlossaryForbsArray.add(flowerShapeCategory);
        newGlossaryForbsArray.add(campanulate);
        newGlossaryForbsArray.add(composite);
        newGlossaryForbsArray.add(funnelform);
        newGlossaryForbsArray.add(labiate);
        newGlossaryForbsArray.add(papilionaceous);
        newGlossaryForbsArray.add(radial);
        newGlossaryForbsArray.add(reflexed);
        newGlossaryForbsArray.add(urceolate);

        newGlossaryForbsArray.add(leafArrangementCategory);
        newGlossaryForbsArray.add(alternate);
        newGlossaryForbsArray.add(basal);
        newGlossaryForbsArray.add(cushion);
        newGlossaryForbsArray.add(opposite);
        newGlossaryForbsArray.add(whorled);

        newGlossaryForbsArray.add(leafShapeCategory);
        newGlossaryForbsArray.add(oblong);
        newGlossaryForbsArray.add(palmate);
        newGlossaryForbsArray.add(round);
        newGlossaryForbsArray.add(ternate);

        PlantArrayManager.getInstance().setGlobalGlossaryForbsArray(newGlossaryForbsArray);

        glossaryDetails leafBladeCategory = new glossaryDetails("- Leaf Blades -", "");
        glossaryDetails pinflorescenceCategory = new glossaryDetails("-Grasses Inflorescence-", "");
        glossaryDetails cinflorescenceCategory = new glossaryDetails("-Sedges Inflorescence-", "");
        glossaryDetails allCategories = new glossaryDetails("- Parts of Plant -", "awn");

        glossaryDetails awn = new glossaryDetails("- Awn Types -", "");
        glossaryDetails awn_absent = new glossaryDetails("Absent", "absent");
        glossaryDetails awn_bent = new glossaryDetails("Bent", "bent");
        glossaryDetails awn_straight = new glossaryDetails("Straight", "straight");
        glossaryDetails awn_twisted = new glossaryDetails("Twisted", "twisted");
        glossaryDetails contracted = new glossaryDetails("Contracted", "contracted");
        glossaryDetails flat = new glossaryDetails("Flat", "flat");
        glossaryDetails floret = new glossaryDetails("Floret", "floret");
        glossaryDetails involute = new glossaryDetails("Involute", "involute");
        glossaryDetails keeled = new glossaryDetails("Keeled", "keeled");
        glossaryDetails open = new glossaryDetails("Open", "open");
        glossaryDetails spikelet = new glossaryDetails("Spikelet", "spikelet");
        glossaryDetails spikes_globose = new glossaryDetails("Globose", "globose");
        glossaryDetails spikes_one = new glossaryDetails("One", "one");
        glossaryDetails spikes_twoormore = new glossaryDetails("Two or More", "twoormore");

        newGlossaryGraminoidsArray.add(allCategories);

        newGlossaryGraminoidsArray.add(leafBladeCategory);
        newGlossaryGraminoidsArray.add(flat);
        newGlossaryGraminoidsArray.add(involute);
        newGlossaryGraminoidsArray.add(keeled);

        newGlossaryGraminoidsArray.add(awn);
        newGlossaryGraminoidsArray.add(awn_absent);
        newGlossaryGraminoidsArray.add(awn_bent);
        newGlossaryGraminoidsArray.add(awn_straight);
        newGlossaryGraminoidsArray.add(awn_twisted);

        newGlossaryGraminoidsArray.add(pinflorescenceCategory);
        newGlossaryGraminoidsArray.add(contracted);
        newGlossaryGraminoidsArray.add(open);

        newGlossaryGraminoidsArray.add(cinflorescenceCategory);
        newGlossaryGraminoidsArray.add(spikes_globose);
        newGlossaryGraminoidsArray.add(spikes_one);
        newGlossaryGraminoidsArray.add(spikes_twoormore);

        PlantArrayManager.getInstance().setGlobalGlossaryGraminoidsArray(newGlossaryGraminoidsArray);
    }

}
