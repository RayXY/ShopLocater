package com.placelocator.control;

import com.placelocator.PlaceGeoCodeNotFoundException;
import com.placelocator.RemoteCallException;
import com.placelocator.model.Place;
import com.placelocator.model.PlaceIdentity;

import java.util.Collection;

/**
 * Created by Ray on 31/05/2016.
 */
public interface UserPlacesManager {

    /**
     * Search place geo code based on place identity and then add place only if geo code can be found
     * @param placeIdentity
     * @throws PlaceGeoCodeNotFoundException
     */
    void addPlace(PlaceIdentity placeIdentity) throws PlaceGeoCodeNotFoundException, RemoteCallException;

    /**
     * Get all the user defined places
     * @return a collection of places
     */
    Collection<Place> getAllPlaces();

    /**
     * Remove a user defined place from its container
     * @param placeIdentity
     */
    void deletePlace(PlaceIdentity placeIdentity) throws RemoteCallException;

    /**
     * Get place details of a place
     * @param placeIdentity
     * @return the place details
     */
    Place getPlace(PlaceIdentity placeIdentity);
}
