package com.example.zach.musicians.models;

import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class User {
    String userName;
    String userLocation;
    ArrayList<String> userInstruments;
    ArrayList<String> userGenres;
    String userBio;
    Boolean collaborator;
    Boolean lookingForGigs;

    public User() {}

    public User(String userName, String userLocation, ArrayList<String> userInstruments, ArrayList<String> userGenres, String userBio, Boolean collaborator, Boolean lookingForGigs) {
        this.userName = userName;
        this.userLocation = userLocation;
        this.userInstruments = userInstruments;
        this.userGenres = userGenres;
        this.userBio = userBio;
        this.collaborator = collaborator;
        this.lookingForGigs = lookingForGigs;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(String userLocation) {
        this.userLocation = userLocation;
    }

    public ArrayList<String> getUserInstruments() {
        return userInstruments;
    }

    public void setUserInstruments(ArrayList<String> userInstruments) {
        this.userInstruments = userInstruments;
    }

    public ArrayList<String> getUserGenres() {
        return userGenres;
    }

    public void setUserGenres(ArrayList<String> userGenres) {
        this.userGenres = userGenres;
    }

    public String getUserBio() {
        return userBio;
    }

    public void setUserBio(String userBio) {
        this.userBio = userBio;
    }

    public Boolean getCollaborator() {
        return collaborator;
    }

    public void setCollaborator(Boolean collaborator) {
        this.collaborator = collaborator;
    }

    public Boolean getLookingForGigs() {
        return lookingForGigs;
    }

    public void setLookingForGigs(Boolean lookingForGigs) {
        this.lookingForGigs = lookingForGigs;
    }
}
