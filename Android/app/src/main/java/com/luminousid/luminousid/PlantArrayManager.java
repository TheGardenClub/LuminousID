package com.luminousid.luminousid;

import android.app.Application;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by chase on 4/18/2017.
 * A class to hold all of our global arrays.
 *      Arrays: Field Guide, Glossary, User Info, User Observations
 */

public class PlantArrayManager {

    private static PlantArrayManager instance;

    public ArrayList<forbsDetails> globalForbsArray = new ArrayList<>();
    public ArrayList<cyperaceaeDetails> globalCyperArray = new ArrayList<>();
    public ArrayList<juncaceaeDetails> globalJuncaArray = new ArrayList<>();
    public ArrayList<poaceaeDetails> globalPoaArray = new ArrayList<>();
    public ArrayList<needleDetails> globalNeedleArray = new ArrayList<>();
    public ArrayList<deciduousDetails> globalDeciArray = new ArrayList<>();

    public ArrayList<glossaryDetails> globalGlossaryForbsArray = new ArrayList<>();
    public ArrayList<glossaryDetails> globalGlossaryGraminoidsArray = new ArrayList<>();

    public ArrayList<observationDetails> globalObservationArray = new ArrayList<>();

    public ArrayList<accountDetails> globalAccountDetails = new ArrayList<>();

    public static void initInstance() {
        if(instance == null){
            instance = new PlantArrayManager();
        }
    }

    public static PlantArrayManager getInstance(){
        PlantArrayManager.initInstance();
        return instance;
    }

    private PlantArrayManager() {
        // Hidden because it's a singleton
    }

    public ArrayList<forbsDetails> getglobalForbsArray() {
        return globalForbsArray;
    }

    public void setglobalForbsArray(ArrayList<forbsDetails> newForbArray) {
        globalForbsArray = newForbArray;
    }

    public ArrayList<cyperaceaeDetails> getGlobalCyperArray() {
        return globalCyperArray;
    }

    public void setGlobalCyperArray(ArrayList<cyperaceaeDetails> newCyperArray) {
        globalCyperArray = newCyperArray;
    }

    public ArrayList<juncaceaeDetails> getGlobalJuncaArray() {
        return globalJuncaArray;
    }

    public void setGlobalJuncaArray(ArrayList<juncaceaeDetails> newJuncaArray) {
        globalJuncaArray = newJuncaArray;
    }

    public ArrayList<poaceaeDetails> getGlobalPoaArray() {
        return globalPoaArray;
    }

    public void setGlobalPoaArray(ArrayList<poaceaeDetails> newPoaArray) {
        globalPoaArray = newPoaArray;
    }

    public ArrayList<needleDetails> getGlobalNeedleArray() {
        return globalNeedleArray;
    }

    public void setGlobalNeedleArray(ArrayList<needleDetails> newNeedleArray) {
        globalNeedleArray = newNeedleArray;
    }

    public ArrayList<deciduousDetails> getGlobalDeciArray() {
        return globalDeciArray;
    }

    public void setGlobalDeciArray(ArrayList<deciduousDetails> newDeciArray) {
        globalDeciArray = newDeciArray;
    }

    public ArrayList<glossaryDetails> getGlobalGlossaryForbsArray() {
        return globalGlossaryForbsArray;
    }

    public void setGlobalGlossaryForbsArray(ArrayList<glossaryDetails> newGlossaryForbArray) {
        globalGlossaryForbsArray = newGlossaryForbArray;
    }

    public ArrayList<glossaryDetails> getGlobalGlossaryGraminoidsArray() {
        return globalGlossaryGraminoidsArray;
    }

    public void setGlobalGlossaryGraminoidsArray(ArrayList<glossaryDetails> newGlossaryGraminoidsArray) {
        globalGlossaryGraminoidsArray = newGlossaryGraminoidsArray;
    }

    public ArrayList<observationDetails> getGlobalObservationArray() {
        return globalObservationArray;
    }

    public void setGlobalObservationArray(ArrayList<observationDetails> newObservationArray) {
        globalObservationArray = newObservationArray;
    }

    public ArrayList<accountDetails> getGlobalAccountDetails() {
        return globalAccountDetails;
    }

    public void setGlobalAccountDetails(ArrayList<accountDetails> newAccountDetails) {
        globalAccountDetails = newAccountDetails;
    }

}
