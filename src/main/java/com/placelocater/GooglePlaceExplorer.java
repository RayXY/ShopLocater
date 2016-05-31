package com.placelocater;

import com.placelocater.model.Place;
import com.placelocater.model.PlaceGeoCode;
import com.placelocater.model.PlaceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Ray on 27/05/2016.
 */
public class GooglePlaceExplorer implements PlaceExporer {

    @Autowired
    @Qualifier("Plain")
    private CentroidCalculator centroidCalculator;

    @Autowired
    @Qualifier("Google")
    private NearbySearcher nearbySearcher;

    @Autowired
    @Qualifier("Google")
    private PlaceGeoCodeFinder placeGeoCodeFinder;

    @Override
    public Place findMeetingPoint(String placeType, Collection<String> postCodes) throws PlaceGeoCodeNotFoundException{
        PlaceGeoCode centralPlace = centroidCalculator.findCentroid(fetchGeoCodesFromPlaces(postCodes));
        // Returned list should be ordered by the distance to the center so return first
        List<Place> result = nearbySearcher.searchNearby(centralPlace, placeType);
        if (result.isEmpty())
            return null;
        else
            return result.get(0);
    }

    private Collection<PlaceGeoCode> fetchGeoCodesFromPlaces(Collection<String> postCodes) throws PlaceGeoCodeNotFoundException {
        Collection<PlaceGeoCode> geoCodes = new ArrayList<>();
        for (String postCode : postCodes) {
            geoCodes.add(placeGeoCodeFinder.findPlaceGeoCode(postCode));
        }
        return geoCodes;
    }

    @Override
    public Place findNearestPlace(PlaceGeoCode placeGeoCode, String type) throws PlaceTypeNotDefinedException {
        List<Place> nearbyPlaces;
        if (type != null) {
            try {
                String placeType = PlaceType.valueOf(type).name();
                nearbyPlaces = nearbySearcher.searchNearby(placeGeoCode, placeType);
            } catch (IllegalArgumentException e) {
                throw new PlaceTypeNotDefinedException();
            }
        }
        else {
            nearbyPlaces = nearbySearcher.searchNearby(placeGeoCode);
        }
        if (nearbyPlaces.isEmpty())
            return null;
        else
            return nearbyPlaces.get(0);
    }

    @Override
    public Place findNearestPlace(PlaceGeoCode placeGeoCode) {
        List<Place> nearbyPlaces = nearbySearcher.searchNearby(placeGeoCode);;
        if (nearbyPlaces.isEmpty())
            return null;
        else
            return nearbyPlaces.get(0);
    }
}
