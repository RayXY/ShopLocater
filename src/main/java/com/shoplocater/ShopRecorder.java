package com.shoplocater;

import com.shoplocater.model.Shop;
import com.shoplocater.model.ShopGeoCode;
import com.shoplocater.model.ShopIdentity;

/**
 * Created by Ray on 25/05/2016.
 */
public interface ShopRecorder {

    /**
     * Search shop geo code based on shop identity and then add shop only if geo code can be found
     * @param shopIdentity
     * @throws ShopGeoCodeNotFoundException
     */
    void addShop(ShopIdentity shopIdentity) throws ShopGeoCodeNotFoundException;

    /**
     * Find the nearest shop based on the given geo code
     * @param shopGeoCode
     * @return the nearest shop, null if none found
     */
    Shop findNearestShop(ShopGeoCode shopGeoCode);

    /**
     * Check if shop has already been added
     * @param shopIdentity
     * @return true if shop already exists
     */
    boolean isShopExist(ShopIdentity shopIdentity);
}
