package com.placelocater;

import com.placelocater.model.PlaceGeoCode;
import com.placelocater.model.PlaceIdentity;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Ray on 25/05/2016.
 */
public class GooglePlaceGeoCodeFinderTest {

    private static final double EXPECTED_LATITUDE = 37.4224764;
    private static final double EXPECTED_LONGITUDE = -122.0842499;
    private static final double EPSILON = 1e-10;

    @InjectMocks
    private GooglePlaceGeoCodeFinder testPlaceGeoCodeFinder;

    @Mock
    private RestTemplate mockRestTemplate;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindPlaceGeoCodeByPlaceIdentity() throws Exception {
        PlaceIdentity stubPlace = mock(PlaceIdentity.class);
        when(stubPlace.getAddressNumber()).thenReturn("");
        when(stubPlace.getPostCode()).thenReturn("");

        when(mockRestTemplate.getForObject(anyString(), anyObject())).
                thenReturn(loadStringWithFullContent());

        PlaceGeoCode result = testPlaceGeoCodeFinder.findPlaceGeoCode(stubPlace);
        assertEquals(EXPECTED_LATITUDE, result.getLatitude(), EPSILON);
        assertEquals(EXPECTED_LONGITUDE, result.getLongitude(), EPSILON);
    }

    @Test
    public void testFindPlaceGeoCodeByPostcode() throws Exception {
        String postcode = "testPostcode";
        when(mockRestTemplate.getForObject(anyString(), anyObject())).
                thenReturn(loadStringWithFullContent());

        PlaceGeoCode result = testPlaceGeoCodeFinder.findPlaceGeoCode(postcode);
        assertEquals(EXPECTED_LATITUDE, result.getLatitude(), EPSILON);
        assertEquals(EXPECTED_LONGITUDE, result.getLongitude(), EPSILON);
    }

    @Test
    public void testFindPlaceGeoCodeWithEmptyResult() throws Exception {
        PlaceIdentity stubPlace = mock(PlaceIdentity.class);
        when(stubPlace.getAddressNumber()).thenReturn("");
        when(stubPlace.getPostCode()).thenReturn("");

        when(mockRestTemplate.getForObject(anyString(), anyObject())).
                thenReturn(loadStringWithNoResult());
        expectedException.expect(PlaceGeoCodeNotFoundException.class);
        testPlaceGeoCodeFinder.findPlaceGeoCode(stubPlace);
    }

    private String loadStringWithFullContent() throws Exception {
        return loadJsonAsString("GooglePlaceGeoCodeFinderTestInput");
    }

    private String loadStringWithNoResult() throws Exception {
        return loadJsonAsString("GooglePlaceGeoCodeFinderTestInput2");
    }

    private String loadJsonAsString(String fileName) throws Exception {
        Path filePath = Paths.get(this.getClass().getResource(fileName).toURI());
        return new String(Files.readAllBytes(filePath));
    }

}