package com.shoplocater.model;

/**
 * Created by Ray on 25/05/2016.
 */
public class ShopGeoCode {

    private final double shopLongitude;
    private final double shopLatitude;

    public ShopGeoCode(double shopLongitude, double shopLatitude) {
        this.shopLongitude = shopLongitude;
        this.shopLatitude = shopLatitude;
    }

    public double getShopLongitude() {
        return shopLongitude;
    }

    public double getShopLatitude() {
        return shopLatitude;
    }

    @Override
    public String toString() {
        return "ShopGeoCode{" +
                "shopLongitude='" + shopLongitude + '\'' +
                ", shopLatitude='" + shopLatitude + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShopGeoCode that = (ShopGeoCode) o;

        if (Double.compare(that.shopLongitude, shopLongitude) != 0) return false;
        return Double.compare(that.shopLatitude, shopLatitude) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(shopLongitude);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(shopLatitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
