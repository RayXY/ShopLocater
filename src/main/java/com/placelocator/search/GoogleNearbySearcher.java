package com.placelocator.search;

import com.placelocator.model.Place;
import com.placelocator.model.PlaceGeoCode;
import com.placelocator.model.PlaceIdentity;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ray on 27/05/2016.
 */
@Service(value="Google")
public class GoogleNearbySearcher implements NearbySearcher {

    private static final int DEFAULT_RADIUS = 500;
    private static final int MAX_RADIUS = 2000;
    private static final String GOOGLE_NEARBY_API = "https://maps.googleapis.com/maps/api/nearbysearch/json?";
    private static final String PARAMETERS = "location=%s,%s&radius=%s&rankby=distance&key=%s";
    private static final String PARAMETERS_WITH_TYPE = "location=%s,%s&radius=%s&type=%s&rankby=distance&key=%s";
    private static final String GOOGLE_API_KEY = "AIzaSyBQjCQVlN_fgTyIPDG65tTdNuuC7k9qs0Y";

    private RestTemplate restTemplate;

    @Override
    public List<Place> searchNearby(PlaceGeoCode centrePoint, String placeType) {
        if (restTemplate == null)
            restTemplate = new RestTemplate();
        List<Place> nearbyPlaces = null;
        int radius = DEFAULT_RADIUS;
        while (nearbyPlaces == null && radius <= MAX_RADIUS) {
            nearbyPlaces = searchNearby(centrePoint, placeType, radius);
        }

        return nearbyPlaces;
    }

    @Override
    public List<Place> searchNearby(PlaceGeoCode centrePoint) {
        return searchNearby(centrePoint, null);
    }

    private List<Place> searchNearby(PlaceGeoCode centrePoint, String placeType, int radius) {
        String url = placeType == null ?
                constructUrl(centrePoint, radius) :
                constructUrlWithType(centrePoint, radius, placeType);
        String result = restTemplate.getForObject(url, String.class);
        JSONObject resultJson = new JSONObject(result);
        List<Place> nearbyPlaces = new ArrayList<>();;
        JSONArray resultArray = resultJson.getJSONArray("results");
        if (resultArray.length() > 0) {
            for (int i = 0; i < resultArray.length(); i++) {
                nearbyPlaces.add(createPlace(resultArray.getJSONObject(i), placeType));
            }
        }

        return nearbyPlaces;
    }

    private String constructUrl(PlaceGeoCode centrePoint, int radius) {
        return GOOGLE_NEARBY_API +
                String.format(PARAMETERS,
                        centrePoint.getLatitude(),
                        centrePoint.getLongitude(),
                        radius,
                        GOOGLE_API_KEY);
    }

    private String constructUrlWithType(PlaceGeoCode centrePoint, int radius, String placeType) {
        return GOOGLE_NEARBY_API +
                String.format(PARAMETERS_WITH_TYPE,
                        centrePoint.getLatitude(),
                        centrePoint.getLongitude(),
                        radius,
                        placeType, GOOGLE_API_KEY);
    }

    private Place createPlace(JSONObject placeAsJson, String type) {
        String name = placeAsJson.getString("name");
        String postCode = placeAsJson.has("postal_code") ?
                placeAsJson.getString("postal_code") : null;
        PlaceIdentity placeIdentity = new PlaceIdentity(name, null, postCode, type);
        double longitude = placeAsJson.getJSONObject("geometry").getJSONObject("location").getDouble("lng");
        double latitude = placeAsJson.getJSONObject("geometry").getJSONObject("location").getDouble("lat");
        PlaceGeoCode placeGeoCode = new PlaceGeoCode(longitude, latitude);

        return new Place(placeIdentity, placeGeoCode);
    }
}
