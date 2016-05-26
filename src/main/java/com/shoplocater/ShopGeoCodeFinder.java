package com.shoplocater;

import com.shoplocater.model.ShopGeoCode;
import com.shoplocater.model.ShopIdentity;

/**
 * Created by Ray on 25/05/2016.
 */
public interface ShopGeoCodeFinder {

    ShopGeoCode findShopGeoCode(ShopIdentity shopIdentity) throws ShopGeoCodeNotFoundException;
}
