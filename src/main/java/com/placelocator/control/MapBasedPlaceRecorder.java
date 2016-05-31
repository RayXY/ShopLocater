package com.placelocator.control;

import com.placelocator.model.PlaceIdentity;
import com.placelocator.model.Place;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ray on 25/05/2016.
 */
@Service(value="MapBased")
public class MapBasedPlaceRecorder implements PlaceRecorder {
    private Map<PlaceIdentity, Place> recordMap;

    public MapBasedPlaceRecorder() {
        this.recordMap = new HashMap<>();
    }

    @Override
    public void addPlace(Place place) {
        recordMap.put(place.getPlaceIdentity(), place);
    }

    @Override
    public boolean isPlaceExist(PlaceIdentity placeIdentity) {
        return recordMap.containsKey(placeIdentity);
    }

    @Override
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
