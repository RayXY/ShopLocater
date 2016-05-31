package com.placelocater;

import com.placelocater.model.PlaceGeoCode;
import com.placelocater.model.PlaceIdentity;
import com.placelocater.model.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ray on 25/05/2016.
 */
@Service(value="MapBased")
public class MapBasedPlaceRecorder implements PlaceRecorder {

    @Autowired
    @Qualifier("Google")
    private PlaceGeoCodeFinder placeGeoCodeFinder;
    @Autowired
    @Qualifier("MinDistance")
    private NearbySearcher nearbySearcher;
    @Autowired
    @Qualifier("Plain")
    private CentroidCalculator centroidCalculator;

    private Map<PlaceIdentity, Place> recordMap;

    public MapBasedPlaceRecorder() {
        this.recordMap = new HashMap<>();
    }

    @Override
    public void addPlace(PlaceIdentity placeIdentity) throws PlaceGeoCodeNotFoundException {
        PlaceGeoCode placeGeoCode = placeGeoCodeFinder.findPlaceGeoCode(placeIdentity);
        recordMap.put(placeIdentity, new Place(placeIdentity, placeGeoCode));
    }

    @Override
    public boolean isPlaceExist(PlaceIdentity placeIdentity) {
        return recordMap.containsKey(placeIdentity);
    }

    public Place getPlace(PlaceIdentity placeIdentity) {
        return recordMap.get(placeIdentity);
    }

    @Override
    public Collection<Place> getAllPlaces() {
        return recordMap.values();
    }

    @Override
    public void deletePlace(PlaceIdentity placeIdentity) {
        recordMap.remove(placeIdentity);
    }
}
