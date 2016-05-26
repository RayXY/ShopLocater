package com.shoplocater;

import com.shoplocater.model.Shop;
import com.shoplocater.model.ShopGeoCode;

import java.util.Collection;

/**
 * Created by Ray on 25/05/2016.
 */
public interface NearestShopFinder {

    Shop findNearestShop(Collection<Shop> shops, ShopGeoCode shopGeoCode);
}
