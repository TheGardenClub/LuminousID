package com.luminousid.luminousid;

import org.parceler.Parcel;

/**
 * Created by chase on 4/26/2017.
 * A class to store user information data so we don't need to talk to Firebase each time.
 */

@Parcel(Parcel.Serialization.BEAN)
public class accountDetails {

    private String UID = null;
    private String email = null;
    private int researcher = 0;
    private String username = null;

    public accountDetails(){

    }

    public accountDetails(String email, int researcher, String username){

        this.email = email;
        this.researcher = researcher;
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getResearcher() {
        return researcher;
    }

    public void setResearcher(int researcher) {
        this.researcher = researcher;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
