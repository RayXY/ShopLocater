package com.placelocator.common;

import com.placelocator.model.PlaceGeoCode;

/**
 * Created by Ray on 25/05/2016.
 */
public interface DistanceCalculator {
    /**
     * Calculate the abstract distance between two geo code
     * @param geoCode1
     * @param geoCode2
     * @return A double number to represent the distance
     */
    double calculateDistance(PlaceGeoCode geoCode1, PlaceGeoCode geoCode2);
}
