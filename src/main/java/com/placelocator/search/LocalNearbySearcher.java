package com.placelocator.search;

import com.placelocator.control.PlaceRecorder;
import com.placelocator.model.Place;
import com.placelocator.model.PlaceGeoCode;
import com.placelocator.common.DistanceCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Ray on 25/05/2016.
 */
@Service(value="LocalNearbySearcher")
public class LocalNearbySearcher implements NearbySearcher {

    @Autowired
    private DistanceCalculator distanceCalculator;

    @Autowired
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
