package com.placelocator.control;

import com.placelocator.RemoteCallException;
import com.placelocator.common.RemoteJsonCaller;
import com.placelocator.model.Place;
import com.placelocator.model.PlaceIdentity;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ray on 31/05/2016.
 */
@Service
public class GoogleRemotePlaceController implements RemotePlaceController {

    private static final String GOOGLE_ADDPLACE_API = "https://maps.googleapis.com/maps/api/place/add/json?key=";
    private static final String GOOGLE_DELETEPLACE_API = "https://maps.googleapis.com/maps/api/place/delete/json?key=";
    private static final String GOOGLE_API_KEY = "AIzaSyBQjCQVlN_fgTyIPDG65tTdNuuC7k9qs0Y";
    private Map<PlaceIdentity, String> placeIds = new HashMap<>();

    @Autowired
    private RemoteJsonCaller remoteJsonCaller;

    @Override
    public void addPlaceRemote(Place place) throws RemoteCallException {
        String url = GOOGLE_ADDPLACE_API + GOOGLE_API_KEY;
        JSONObject response = remoteJsonCaller.sendPostRequest(url, constructAddJson(place));
        if ("OK".equals(response.get("status"))) {
            placeIds.put(place.getPlaceIdentity(), response.getString("place_id"));
        }
        else
            throw new RemoteCallException("Failed to add place to search storage");
    }

    @Override
    public void removePlaceRemote(PlaceIdentity placeIdentity) throws RemoteCallException {
        if (!placeIds.containsKey(placeIdentity))
            return;
        String url = GOOGLE_DELETEPLACE_API + GOOGLE_API_KEY;
        JSONObject response = remoteJsonCaller.sendPostRequest(url, constructDeleteJson(placeIdentity));
        if ("OK".equals(response.get("status"))) {
            placeIds.remove(placeIdentity);
        }
        else
            throw new RemoteCallException("Failed to add place to search storage");
    }

    private JSONObject constructAddJson(Place place) {
        JSONObject json = new JSONObject();
        json.put("name", place.getPlaceIdentity().getName());
        JSONArray types = new JSONArray();
        types.put(place.getPlaceIdentity().getPlaceType());
        json.put("types", types);
        JSONObject location = new JSONObject();
        location.put("lat", place.getPlaceGeoCode().getLatitude());
        location.put("lng", place.getPlaceGeoCode().getLongitude());
        json.put("location", location);

        return json;
    }

    private JSONObject constructDeleteJson(PlaceIdentity placeIdentity) {
        JSONObject json = new JSONObject();
        json.put("place_id", placeIds.get(placeIdentity));

        return json;
    }
}
