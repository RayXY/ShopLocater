package com.placelocater;

import com.placelocater.model.PlaceGeoCode;
import org.springframework.stereotype.Service;

/**
 * Created by Ray on 25/05/2016.
 */
@Service(value="DirectLine")
public class DirectLineDistanceCalculator implements DistanceCalculator  {

    @Override
    public double calculateDistance(PlaceGeoCode geoCode1, PlaceGeoCode geoCode2) {
        double longtitude1 = geoCode1.getLongitude();
        double latitude1 = geoCode1.getLatitude();
        double longtitude2 = geoCode2.getLongitude();
        double latitude2 = geoCode2.getLatitude();

        return Math.sqrt(
                Math.pow(latitude1 - latitude2, 2) +
                Math.pow(longtitude1 - longtitude2, 2)
        );
    }
}
