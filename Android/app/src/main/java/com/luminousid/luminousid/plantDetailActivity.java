package com.luminousid.luminousid;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.parceler.Parcels;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class plantDetailActivity extends AppCompatActivity implements View.OnClickListener {
    String plantName;
    String plantCode;

    // Get Calibri Font for page. Check FontHelper class for more info.
    @Override
    protected void attachBaseContext(Context newbase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newbase));
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();

        if(i == R.id.addObsButton) {
            // add an observation
            gotoAddObs();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set lock to portrait mode.
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // See what plant list you're coming from.
        // This will allow the activity to display the correct layout with the correct info.
        Intent previousIntent = getIntent();
        String plantType = previousIntent.getStringExtra("plantType");

        if(plantType.equalsIgnoreCase("forbs")){
            setContentView(R.layout.activity_forbs_detail);

            // Set button
            Button addobsButton = (Button) findViewById(R.id.addObsButton);
            addobsButton.setOnClickListener(this);

            // Check if not logged in
            if(PlantArrayManager.getInstance().getGlobalAccountDetails().size() == 0) {
                ((ViewGroup) addobsButton.getParent()).removeView(addobsButton);
            }

            // Plant info unwrapped using Parcel library.
            forbsDetails forbPlant = Parcels.unwrap(previousIntent.getParcelableExtra("plantInfo"));

            String plant_code = changeIfNA(forbPlant.getPlant_code());
            String species_name = changeIfNA(forbPlant.getSpecies_name());
            String common_name = changeIfNA(forbPlant.getCommon_name());
            String synonyms = changeIfNA(forbPlant.getSynonyms());
            String family_name = changeIfNA(forbPlant.getFamily_name());
            String flower_color = changeIfNA(forbPlant.getFlower_color());
            String flower_shape = changeIfNA(forbPlant.getFlower_shape());
            String petal_number = changeIfNA(forbPlant.getPetal_number());
            String habitat = changeIfNA(forbPlant.getHabitat());
            String leaf_arrangement = changeIfNA(forbPlant.getLeaf_arrangement());
            String leaf_shape_filter = changeIfNA(forbPlant.getLeaf_shape_filter());
            String notes = changeIfNA(forbPlant.getNotes());
            String photo_credit = changeIfNA(forbPlant.getPhoto_credit());
            plantCode = plant_code;
            plantName = species_name;

            // Find all text views and the image view.
            ImageView plantPicture = (ImageView) findViewById(R.id.forbsPlantImage);

            TextView forbsSpeciesName = (TextView) findViewById(R.id.SpeciesName);
            TextView forbsCommonName = (TextView) findViewById(R.id.forbsCommonName);
            TextView forbsSynonyms = (TextView) findViewById(R.id.forbsSynonyms);
            TextView forbsFamilyName = (TextView) findViewById(R.id.forbsFamilyName);
            TextView forbsFlowerColor = (TextView) findViewById(R.id.forbsFlowerColor);
            TextView forbsFlowerShape = (TextView) findViewById(R.id.forbsFlowerShape);
            TextView forbsPetalNumber = (TextView) findViewById(R.id.forbsPetalNumber);
            TextView forbsHabitat = (TextView) findViewById(R.id.forbsHabitat);
            TextView forbsLeafArrangement = (TextView) findViewById(R.id.forbsLeafArrangement);
            TextView forbsLeafShape = (TextView) findViewById(R.id.forbsLeafShape);
            TextView forbsNotes = (TextView) findViewById(R.id.forbsNotes);
            TextView forbsPhotoCredit = (TextView) findViewById(R.id.forbsPhotoCredit);

            // Set information and picture.
            // Using Glide library for image loading.
            String pictureFile = plant_code + "_1.jpg";
            Glide.with(this).load(Uri.parse("file:///android_asset/plantphotos/forbs/" + pictureFile)).into(plantPicture);

            forbsSpeciesName.setText(species_name);
            forbsCommonName.setText("Common Name: " + common_name);
            forbsSynonyms.setText("Synonyms: " + synonyms);
            forbsFamilyName.setText("Family: " + family_name);
            forbsFlowerColor.setText("Flower Color: " + flower_color);
            forbsFlowerShape.setText("Flower Shape: " + flower_shape);
            forbsPetalNumber.setText("Petal Number: " + petal_number);
            forbsHabitat.setText("Habitat: " + habitat);
            forbsLeafArrangement.setText("Leaf Arrangement: " + leaf_arrangement);
            forbsLeafShape.setText("Leaf Shape: " + leaf_shape_filter);
            forbsNotes.setText("Notes: " + notes);
            forbsPhotoCredit.setText("Photo Credit: " + photo_credit);

        }

        else if(plantType.equalsIgnoreCase("cyperaceae")){
            setContentView(R.layout.activity_cyperceae_detail);

            // Add observation button
            Button addobsButton = (Button) findViewById(R.id.addObsButton);
            addobsButton.setOnClickListener(this);

            // Check if not logged in
            if(PlantArrayManager.getInstance().getGlobalAccountDetails().size() == 0) {
                ((ViewGroup) addobsButton.getParent()).removeView(addobsButton);
            }

            // Plant detail unwrapped using Parcel Library
            cyperaceaeDetails cyperPlant = Parcels.unwrap(previousIntent.getParcelableExtra("plantInfo"));

            String plant_code = changeIfNA(cyperPlant.getPlant_code());
            String species_name = changeIfNA(cyperPlant.getSpecies_name());
            String common_name = changeIfNA(cyperPlant.getCommon_name());
            String synonyms = changeIfNA(cyperPlant.getSynonyms());
            String family_name = changeIfNA(cyperPlant.getFamily_name());
            String habitat = changeIfNA(cyperPlant.getHabitat());
            String notes = changeIfNA(cyperPlant.getNotes());
            String photo_credit = changeIfNA(cyperPlant.getPhoto_credit());

            String inflorescence = changeIfNA(cyperPlant.getInflorescence());
            String leaf_blade = changeIfNA(cyperPlant.getLeaf_blade());
            String spike_color = changeIfNA(cyperPlant.getSpike_color());
            String stem_cross_section = changeIfNA(cyperPlant.getStem_cross_section());
            plantCode = plant_code;
            plantName = species_name;

            // Find all text views and the image view.
            ImageView plantPicture = (ImageView) findViewById(R.id.cyperPlantImage);

            TextView SpeciesName = (TextView) findViewById(R.id.SpeciesName);
            TextView CommonName = (TextView) findViewById(R.id.cyperCommonName);
            TextView Synonyms = (TextView) findViewById(R.id.cyperSynonyms);
            TextView FamilyName = (TextView) findViewById(R.id.cyperFamilyName);
            TextView Habitat = (TextView) findViewById(R.id.cyperHabitat);
            TextView Notes = (TextView) findViewById(R.id.cyperNotes);
            TextView PhotoCredit = (TextView) findViewById(R.id.cyperPhotoCredit);

            TextView Inflorescence = (TextView) findViewById(R.id.cyperInflorescence);
            TextView LeafBlade = (TextView) findViewById(R.id.cyperLeafBlade);
            TextView SpikeColor = (TextView) findViewById(R.id.cyperSpikeColor);
            TextView StemCrossSection = (TextView) findViewById(R.id.cyperStemCrossSection);

            // Set information and picture.
            // Using Glide library for image loading.
            String pictureFile = plant_code + "_1.jpg";
            Glide.with(this).load(Uri.parse("file:///android_asset/plantphotos/graminoids/" + pictureFile)).into(plantPicture);

            SpeciesName.setText(species_name);
            CommonName.setText("Common Name: " + common_name);
            Synonyms.setText("Synonyms: " + synonyms);
            FamilyName.setText("Family: " + family_name);
            Inflorescence.setText("Inflorescence: " + inflorescence);
            LeafBlade.setText("Leaf Blade: " + leaf_blade);
            SpikeColor.setText("Spike Color: " + spike_color);
            Habitat.setText("Habitat: " + habitat);
            StemCrossSection.setText("Stem Cross Section: " + stem_cross_section);
            Notes.setText("Notes: " + notes);
            PhotoCredit.setText("Photo Credit: " + photo_credit);

        }

        else if(plantType.equalsIgnoreCase("juncaceae")) {
            setContentView(R.layout.activity_juncaceae_detail);

            // Add observation button
            Button addobsButton = (Button) findViewById(R.id.addObsButton);
            addobsButton.setOnClickListener(this);

            // Check if not logged in
            if(PlantArrayManager.getInstance().getGlobalAccountDetails().size() == 0) {
                ((ViewGroup) addobsButton.getParent()).removeView(addobsButton);
            }

            // Plant detail unwrapped using Parcel Library
            juncaceaeDetails juncaPlant = Parcels.unwrap(previousIntent.getParcelableExtra("plantInfo"));

            String plant_code = changeIfNA(juncaPlant.getPlant_code());
            String species_name = changeIfNA(juncaPlant.getSpecies_name());
            String common_name = changeIfNA(juncaPlant.getCommon_name());
            String synonyms = changeIfNA(juncaPlant.getSynonyms());
            String family_name = changeIfNA(juncaPlant.getFamily_name());
            String habitat = changeIfNA(juncaPlant.getHabitat());
            String notes = changeIfNA(juncaPlant.getNotes());
            String photo_credit = changeIfNA(juncaPlant.getPhoto_credit());

            String leaf_blade = changeIfNA(juncaPlant.getLeaf_blade());
            String stem_cross_section = changeIfNA(juncaPlant.getStem_cross_section());
            plantCode = plant_code;
            plantName = species_name;

            // Find all text views and the image view.
            ImageView plantPicture = (ImageView) findViewById(R.id.PlantImage);

            TextView SpeciesName = (TextView) findViewById(R.id.SpeciesName);
            TextView CommonName = (TextView) findViewById(R.id.CommonName);
            TextView Synonyms = (TextView) findViewById(R.id.Synonyms);
            TextView FamilyName = (TextView) findViewById(R.id.FamilyName);
            TextView Habitat = (TextView) findViewById(R.id.Habitat);
            TextView Notes = (TextView) findViewById(R.id.Notes);
            TextView PhotoCredit = (TextView) findViewById(R.id.PhotoCredit);

            TextView LeafBlade = (TextView) findViewById(R.id.LeafBlade);
            TextView StemCrossSection = (TextView) findViewById(R.id.StemCrossSection);

            // Set information and picture.
            // Using Glide library for image loading.
            String pictureFile = plant_code + "_1.jpg";
            Glide.with(this).load(Uri.parse("file:///android_asset/plantphotos/graminoids/" + pictureFile)).into(plantPicture);

            SpeciesName.setText(species_name);
            CommonName.setText("Common Name: " + common_name);
            Synonyms.setText("Synonyms: " + synonyms);
            FamilyName.setText("Family: " + family_name);
            LeafBlade.setText("Leaf Blade: " + leaf_blade);
            Habitat.setText("Habitat: " + habitat);
            StemCrossSection.setText("Stem Cross Section: " + stem_cross_section);
            Notes.setText("Notes: " + notes);
            PhotoCredit.setText("Photo Credit: " + photo_credit);
        }

        else if(plantType.equalsIgnoreCase("poaceae")) {
            setContentView(R.layout.activity_poaceae_detail);

            // Add observation button
            Button addobsButton = (Button) findViewById(R.id.addObsButton);
            addobsButton.setOnClickListener(this);

            // Check if not logged in
            if(PlantArrayManager.getInstance().getGlobalAccountDetails().size() == 0) {
                ((ViewGroup) addobsButton.getParent()).removeView(addobsButton);
            }

            // Plant detail unwrapped using Parcel Library
            poaceaeDetails poaPlant = Parcels.unwrap(previousIntent.getParcelableExtra("plantInfo"));

            String plant_code = changeIfNA(poaPlant.getPlant_code());
            String species_name = changeIfNA(poaPlant.getSpecies_name());
            String common_name = changeIfNA(poaPlant.getCommon_name());
            String synonyms = changeIfNA(poaPlant.getSynonyms());
            String family_name = changeIfNA(poaPlant.getFamily_name());
            String habitat = changeIfNA(poaPlant.getHabitat());
            String notes = changeIfNA(poaPlant.getNotes());
            String photo_credit = changeIfNA(poaPlant.getPhoto_credit());

            String inflorescence = changeIfNA(poaPlant.getInflorescence());
            String leaf_blade = changeIfNA(poaPlant.getLeaf_blade());
            String awns = changeIfNA(poaPlant.getAwns());
            String florets_per_spikelet = changeIfNA(poaPlant.getFlorets_per_spikelet());
            String stem_cross_section = changeIfNA(poaPlant.getStem_cross_section());
            plantCode = plant_code;
            plantName = species_name;

            // Find all text views and the image view.
            ImageView plantPicture = (ImageView) findViewById(R.id.PlantImage);

            TextView SpeciesName = (TextView) findViewById(R.id.SpeciesName);
            TextView CommonName = (TextView) findViewById(R.id.CommonName);
            TextView Synonyms = (TextView) findViewById(R.id.Synonyms);
            TextView FamilyName = (TextView) findViewById(R.id.FamilyName);
            TextView Habitat = (TextView) findViewById(R.id.Habitat);
            TextView Notes = (TextView) findViewById(R.id.Notes);
            TextView PhotoCredit = (TextView) findViewById(R.id.PhotoCredit);

            TextView Inflorescence = (TextView) findViewById(R.id.Inflorescence);
            TextView LeafBlade = (TextView) findViewById(R.id.LeafBlade);
            TextView Awns = (TextView) findViewById(R.id.Awns);
            TextView FloretsPerSpikelet = (TextView) findViewById(R.id.FloretsPerSpikelet);
            TextView StemCrossSection = (TextView) findViewById(R.id.StemCrossSection);

            // Set information and picture.
            // Using Glide library for image loading.
            String pictureFile = plant_code + "_1.jpg";
            Glide.with(this).load(Uri.parse("file:///android_asset/plantphotos/graminoids/" + pictureFile)).into(plantPicture);

            SpeciesName.setText(species_name);
            CommonName.setText("Common Name: " + common_name);
            Synonyms.setText("Synonyms: " + synonyms);
            FamilyName.setText("Family: " + family_name);
            Inflorescence.setText("Inflorescence: " + inflorescence);
            LeafBlade.setText("Leaf Blade: " + leaf_blade);
            Awns.setText("Awns: " + awns);
            FloretsPerSpikelet.setText("Florets per Spikelet: " + florets_per_spikelet);
            Habitat.setText("Habitat: " + habitat);
            StemCrossSection.setText("Stem Cross Section: " + stem_cross_section);
            Notes.setText("Notes: " + notes);
            PhotoCredit.setText("Photo Credit: " + photo_credit);

        }

        else if(plantType.equalsIgnoreCase("deciduous")){
            setContentView(R.layout.activity_deciduous_detail);

            // Add observation button
            Button addobsButton = (Button) findViewById(R.id.addObsButton);
            addobsButton.setOnClickListener(this);

            // Check if not logged in
            if(PlantArrayManager.getInstance().getGlobalAccountDetails().size() == 0) {
                ((ViewGroup) addobsButton.getParent()).removeView(addobsButton);
            }

            // Plant detail unwrapped using Parcel Library
            deciduousDetails deciPlant = Parcels.unwrap(previousIntent.getParcelableExtra("plantInfo"));

            String plant_code = changeIfNA(deciPlant.getPlant_code());
            String species_name = changeIfNA(deciPlant.getSpecies_name());
            String common_name = changeIfNA(deciPlant.getCommon_name());
            String synonyms = changeIfNA(deciPlant.getSynonyms());
            String family_name = changeIfNA(deciPlant.getFamily_name());
            String notes = changeIfNA(deciPlant.getNotes());
            String photo_credit = changeIfNA(deciPlant.getPhoto_credit());

            String leaf_arrangement = changeIfNA(deciPlant.getLeaf_arrangement());
            String leaf_margin = changeIfNA(deciPlant.getLeaf_margin());
            String leaf_shape = changeIfNA(deciPlant.getLeaf_shape());
            String leaf_type = changeIfNA(deciPlant.getLeaf_type());
            plantCode = plant_code;
            plantName = species_name;

            // Find all text views and the image view.
            ImageView plantPicture = (ImageView) findViewById(R.id.PlantImage);

            TextView SpeciesName = (TextView) findViewById(R.id.SpeciesName);
            TextView CommonName = (TextView) findViewById(R.id.CommonName);
            TextView Synonyms = (TextView) findViewById(R.id.Synonyms);
            TextView FamilyName = (TextView) findViewById(R.id.FamilyName);
            TextView Notes = (TextView) findViewById(R.id.Notes);
            TextView PhotoCredit = (TextView) findViewById(R.id.PhotoCredit);

            TextView LeafArrangement = (TextView) findViewById(R.id.LeafArrangement);
            TextView LeafMargin = (TextView) findViewById(R.id.LeafMargin);
            TextView LeafShape = (TextView) findViewById(R.id.LeafShape);
            TextView LeafType = (TextView) findViewById(R.id.LeafType);

            // Set information and picture.
            // Using Glide library for image loading.
            String pictureFile = plant_code + ".jpg";
            Glide.with(this).load(Uri.parse("file:///android_asset/plantphotos/woody/" + pictureFile)).into(plantPicture);

            SpeciesName.setText(species_name);
            CommonName.setText("Common Name: " + common_name);
            Synonyms.setText("Synonyms: " + synonyms);
            FamilyName.setText("Family: " + family_name);
            LeafArrangement.setText("Leaf Arrangement: " + leaf_arrangement);
            LeafMargin.setText("Leaf Margin: " + leaf_margin);
            LeafShape.setText("Leaf Shape: " + leaf_shape);
            LeafType.setText("Leaf Type: " + leaf_type);
            Notes.setText("Notes: " + notes);
            PhotoCredit.setText("Photo Credit: " + photo_credit);

        }

        else if(plantType.equalsIgnoreCase("needle")){
            setContentView(R.layout.activity_needle_detail);

            // Add observation button
            Button addobsButton = (Button) findViewById(R.id.addObsButton);
            addobsButton.setOnClickListener(this);

            // Check if not logged in
            if(PlantArrayManager.getInstance().getGlobalAccountDetails().size() == 0) {
                ((ViewGroup) addobsButton.getParent()).removeView(addobsButton);
            }

            // Plant detail unwrapped using Parcel Library
            needleDetails needlePlant = Parcels.unwrap(previousIntent.getParcelableExtra("plantInfo"));

            String plant_code = changeIfNA(needlePlant.getPlant_code());
            String species_name = changeIfNA(needlePlant.getSpecies_name());
            String common_name = changeIfNA(needlePlant.getCommon_name());
            String synonyms = changeIfNA(needlePlant.getSynonyms());
            String family_name = changeIfNA(needlePlant.getFamily_name());
            String notes = changeIfNA(needlePlant.getNotes());
            String photo_credit = changeIfNA(needlePlant.getPhoto_credit());

            String cone = changeIfNA(needlePlant.getCone());
            String needle_apex = changeIfNA(needlePlant.getNeedle_apex());
            String needle_arrangement = changeIfNA(needlePlant.getNeedle_arrangement());
            String needle_per_fascile = changeIfNA(needlePlant.getNeedle_per_fascile());
            String leaf_type = changeIfNA(needlePlant.getLeaf_type());
            plantCode = plant_code;
            plantName = species_name;

            // Find all text views and the image view.
            ImageView plantPicture = (ImageView) findViewById(R.id.PlantImage);

            TextView SpeciesName = (TextView) findViewById(R.id.SpeciesName);
            TextView CommonName = (TextView) findViewById(R.id.CommonName);
            TextView Synonyms = (TextView) findViewById(R.id.Synonyms);
            TextView FamilyName = (TextView) findViewById(R.id.FamilyName);
            TextView Notes = (TextView) findViewById(R.id.Notes);
            TextView PhotoCredit = (TextView) findViewById(R.id.PhotoCredit);

            TextView Cone = (TextView) findViewById(R.id.Cone);
            TextView NeedleApex = (TextView) findViewById(R.id.NeedleApex);
            TextView NeedleArrangement = (TextView) findViewById(R.id.NeedleArrangement);
            TextView NeedlePerFascile = (TextView) findViewById(R.id.NeedlePerFascile);
            TextView LeafType = (TextView) findViewById(R.id.LeafType);

            // Set information and picture.
            // Using Glide library for image loading.
            String pictureFile = plant_code + ".jpg";
            Glide.with(this).load(Uri.parse("file:///android_asset/plantphotos/woody/" + pictureFile)).into(plantPicture);

            SpeciesName.setText(species_name);
            CommonName.setText("Common Name: " + common_name);
            Synonyms.setText("Synonyms: " + synonyms);
            FamilyName.setText("Family: " + family_name);
            Cone.setText("Cone: " + cone);
            NeedleApex.setText("Needle Apex: " + needle_apex);
            NeedleArrangement.setText("Needle Arrangement: " + needle_arrangement);
            NeedlePerFascile.setText("Needles per Fascicle: " + needle_per_fascile);
            LeafType.setText("Leaf Type: " + leaf_type);
            Notes.setText("Notes: " + notes);
            PhotoCredit.setText("Photo Credit: " + photo_credit);

        }

        else{ setContentView(R.layout.activity_error_detail); }

    }

    // Want to change any categories to None if they are entered in DB as NA
    private String changeIfNA(String testString){
        if (testString == null){
            System.out.println("Entry text is null");
            return testString;
        }

        if (testString.equalsIgnoreCase("NA")) {

            testString = "none";
        }

        return testString;
    }

    public void gotoAddObs(){
        Intent intent = new Intent(plantDetailActivity.this, AddObsActivity.class);
        intent.putExtra("PlantName", plantName);
        intent.putExtra("PlantCode", plantCode);
        startActivity(intent);
    }
}
