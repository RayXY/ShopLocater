package com.placelocater;

import com.placelocater.model.PlaceGeoCode;
import com.placelocater.model.PlaceIdentity;

/**
 * Created by Ray on 25/05/2016.
 */
public interface PlaceGeoCodeFinder {
    /**
     * Search and find the geo code assigned to a place based on its identity
     * @param placeIdentity
     * @return PlaceGeoCode if found
     * @throws PlaceGeoCodeNotFoundException
     */
    PlaceGeoCode findPlaceGeoCode(PlaceIdentity placeIdentity) throws PlaceGeoCodeNotFoundException;

    /**
     * Search and find the geo code assigned to a place based on a given postcode
     * @param postCode
     * @return PlaceGeoCode if found
     * @throws PlaceGeoCodeNotFoundException
     */
    PlaceGeoCode findPlaceGeoCode(String postCode) throws PlaceGeoCodeNotFoundException;
}
