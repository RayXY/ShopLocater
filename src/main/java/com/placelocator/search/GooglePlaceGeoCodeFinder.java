package com.placelocator.search;

import com.placelocator.PlaceGeoCodeNotFoundException;
import com.placelocator.common.RemoteJsonCaller;
import com.placelocator.model.PlaceGeoCode;
import com.placelocator.model.PlaceIdentity;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Ray on 25/05/2016.
 */
@Service
public class GooglePlaceGeoCodeFinder implements PlaceGeoCodeFinder {

    private static final String GOOGLE_GEO_API = "https://maps.googleapis.com/maps/api/geocode/json?";
    private static final String PARAMETERS = "components=street_number:%s|postal_code:%s&Key=%s";
    private static final String GOOGLE_API_KEY = "AIzaSyBQjCQVlN_fgTyIPDG65tTdNuuC7k9qs0Y";
    private static final String POSTCODE_PARAMETERS = "components=postal_code:%s&Key=%s";

    @Autowired
    private RemoteJsonCaller remoteJsonCaller;

    @Override
    public PlaceGeoCode findPlaceGeoCode(PlaceIdentity placeIdentity) throws PlaceGeoCodeNotFoundException {
        String url = GOOGLE_GEO_API +
                String.format(PARAMETERS, placeIdentity.getAddressNumber(),
                        placeIdentity.getPostCode(), GOOGLE_API_KEY);

        return createPlaceGeoCode(url);
    }

    @Override
    public PlaceGeoCode findPlaceGeoCode(String postCode) throws PlaceGeoCodeNotFoundException {
        String url = GOOGLE_GEO_API +
                String.format(POSTCODE_PARAMETERS, postCode, GOOGLE_API_KEY);

        return createPlaceGeoCode(url);
    }

    private PlaceGeoCode createPlaceGeoCode(String url) throws PlaceGeoCodeNotFoundException {
        JSONObject resultJson = remoteJsonCaller.sendGetRequest(url);
        String status = resultJson.getString("status");
        if ("ZERO_RESULTS".equals(status)) throw new PlaceGeoCodeNotFoundException();

        JSONObject location = resultJson.
                getJSONArray("results").
                getJSONObject(0).
                getJSONObject("geometry").
                getJSONObject("location");

        return new PlaceGeoCode(location.getDouble("lng"), location.getDouble("lat"));
    }
}
