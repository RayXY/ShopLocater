package com.shoplocater;

import com.shoplocater.model.Shop;
import com.shoplocater.model.ShopGeoCode;
import com.shoplocater.model.ShopIdentity;

/**
 * Created by Ray on 25/05/2016.
 */
public interface ShopRecorder {

    void addShop(ShopIdentity shopIdentity) throws ShopGeoCodeNotFoundException;

    Shop findNearestShop(ShopGeoCode shopGeoCode);

    boolean isShopExist(ShopIdentity shopIdentity);
}
