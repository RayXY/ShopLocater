package com.shoplocater;

import com.shoplocater.model.ShopGeoCode;
import org.springframework.stereotype.Service;

/**
 * Created by Ray on 25/05/2016.
 */
@Service(value="DirectLine")
public class DirectLineDistanceCalculator implements DistanceCalculator  {

    @Override
    public double calculateDistance(ShopGeoCode geoCode1, ShopGeoCode geoCode2) {
        double longtitude1 = geoCode1.getShopLongitude();
        double latitude1 = geoCode1.getShopLatitude();
        double longtitude2 = geoCode2.getShopLongitude();
        double latitude2 = geoCode2.getShopLatitude();

        return Math.sqrt(
                Math.pow(latitude1 - latitude2, 2) +
                Math.pow(longtitude1 - longtitude2, 2)
        );
    }
}
