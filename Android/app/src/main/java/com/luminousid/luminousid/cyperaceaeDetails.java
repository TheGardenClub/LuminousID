package com.luminousid.luminousid;

import org.parceler.Parcel;

/**
 * Created by chase on 3/18/2017.
 * A class to help with the Firebase UI library to populate a listview.
 * Allows data to be stored in the way needed for the Field Guide listviews.
 * Gets data for the cyperceae family of graminoids.
 * Uses the Parcel library allow this class to be wrapped and passed on to a detail activity.
 */

@Parcel(Parcel.Serialization.BEAN)
public class cyperaceaeDetails {

    private String species_name = null;
    private String common_name = null;
    private int plant_thumbnail = 0;
    private String family_name = null;
    private String growth_form = null;
    private String inflorescence = null;
    private String leaf_blade = null;
    private String spike_color = null;
    private String stem_cross_section = null;
    private String habitat = null;
    private String notes = null;
    private String photo_credit = null;
    private String plant_code = null;
    private String synonyms = null;

    public cyperaceaeDetails(){

    }

    public cyperaceaeDetails(String species_name, String common_name, int plant_thumbnail, String family_name,
                             String growth_form, String inflorescence, String leaf_blade,
                             String spike_color, String stem_cross_section, String habitat, String notes,
                             String photo_credit, String plant_code, String synonyms){

        this.species_name = species_name;
        this.common_name = common_name;
        this.plant_thumbnail = plant_thumbnail;
        this.family_name = family_name;
        this.growth_form = growth_form;
        this.inflorescence = inflorescence;
        this.leaf_blade = leaf_blade;
        this.spike_color = spike_color;
        this.stem_cross_section = stem_cross_section;
        this.habitat = habitat;
        this.notes = notes;
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

    public int getPlant_thumbnail() { return plant_thumbnail; }

    public void setPlant_thumbnail(int newPlant_thumbnail) { this.plant_thumbnail = newPlant_thumbnail; }

    public String getFamily_name() { return family_name; }

    public void setFamily_name(String family_name) { this.family_name = family_name; }

    public String getInflorescence() { return inflorescence; }

    public void setInflorescence(String inflorescence) { this.inflorescence = inflorescence; }

    public String getLeaf_blade() { return leaf_blade; }

    public void setLeaf_blade(String leaf_blade) { this.leaf_blade = leaf_blade; }

    public String getSpike_color() { return spike_color; }

    public void setSpike_color(String spike_color) { this.spike_color = spike_color; }

    public String getStem_cross_section() { return stem_cross_section; }

    public void setStem_cross_section(String stem_cross_section) { this.stem_cross_section = stem_cross_section; }

    public String getGrowth_form() { return growth_form; }

    public void setGrowth_form(String growth_form) { this.growth_form = growth_form; }

    public String getHabitat() { return habitat; }

    public void setHabitat(String habitat) { this.habitat = habitat; }

    public String getNotes() { return notes; }

    public void setNotes(String notes) { this.notes = notes; }

    public String getPhoto_credit() { return photo_credit; }

    public void setPhoto_credit(String photo_credit) { this.photo_credit = photo_credit; }

    public String getPlant_code() { return plant_code; }

    public void setPlant_code(String plant_code) { this.plant_code = plant_code; }

    public String getSynonyms() { return synonyms; }

    public void setSynonyms(String synonyms) { this.synonyms = synonyms; }
}
