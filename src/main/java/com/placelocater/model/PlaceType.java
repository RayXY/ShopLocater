package com.placelocater.model;

/**
 * Created by Ray on 27/05/2016.
 */
public enum PlaceType {

    Restaurant("restaurant"),
    Shop("store"),
    Cafe("cafe"),
    Cinema("movie_theater"),
    Undefined("undefined");

    private String googlePlaceType;

    PlaceType(String googlePlaceType) {
        this.googlePlaceType = googlePlaceType;
    }

    public String getGooglePlaceType() {
        return googlePlaceType;
    }
}
