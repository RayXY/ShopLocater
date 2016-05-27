package com.placelocater.model;

/**
 * Created by Ray on 25/05/2016.
 */
public class Place {
    private final PlaceIdentity placeIdentity;
    private final PlaceGeoCode placeGeoCode;

    public Place(PlaceIdentity placeIdentity, PlaceGeoCode placeGeoCode) {
        this.placeIdentity = placeIdentity;
        this.placeGeoCode = placeGeoCode;
    }

    public PlaceIdentity getPlaceIdentity() {
        return placeIdentity;
    }

    public PlaceGeoCode getPlaceGeoCode() {
        return placeGeoCode;
    }

}
