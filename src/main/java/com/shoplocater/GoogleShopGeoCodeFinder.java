package com.shoplocater;

import com.shoplocater.model.ShopGeoCode;
import com.shoplocater.model.ShopIdentity;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Ray on 25/05/2016.
 */
@Service(value="Google")
public class GoogleShopGeoCodeFinder implements ShopGeoCodeFinder{

    private static final String GOOGLE_GEO_API = "https://maps.googleapis.com/maps/api/geocode/json?";
    private static final String PARAMETERS = "components=street_number:%s|postal_code:%s&Key=";
    private static final String GOOGLE_API_KEY = "AIzaSyBQjCQVlN_fgTyIPDG65tTdNuuC7k9qs0Y";

    private RestTemplate restTemplate;

    @Override
    public ShopGeoCode findShopGeoCode(ShopIdentity shopIdentity) throws ShopGeoCodeNotFoundException {
        String url = GOOGLE_GEO_API +
                String.format(PARAMETERS, shopIdentity.getShopAddressNumber(),
                        shopIdentity.getShopAddressPostCode()) +
                GOOGLE_API_KEY;
        if (restTemplate == null)
            restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        JSONObject resultJson = new JSONObject(result);
        String status = resultJson.getString("status");
        if ("ZERO_RESULTS".equals(status)) throw new ShopGeoCodeNotFoundException();

        JSONObject location = resultJson.
                getJSONArray("results").
                getJSONObject(0).
                getJSONObject("geometry").
                getJSONObject("location");

        return new ShopGeoCode(location.getDouble("lng"), location.getDouble("lat"));
    }
}
