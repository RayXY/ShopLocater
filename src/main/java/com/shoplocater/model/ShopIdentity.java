package com.shoplocater.model;

/**
 * Created by Ray on 25/05/2016.
 */
public class ShopIdentity {
    private String shopName;
    private String shopAddressNumber;
    private String shopAddressPostCode;

    public ShopIdentity(){} // For JackSON to convert

    public ShopIdentity(String shopName, String shopAddressNumber, String shopAddressPostCode) {
        this.shopName = shopName;
        this.shopAddressNumber = shopAddressNumber;
        this.shopAddressPostCode = shopAddressPostCode;
    }

    public String getShopName() {
        return shopName;
    }

    public String getShopAddressNumber() {
        return shopAddressNumber;
    }

    public String getShopAddressPostCode() {
        return shopAddressPostCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShopIdentity that = (ShopIdentity) o;

        if (!shopName.equals(that.shopName)) return false;
        if (!shopAddressNumber.equals(that.shopAddressNumber)) return false;
        return shopAddressPostCode.equals(that.shopAddressPostCode);

    }

    @Override
    public int hashCode() {
        int result = shopName.hashCode();
        result = 31 * result + shopAddressNumber.hashCode();
        result = 31 * result + shopAddressPostCode.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ShopIdentity{" +
                "shopName='" + shopName + '\'' +
                ", shopAddressNumber='" + shopAddressNumber + '\'' +
                ", shopAddressPostCode='" + shopAddressPostCode + '\'' +
                '}';
    }
}
