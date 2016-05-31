package com.placelocator.control;

import com.placelocator.PlaceGeoCodeNotFoundException;
import com.placelocator.RemoteCallException;
import com.placelocator.model.Place;
import com.placelocator.model.PlaceGeoCode;
import com.placelocator.model.PlaceIdentity;
import com.placelocator.search.PlaceGeoCodeFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by Ray on 31/05/2016.
 */
@Service
public class DefaultUserPlacesManager implements UserPlacesManager {

    @Autowired
    private PlaceGeoCodeFinder placeGeoCodeFinder;

    @Autowired
    private PlaceRecorder placeRecorder;

    @Autowired
    private RemotePlaceController remotePlaceController;

    @Override
    public void addPlace(PlaceIdentity placeIdentity) throws PlaceGeoCodeNotFoundException, RemoteCallException {
        PlaceGeoCode placeGeoCode = placeGeoCodeFinder.findPlaceGeoCode(placeIdentity);
        Place placeToAdd = new Place(placeIdentity, placeGeoCode);
        remotePlaceController.addPlaceRemote(placeToAdd);
        placeRecorder.addPlace(placeToAdd);
    }

    @Override
    public Collection<Place> getAllPlaces() {
        return placeRecorder.getAllPlaces();
    }

    @Override
    public void deletePlace(PlaceIdentity placeIdentity) throws RemoteCallException {
        if (placeRecorder.isPlaceExist(placeIdentity)) {
            remotePlaceController.removePlaceRemote(placeIdentity);
            placeRecorder.deletePlace(placeIdentity);
        }
    }

    @Override
    public Place getPlace(PlaceIdentity placeIdentity) {
        return placeRecorder.getPlace(placeIdentity);
    }
}
