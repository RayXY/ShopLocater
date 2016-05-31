package com.placelocator.model;

import java.util.List;

/**
 * Created by Ray on 27/05/2016.
 */
public class MeetingPointRequest {

    private final String placeType;
    private final List<String> origins;

    public MeetingPointRequest(String placeType, List<String> origins) {
        this.placeType = placeType;
        this.origins = origins;
    }

    public String getPlaceType() {
        return placeType;
    }

    public List<String> getOrigins() {
        return origins;
    }
}
