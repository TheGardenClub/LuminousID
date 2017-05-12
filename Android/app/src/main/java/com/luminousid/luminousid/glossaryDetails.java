package com.luminousid.luminousid;

import org.parceler.Parcel;

/**
 * Created by chase on 3/18/2017.
 * Allows data to be stored in the way needed for the Field Guide listviews.
 * Gets data for the glossary entries.
 * Uses the Parcel library allow this class to be wrapped and passed on to a detail activity.
 */

@Parcel(Parcel.Serialization.BEAN)
public class glossaryDetails {

    private String glossary_name = null;
    private String glossary_imagename = null;

    public glossaryDetails(){

    }

    public glossaryDetails(String glossary_name, String glossary_imagename){

        this.glossary_name = glossary_name;
        this.glossary_imagename = glossary_imagename;
    }

    public String getGlossary_name(){
        return glossary_name;
    }

    public void setGlossary_name(String newglossary_name) { this.glossary_name = newglossary_name; }

    public String getGlossary_imagename(){
        return glossary_imagename;
    }

    public void setGlossary_imagename(String newglossary_imagename) { this.glossary_imagename = newglossary_imagename; }

}
