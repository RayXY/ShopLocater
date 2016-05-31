package com.placelocator.search;

import com.placelocator.PlaceGeoCodeNotFoundException;
import com.placelocator.PlaceTypeNotDefinedException;
import com.placelocator.model.Place;
import com.placelocator.model.PlaceGeoCode;

import java.util.Collection;

/**
 * Created by Ray on 27/05/2016.
 */
public interface PlaceExporer {

    /**
     * Find a place which is convenient for all post code location to visit
     * @param placeType
     * @param postCodes
     * @return
     * @throws PlaceGeoCodeNotFoundException
     */
    Place findMeetingPoint(String placeType, Collection<String> postCodes) throws PlaceGeoCodeNotFoundException;

    /**
     * Find the nearest place based on the given geo code and place type
     * @param placeGeoCode
     * @return the nearest place, null if none found
     */
    Place findNearestPlace(PlaceGeoCode placeGeoCode, String type) throws PlaceTypeNotDefinedException;

    /**
     * Find the nearest place based on the given geo code
     * @param placeGeoCode
     * @return the nearest place, null if none found
     */
    Place findNearestPlace(PlaceGeoCode placeGeoCode);
}
