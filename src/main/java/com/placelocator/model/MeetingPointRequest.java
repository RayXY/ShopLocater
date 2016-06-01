package com.placelocator.model;

import java.util.List;

/**
 * Created by Ray on 27/05/2016.
 */
public class MeetingPointRequest {

    private  String type;
    private  List<String> origins;

    public MeetingPointRequest(){} // For Jackson

    public MeetingPointRequest(String type, List<String> origins) {
        this.type = type;
        this.origins = origins;
    }

    public String getType() {
        return type;
    }

    public List<String> getOrigins() {
        return origins;
    }
}
