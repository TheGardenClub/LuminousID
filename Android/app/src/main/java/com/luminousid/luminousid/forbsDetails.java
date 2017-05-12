package com.luminousid.luminousid;

import android.content.Intent;
import android.view.View;

import org.parceler.Parcel;

/**
 * Created by chase on 3/18/2017.
 * A class to help with the Firebase UI library to populate a listview.
 * Allows data to be stored in the way needed for the Field Guide listviews.
 * Gets data for the forbs.
 * Uses the Parcel library allow this class to be wrapped and passed on to a detail activity.
 */

@Parcel(Parcel.Serialization.BEAN)
public class forbsDetails {

    private String species_name = null;
    private String common_name = null;
    private String family_name = null;
    private String flower_color = null;
    private String flower_shape = null;
    private String growth_form = null;
    private String habitat = null;
    private String leaf_arrangement = null;
    private String leaf_shape_filter = null;
    private String notes = null;
    private String petal_number = null;
    private String photo_credit = null;
    private String plant_code = null;
    private String synonyms = null;

    public forbsDetails(){

    }

    public forbsDetails(String species_name, String common_name, String family_name,
                        String flower_color, String flower_shape, String growth_form, String habitat,
                        String leaf_arrangement, String leaf_shape_filter, String notes,
                        String petal_number, String photo_credit, String plant_code, String synonyms){

        this.species_name = species_name;
        this.common_name = common_name;
        this.family_name = family_name;
        this.flower_color = flower_color;
        this.flower_shape = flower_shape;
        this.growth_form = growth_form;
        this.habitat = habitat;
        this.leaf_arrangement = leaf_arrangement;
        this.leaf_shape_filter = leaf_shape_filter;
        this.notes = notes;
        this.petal_number = petal_number;
        this.photo_credit = photo_credit;
        this.plant_code = plant_code;
        this.synonyms = synonyms;
    }

    public String getSpecies_name(){
        return species_name;
    }

    public void setSpecies_name(String newspecies_name) { this.species_name = newspecies_name; }

    public String getCommon_name(){
        return common_name;
    }

    public void setCommon_name(String newCommon_name) { this.common_name = newCommon_name; }

    public String getFamily_name() { return family_name; }

    public void setFamily_name(String family_name) { this.family_name = family_name; }

    public String getFlower_color() { return flower_color; }

    public void setFlower_color(String flower_color) { this.flower_color = flower_color; }

    public String getFlower_shape() { return flower_shape; }

    public void setFlower_shape(String flower_shape) { this.flower_shape = flower_shape; }

    public String getGrowth_form() { return growth_form; }

    public void setGrowth_form(String growth_form) { this.growth_form = growth_form; }

    public String getHabitat() { return habitat; }

    public void setHabitat(String habitat) { this.habitat = habitat; }

    public String getLeaf_arrangement() { return leaf_arrangement; }

    public void setLeaf_arrangement(String leaf_arrangement) { this.leaf_arrangement = leaf_arrangement; }

    public String getLeaf_shape_filter() { return leaf_shape_filter; }

    public void setLeaf_shape_filter(String leaf_shape_filter) { this.leaf_shape_filter = leaf_shape_filter; }

    public String getNotes() { return notes; }

    public void setNotes(String notes) { this.notes = notes; }

    public String getPetal_number() { return petal_number; }

    public void setPetal_number(String petal_number) { this.petal_number = petal_number; }

    public String getPhoto_credit() { return photo_credit; }

    public void setPhoto_credit(String photo_credit) { this.photo_credit = photo_credit; }

    public String getPlant_code() { return plant_code; }

    public void setPlant_code(String plant_code) { this.plant_code = plant_code; }

    public String getSynonyms() { return synonyms; }

    public void setSynonyms(String synonyms) { this.synonyms = synonyms; }

}
