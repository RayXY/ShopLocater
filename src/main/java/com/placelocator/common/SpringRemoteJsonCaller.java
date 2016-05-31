package com.placelocator.common;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Ray on 31/05/2016.
 */
@Service
public class SpringRemoteJsonCaller implements RemoteJsonCaller {

    @Override
    public JSONObject sendGetRequest(String url) {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        return new JSONObject(response);
    }

    @Override
    public JSONObject sendPostRequest(String url, JSONObject body) {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(url, body, String.class);
        return new JSONObject(response);
    }
}
