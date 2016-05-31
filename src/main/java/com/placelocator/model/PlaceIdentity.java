package com.placelocator.model;

/**
 * Created by Ray on 25/05/2016.
 */
public class PlaceIdentity {
    private String name;
    private String addressNumber;
    private String postCode;
    private String placeType;

    public PlaceIdentity(){} // For JackSON to convert

    public PlaceIdentity(String name, String addressNumber, String postCode) {
        this.name = name;
        this.addressNumber = addressNumber;
        this.postCode = postCode;
    }

    public PlaceIdentity(String name, String addressNumber, String postCode, String placeType) {
        this(name, addressNumber, postCode);
        this.placeType = placeType;
    }

    public String getName() {
        return name;
    }

    public String getAddressNumber() {
        return addressNumber;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getPlaceType() {
        return placeType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlaceIdentity that = (PlaceIdentity) o;

        if (!name.equals(that.name)) return false;
        if (!addressNumber.equals(that.addressNumber)) return false;
        return postCode.equals(that.postCode);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + addressNumber.hashCode();
        result = 31 * result + postCode.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "PlaceIdentity{" +
                "name='" + name + '\'' +
                ", addressNumber='" + addressNumber + '\'' +
                ", postCode='" + postCode + '\'' +
                '}';
    }
}
