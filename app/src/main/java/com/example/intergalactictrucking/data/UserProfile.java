package com.example.intergalactictrucking.data;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class UserProfile {

    private String userUID;
    private String userFullName;
    private String userCity;
    private String userPhoneNumber;
    private Boolean userPrivatePerson;
    private String userCompanyName;

    public String getUserUID() {
        return userUID;
    }

    public void setUserUID(String userUID) {
        this.userUID = userUID;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public Boolean getUserPrivatePerson() {
        return userPrivatePerson;
    }

    public void setUserPrivatePerson(Boolean userPrivatePerson) {
        this.userPrivatePerson = userPrivatePerson;
    }

    public String getUserCompanyName() {
        return userCompanyName;
    }

    public void setUserCompanyName(String userCompanyName) {
        this.userCompanyName = userCompanyName;
    }
}
