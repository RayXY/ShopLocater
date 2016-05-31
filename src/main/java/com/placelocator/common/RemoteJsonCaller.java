package com.placelocator.common;

import org.json.JSONObject;

/**
 * Created by Ray on 31/05/2016.
 */
public interface RemoteJsonCaller {

    JSONObject sendGetRequest(String url);

    JSONObject sendPostRequest(String url, JSONObject body);
}
