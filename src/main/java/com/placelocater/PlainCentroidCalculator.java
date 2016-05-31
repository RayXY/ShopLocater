package com.placelocater;

import com.placelocater.model.PlaceGeoCode;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by Ray on 27/05/2016.
 */
@Service(value="plain")
public class PlainCentroidCalculator implements CentroidCalculator {

    @Override
    public PlaceGeoCode findCentroid(Collection<PlaceGeoCode> places) {
        double longitude = 0;
        double latitude = 0;
        for (PlaceGeoCode placeGeoCode : places) {
            longitude += placeGeoCode.getLongitude();
            latitude += placeGeoCode.getLatitude();
        }

        return new PlaceGeoCode(longitude/places.size(), latitude/places.size());
    }
}
