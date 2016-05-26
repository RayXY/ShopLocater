package com.shoplocater;

import com.shoplocater.model.ShopGeoCode;

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
    double calculateDistance(ShopGeoCode geoCode1, ShopGeoCode geoCode2);
}
