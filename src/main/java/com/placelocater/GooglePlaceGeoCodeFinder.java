package com.placelocater;

import com.placelocater.model.PlaceGeoCode;
import com.placelocater.model.PlaceIdentity;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Ray on 25/05/2016.
 */
@Service(value="Google")
public class GooglePlaceGeoCodeFinder implements PlaceGeoCodeFinder {

    private static final String GOOGLE_GEO_API = "https://maps.googleapis.com/maps/api/geocode/json?";
    private static final String PARAMETERS = "components=street_number:%s|postal_code:%s&Key=%s";
    private static final String GOOGLE_API_KEY = "AIzaSyBQjCQVlN_fgTyIPDG65tTdNuuC7k9qs0Y";

    private RestTemplate restTemplate;

    @Override
    public PlaceGeoCode findPlaceGeoCode(PlaceIdentity placeIdentity) throws PlaceGeoCodeNotFoundException {
        String url = GOOGLE_GEO_API +
                String.format(PARAMETERS, placeIdentity.getAddressNumber(),
                        placeIdentity.getPostCode(), GOOGLE_API_KEY);
        if (restTemplate == null)
            restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        JSONObject resultJson = new JSONObject(result);
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
