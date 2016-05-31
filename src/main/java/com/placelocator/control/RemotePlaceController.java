package com.placelocator.control;

import com.placelocator.RemoteCallException;
import com.placelocator.model.Place;
import com.placelocator.model.PlaceIdentity;

/**
 * Created by Ray on 31/05/2016.
 */
public interface RemotePlaceController {

    /**
     * Add a place definition to a search storage and return its unique search identity
     * @param place
     * @return the search identity if successfully added
     */
    void addPlaceRemote(Place place) throws RemoteCallException;

    /**
     * Remove a place from the search storage and return status
     * @param placeIdentity
     * @return the status of the removal result
     */
    void removePlaceRemote(PlaceIdentity placeIdentity) throws RemoteCallException;
}
