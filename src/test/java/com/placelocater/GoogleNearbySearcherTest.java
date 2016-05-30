package com.placelocater;

import com.placelocater.model.Place;
import com.placelocater.model.PlaceGeoCode;
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
    private RestTemplate mockRestTemplate;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void searchNearby() throws Exception {
        when(mockRestTemplate.getForObject(anyString(), anyObject())).
                thenReturn(loadStringWithPlaces());
        PlaceGeoCode targetGeoCode = mock(PlaceGeoCode.class);
        List<Place> result = nearbySearcher.searchNearby(targetGeoCode);
        assertEquals("nearer place", result.get(0).getPlaceIdentity().getName());
        assertEquals("further place", result.get(1).getPlaceIdentity().getName());
    }

    private String loadStringWithPlaces() throws Exception {
        return loadJsonAsString("GoogleNearbySearcherTestInput");
    }

    private String loadJsonAsString(String fileName) throws Exception {
        Path filePath = Paths.get(this.getClass().getResource(fileName).toURI());
        return new String(Files.readAllBytes(filePath));
    }

}