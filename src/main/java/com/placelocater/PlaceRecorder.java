package com.placelocater;

import com.placelocater.model.Place;
import com.placelocater.model.PlaceGeoCode;
import com.placelocater.model.PlaceIdentity;

/**
 * Created by Ray on 25/05/2016.
 */
public interface PlaceRecorder {

    /**
     * Search place geo code based on place identity and then add place only if geo code can be found
     * @param placeIdentity
     * @throws PlaceGeoCodeNotFoundException
     */
    void addPlace(PlaceIdentity placeIdentity) throws PlaceGeoCodeNotFoundException;

    /**
     * Find the nearest place based on the given geo code
     * @param placeGeoCode
     * @return the nearest place, null if none found
     */
    Place findNearestPlace(PlaceGeoCode placeGeoCode);

    /**
     * Check if place has already been added
     * @param placeIdentity
     * @return true if place already exists
     */
    boolean isPlaceExist(PlaceIdentity placeIdentity);
}
