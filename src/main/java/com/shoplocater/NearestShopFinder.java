package com.shoplocater;

import com.shoplocater.model.Shop;
import com.shoplocater.model.ShopGeoCode;

import java.util.Collection;

/**
 * Created by Ray on 25/05/2016.
 */
public interface NearestShopFinder {
    /**
     * Given a collection of shops, calculate which shop is nearest to a given geo code;
     * only return one if multiple results found
     * @param shops
     * @param shopGeoCode
     * @return A shop represent the nearest or one of the nearest shops
     */
    Shop findNearestShop(Collection<Shop> shops, ShopGeoCode shopGeoCode);
}
