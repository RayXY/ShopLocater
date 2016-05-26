package com.shoplocater;

import com.shoplocater.model.ShopGeoCode;

/**
 * Created by Ray on 25/05/2016.
 */
public interface DistanceCalculator {

    double calculateDistance(ShopGeoCode geoCode1, ShopGeoCode geoCode2);
}
