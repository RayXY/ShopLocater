package com.shoplocater;

import com.shoplocater.model.ShopGeoCode;
import com.shoplocater.model.ShopIdentity;

/**
 * Created by Ray on 25/05/2016.
 */
public interface ShopGeoCodeFinder {
    /**
     * Search and find the geo code assigned to a shop based on its identity
     * @param shopIdentity
     * @return ShopGeoCode if found
     * @throws ShopGeoCodeNotFoundException
     */
    ShopGeoCode findShopGeoCode(ShopIdentity shopIdentity) throws ShopGeoCodeNotFoundException;
}
