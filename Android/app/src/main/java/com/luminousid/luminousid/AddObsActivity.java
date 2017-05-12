package com.luminousid.luminousid;


import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.location.Location;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;

import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.auth.FirebaseAuth;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static android.location.Location.convert;


public class AddObsActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    public static final int MY_PERMISSIONS_REQUEST_CAMERA = 100;
    public static final String ALLOW_KEY = "ALLOWED";
    public static final String CAMERA_PREF = "camera_pref";
    protected static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 0;

    ExifInterface ei;
    Bitmap bitmap;

    //Info for the observation database
    String gps_lat;
    String gps_long;
    String time_date;
    String comment;
    double d_gps_lat;
    double d_gps_long;
    float gps_accuracy;
    String speciesName;
    String speciesCode;
    String username;
    String userID;
    String key;
    String imagePath;

    // Get Calibri Font for page. Check FontHelper class for more info.
    @Override
    protected void attachBaseContext(Context newbase){
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newbase));
    }


    @Override
    public void onConnected(Bundle connectionHint) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        // Get information from Google Location Services and format it correctly
        Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            d_gps_lat = ((mLastLocation.getLatitude()));
            d_gps_long = ((mLastLocation.getLongitude()));
            gps_accuracy = ( mLastLocation.getAccuracy() );

            gps_lat = convert(d_gps_lat, Location.FORMAT_SECONDS);
            gps_lat = replaceDelimiters(gps_lat);
            gps_lat = gps_lat + " N";

            gps_long = convert(d_gps_long, Location.FORMAT_SECONDS);
            gps_long = replaceDelimiters(gps_long);
            gps_long = gps_long + " W";
        }
    }

    @NonNull
    private static String replaceDelimiters(String str) {
        str = str.replaceFirst(":", "°");
        str = str.replaceFirst(":", "'");
        int pointIndex = str.indexOf(".");
        int endIndex = pointIndex + 1+2;
        if(endIndex < str.length()) {
            str = str.substring(0, endIndex);
        }
        str = str + "\"";
        return str;
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Set lock to portrait mode.
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Intent intent = getIntent();
        speciesName = intent.getStringExtra("PlantName");
        speciesCode = intent.getStringExtra("PlantCode");

        userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        ArrayList<accountDetails> accountDets =  PlantArrayManager.getInstance().getGlobalAccountDetails();
        username = accountDets.get(0).getUsername();

        // Create an instance of GoogleAPIClient.
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        // Once we have our picture, we can put it to the correct layout
        setContentView(R.layout.activity_add_obs);

        // Check user permissions
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (getFromPref(this, ALLOW_KEY)) {
                showAlert();
            } else if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.CAMERA)

                    != PackageManager.PERMISSION_GRANTED) {

                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.CAMERA)) {
                    showAlert();
                } else {
                    // No explanation needed, we can request the permission.
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.CAMERA},
                            MY_PERMISSIONS_REQUEST_CAMERA);
                }
            }
        } else {
            openCamera();
        }

        super.onCreate(savedInstanceState);


    }

    public static void saveToPreferences(Context context, String key, Boolean allowed) {
        SharedPreferences myPrefs = context.getSharedPreferences(CAMERA_PREF,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = myPrefs.edit();
        prefsEditor.putBoolean(key, allowed);
        prefsEditor.commit();
    }

    public static Boolean getFromPref(Context context, String key) {
        SharedPreferences myPrefs = context.getSharedPreferences(CAMERA_PREF,
                Context.MODE_PRIVATE);
        return (myPrefs.getBoolean(key, false));
    }

    // Showing alert for accessing the camera.
    private void showAlert() {
        AlertDialog alertDialog = new AlertDialog.Builder(AddObsActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("App needs to access the Camera.");

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
                        ActivityCompat.requestPermissions(AddObsActivity.this,
                                new String[]{Manifest.permission.CAMERA},
                                MY_PERMISSIONS_REQUEST_CAMERA);
                    }
                });
        alertDialog.show();
    }

    private void showSettingsAlert() {
        AlertDialog alertDialog = new AlertDialog.Builder(AddObsActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("App needs to access the Camera.");

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
                        startInstalledAppDetailsActivity(AddObsActivity.this);
                    }
                });

        alertDialog.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CAMERA: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    openCamera();

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

    protected void createLocationRequest() {
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    public static void startInstalledAppDetailsActivity(final Activity context) {
        if (context == null) {
            return;
        }

        final Intent i = new Intent();
        i.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        i.addCategory(Intent.CATEGORY_DEFAULT);
        i.setData(Uri.parse("package:" + context.getPackageName()));
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        context.startActivity(i);
    }


    String mCurrentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timestamp = String.valueOf(System.currentTimeMillis());
        String imageFileName = timestamp + "_" + userID;
        key = timestamp + "_" + userID;

        // Create temporary picture file, so we can save the file path with the observational data
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        imagePath = image.getAbsolutePath();

        time_date = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a").format(new Date());

        return image;
    }
    static final int REQUEST_TAKE_PHOTO = 1;

    private void openCamera() {
        Intent takePictureIntent = new Intent("android.media.action.IMAGE_CAPTURE");

        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                AlertDialog cameraDialog = new AlertDialog.Builder(AddObsActivity.this).create();
                cameraDialog.setTitle("Alert");
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

   protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       Button submit = (Button) findViewById(R.id.submit);
       submit.setOnClickListener(this);
       String message= "Add a comment (optional)";
       TextView tv2 = (TextView) findViewById(R.id.textView2);
       tv2.setText(message);
       ImageView obs = (ImageView) findViewById(R.id.imageView3);

       //obs.setImageBitmap(BitmapFactory.decodeFile(mCurrentPhotoPath));
       //obs.setRotation(90);

       // Let's attempt to fix the rotation of the picture...
       BitmapFactory.Options bmOptions = new BitmapFactory.Options();
       Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
       if(bitmap != null) {
           bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), true);
       }
       else {
           super.finish();
       }


       try {
           ei = new ExifInterface(mCurrentPhotoPath);
       } catch (IOException e) {
           System.out.println("Failed to get ExifInterface");
       }
       int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
       if(bitmap != null) {
           switch(orientation) {

               case ExifInterface.ORIENTATION_ROTATE_90:
                   bitmap = rotateImage(bitmap, 90);
                   obs.setImageBitmap(bitmap);
                   break;

               case ExifInterface.ORIENTATION_ROTATE_180:
                   bitmap = rotateImage(bitmap, 180);
                   obs.setImageBitmap(bitmap);
                   break;

               case ExifInterface.ORIENTATION_ROTATE_270:
                   bitmap = rotateImage(bitmap, 270);
                   obs.setImageBitmap(bitmap);
                   break;

               case ExifInterface.ORIENTATION_NORMAL:
                   bitmap = rotateImage(bitmap, 270);
                   obs.setImageBitmap(bitmap);
                   break;

               default:
                   bitmap = rotateImage(bitmap, 270);
                   obs.setImageBitmap(bitmap);
                   break;
           }
       }

       TextView SpeciesName = (TextView) findViewById(R.id.obsSpeciesName);
       TextView GPSlat = (TextView) findViewById(R.id.GPSlat);
       TextView GPSlong = (TextView) findViewById(R.id.GPSlong);
       TextView TimeandDate = (TextView) findViewById(R.id.TimeDate);

       TimeandDate.setText(time_date);
       GPSlat.setText(gps_lat);
       GPSlong.setText(gps_long);
       SpeciesName.setText(speciesName);


    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                matrix, true);
    }

    ArrayList<observationDetails> tempObsArray = PlantArrayManager.getInstance().globalObservationArray;

    public void gotoMyObs(){

        EditText editText = (EditText) findViewById(R.id.editText2);
        comment = editText.getText().toString();

        Boolean synced = false;
        int verified = 0;
        observationDetails ObsDet = new observationDetails(key, comment, time_date, d_gps_lat, d_gps_long, gps_accuracy,
                synced, verified, speciesCode, speciesName, username, imagePath);
        tempObsArray.add(ObsDet);
        PlantArrayManager.getInstance().setGlobalObservationArray(tempObsArray);

        Intent intent = new Intent(AddObsActivity.this, MyObservationsActivity.class);
        startActivity(intent);
        super.finish();


    }


    @Override
    public void onClick(View v) {
        int i = v.getId();

        if(i == R.id.submit) {
            // add an observation
            gotoMyObs();
        }
    }
}