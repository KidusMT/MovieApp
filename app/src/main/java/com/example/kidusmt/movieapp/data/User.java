package com.example.kidusmt.movieapp.data;

import java.util.HashMap;

/**
 * Created by KidusMT on 12/22/2017.
 */

//has to be changed to a retrofit POJO class when they are connected with network
public class User {

    private String fullName;
    private String photo;
    private String email;
    private HashMap<String,Object> timestampJoined;

    public User(String mFullName, String mPhoneNo, String mEmail, HashMap<String, Object> timestampJoined) {
        this.fullName = mFullName;
        this.photo = mPhoneNo;
        this.email = mEmail;
        this.timestampJoined = timestampJoined;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public HashMap<String, Object> getTimestampJoined() {
        return timestampJoined;
    }

    public void setTimestampJoined(HashMap<String, Object> timestampJoined) {
        this.timestampJoined = timestampJoined;
    }
}
