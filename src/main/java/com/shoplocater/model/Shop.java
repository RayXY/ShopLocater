package com.shoplocater.model;

/**
 * Created by Ray on 25/05/2016.
 */
public class Shop {

    private final ShopIdentity shopIdentity;
    private final ShopGeoCode shopGeoCode;

    public Shop(ShopIdentity shopIdentity, ShopGeoCode shopGeoCode) {
        this.shopIdentity = shopIdentity;
        this.shopGeoCode = shopGeoCode;
    }

    public ShopIdentity getShopIdentity() {
        return shopIdentity;
    }

    public ShopGeoCode getShopGeoCode() {
        return shopGeoCode;
    }
}
