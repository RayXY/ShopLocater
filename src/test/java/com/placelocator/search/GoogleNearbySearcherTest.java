package com.placelocator.search;

import com.placelocator.common.RemoteJsonCaller;
import com.placelocator.model.Place;
import com.placelocator.model.PlaceGeoCode;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Ray on 30/05/2016.
 */
public class GoogleNearbySearcherTest {

    @InjectMocks
    private GoogleNearbySearcher nearbySearcher;

    @Mock
    private RemoteJsonCaller remoteJsonCaller;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSearchNearbyCanReturnInOrder() throws Exception {
        when(remoteJsonCaller.sendGetRequest(anyString())).
                thenReturn(loadStringWithPlaces());
        PlaceGeoCode targetGeoCode = mock(PlaceGeoCode.class);
        List<Place> result = nearbySearcher.searchNearby(targetGeoCode);
        assertEquals("nearer place", result.get(0).getPlaceIdentity().getName());
        assertEquals("further place", result.get(1).getPlaceIdentity().getName());
    }

    private JSONObject loadStringWithPlaces() throws Exception {
        return loadJsonAsObject("GoogleNearbySearcherTestInput");
    }

    private JSONObject loadJsonAsObject(String fileName) throws Exception {
        Path filePath = Paths.get(this.getClass().getResource(fileName).toURI());
        return new JSONObject(new String(Files.readAllBytes(filePath)));
    }

}