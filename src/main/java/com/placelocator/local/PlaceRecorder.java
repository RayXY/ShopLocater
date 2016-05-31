package com.placelocator.local;

import com.placelocator.PlaceGeoCodeNotFoundException;
import com.placelocator.model.Place;
import com.placelocator.model.PlaceIdentity;

import java.util.Collection;

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
     * Check if place has already been added
     * @param placeIdentity
     * @return true if place already exists
     */
    boolean isPlaceExist(PlaceIdentity placeIdentity);

    /**
     * Get all the user defined places
     * @return a collection of places
     */
    Collection<Place> getAllPlaces();

    /**
     * Remove a user defined place from its container
     * @param placeIdentity
     */
    void deletePlace(PlaceIdentity placeIdentity);

    /**
     * Get place details of a place
     * @param placeIdentity
     * @return the place details
     */
    Place getPlace(PlaceIdentity placeIdentity);
}
