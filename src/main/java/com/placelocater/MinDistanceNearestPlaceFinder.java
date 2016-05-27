package com.placelocater;

import com.placelocater.model.Place;
import com.placelocater.model.PlaceGeoCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by Ray on 25/05/2016.
 */
@Service(value="MinDistance")
public class MinDistanceNearestPlaceFinder implements NearestPlaceFinder {

    @Autowired
    @Qualifier("DirectLine")
    private DistanceCalculator distanceCalculator;
    
    @Override
    public Place findNearestPlace(Collection<Place> places, PlaceGeoCode placeGeoCode) {
        double minDistance = Double.MAX_VALUE;
        double currentDistance;
        Place nearestPlace = null;
        for (Place place : places) {
            currentDistance = distanceCalculator.calculateDistance(place.getPlaceGeoCode(), placeGeoCode);
            if (currentDistance < minDistance) {
                nearestPlace = place;
                minDistance = currentDistance;
            }
        }

        return nearestPlace;
    }

}
