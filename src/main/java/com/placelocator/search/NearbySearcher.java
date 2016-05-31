package com.placelocator.search;

import com.placelocator.model.Place;
import com.placelocator.model.PlaceGeoCode;

import java.util.List;

/**
 * Created by Ray on 25/05/2016.
 * This is to common
 */
public interface NearbySearcher {

    /**
     * Given a centre point, find all nearby places with curtain type
     * @param centrePoint
     * @param placeType
     * @return a list of all nearby places ordered by the distance to the centre point
     */
    List<Place> searchNearby(PlaceGeoCode centrePoint, String placeType);

    /**
     * Given a centre point, find all nearby places
     * @param centrePoint
     * @return a list of all nearby places ordered by the distance to the centre point
     */
    List<Place> searchNearby(PlaceGeoCode centrePoint);

}
