package com.placelocater;

import com.placelocater.model.Place;
import com.placelocater.model.PlaceGeoCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Ray on 25/05/2016.
 */
@Service(value="Local")
public class LocalNearbySearcher implements NearbySearcher {

    @Autowired
    @Qualifier("DirectLine")
    private DistanceCalculator distanceCalculator;

    @Autowired
    @Qualifier("MapBased")
    private PlaceRecorder placeRecorder;
    
    @Override
    public List<Place> searchNearby(PlaceGeoCode centrePoint, String placeType) {
        SortedMap<Double, Place> nearbyPlaces = new TreeMap<>();
        double currentDistance;
        for (Place place : placeRecorder.getAllPlaces()) {
            if (placeType == null ||
                    place.getPlaceIdentity().getPlaceType().equals(placeType)) {
                currentDistance = distanceCalculator.calculateDistance(place.getPlaceGeoCode(), centrePoint);
                nearbyPlaces.put(currentDistance, place);
            }
        }

        return new ArrayList<>(nearbyPlaces.values());
    }

    @Override
    public List<Place> searchNearby(PlaceGeoCode centrePoint) {
        return searchNearby(centrePoint, null);
    }
}
