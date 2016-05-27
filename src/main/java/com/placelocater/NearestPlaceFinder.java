package com.placelocater;

import com.placelocater.model.Place;
import com.placelocater.model.PlaceGeoCode;

import java.util.Collection;

/**
 * Created by Ray on 25/05/2016.
 */
public interface NearestPlaceFinder {
    /**
     * Given a collection of places, calculate which place is nearest to a given geo code;
     * only return one if multiple results found
     * @param places
     * @param placeGeoCode
     * @return A place represent the nearest or one of the nearest places
     */
    Place findNearestPlace(Collection<Place> places, PlaceGeoCode placeGeoCode);
}
