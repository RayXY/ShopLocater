package com.placelocater;

import com.placelocater.model.PlaceGeoCode;

import java.util.Collection;

/**
 * Created by Ray on 27/05/2016.
 */
public interface CentroidCalculator {

    PlaceGeoCode findCentroid(Collection<PlaceGeoCode> places);
}
