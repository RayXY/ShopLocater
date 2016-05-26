package com.shoplocater;

import com.shoplocater.model.Shop;
import com.shoplocater.model.ShopGeoCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by Ray on 25/05/2016.
 */
@Service(value="MinDistance")
public class MinDistanceNearestShopFinder implements NearestShopFinder{

    @Autowired
    @Qualifier("DirectLine")
    private DistanceCalculator distanceCalculator;
    
    @Override
    public Shop findNearestShop(Collection<Shop> shops, ShopGeoCode shopGeoCode) {
        double minDistance = Double.MAX_VALUE;
        double currentDistance;
        Shop nearestShop = null;
        for (Shop shop : shops) {
            currentDistance = distanceCalculator.calculateDistance(shop.getShopGeoCode(), shopGeoCode);
            if (currentDistance < minDistance) {
                nearestShop = shop;
                minDistance = currentDistance;
            }
        }

        return nearestShop;
    }

}
