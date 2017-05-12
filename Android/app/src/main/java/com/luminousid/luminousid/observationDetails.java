package com.luminousid.luminousid;

import org.parceler.Parcel;

/**
 * Created by chase on 4/25//2017.
 * A class to read in data from the observations table of the Firebase database
 */

@Parcel(Parcel.Serialization.BEAN)
public class observationDetails {

    private String key = null;
    private String comments = null;
    private String datetime = null;
    private double gps_lat = 0.0;
    private double gps_long = 0.0;
    private float gps_accuracy;
    private boolean is_synced = false;
    private int is_verified = 0;
    private String plant_code = null;
    private String species_name = null;
    private String username = null;
    private String imagePath = null;

    public observationDetails(){

    }

    public observationDetails(String key, String comments, String datetime, double gps_lat, double gps_long, float gps_accuracy,
                              boolean is_synced, int is_verified, String plant_code, String species_name,
                              String username, String imagePath){

        this.key = key;
        this.comments = comments;
        this.datetime = datetime;
        this.gps_lat = gps_lat;
        this.gps_long = gps_long;
        this.gps_accuracy = gps_accuracy;
        this.is_synced = is_synced;
        this.is_verified = is_verified;
        this.plant_code = plant_code;
        this.species_name = species_name;
        this.username = username;
        this.imagePath = imagePath;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getComments() { return comments; }

    public void setComments(String comments) { this.comments = comments; }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public double getGps_lat() {
        return gps_lat;
    }

    public void setGps_lat(double gps_lat) {
        this.gps_lat = gps_lat;
    }

    public double getGps_long() {
        return gps_long;
    }

    public void setGps_long(double gps_long) {
        this.gps_long = gps_long;
    }

    public float getGps_accuracy() { return gps_accuracy; }

    public void setGps_accuracy(float gps_accuracy) { this.gps_accuracy = gps_accuracy; }

    public boolean getIs_synced() {
        return is_synced;
    }

    public void setIs_synced(boolean is_synced) {
        this.is_synced = is_synced;
    }

    public int getIs_verified() {
        return is_verified;
    }

    public void setIs_verified(int is_verified) {
        this.is_verified = is_verified;
    }

    public String getPlant_code() {
        return plant_code;
    }

    public void setPlant_code(String plant_code) {
        this.plant_code = plant_code;
    }

    public String getSpecies_name() {
        return species_name;
    }

    public void setSpecies_name(String species_name) {
        this.species_name = species_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImagePath() { return imagePath; }

    public void setImagePath(String imagePath) { this.imagePath = imagePath; }

}
