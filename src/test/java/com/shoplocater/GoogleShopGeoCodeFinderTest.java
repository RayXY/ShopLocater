package com.shoplocater;

import com.shoplocater.model.ShopGeoCode;
import com.shoplocater.model.ShopIdentity;
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
public class GoogleShopGeoCodeFinderTest {

    private static final double EXPECTED_LATITUDE = 37.4224764;
    private static final double EXPECTED_LONGITUDE = -122.0842499;
    private static final double EPSILON = 1e-10;

    @InjectMocks
    private GoogleShopGeoCodeFinder testShopGeoCodeFinder;

    @Mock
    private RestTemplate mockRestTemplate;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindShopGeoCodeWithNormalResult() throws Exception {
        ShopIdentity stubShop = mock(ShopIdentity.class);
        when(stubShop.getShopAddressNumber()).thenReturn("");
        when(stubShop.getShopAddressPostCode()).thenReturn("");

        when(mockRestTemplate.getForObject(anyString(), anyObject())).
                thenReturn(loadStringWithFullContent());
        ShopGeoCode result = testShopGeoCodeFinder.findShopGeoCode(stubShop);
        assertEquals(EXPECTED_LATITUDE, result.getShopLatitude(), EPSILON);
        assertEquals(EXPECTED_LONGITUDE, result.getShopLongitude(), EPSILON);
    }

    @Test
    public void testFindShopGeoCodeWithEmptyResult() throws Exception {
        ShopIdentity stubShop = mock(ShopIdentity.class);
        when(stubShop.getShopAddressNumber()).thenReturn("");
        when(stubShop.getShopAddressPostCode()).thenReturn("");

        when(mockRestTemplate.getForObject(anyString(), anyObject())).
                thenReturn(loadStringWithNoResult());
        expectedException.expect(ShopGeoCodeNotFoundException.class);
        testShopGeoCodeFinder.findShopGeoCode(stubShop);
    }

    private String loadStringWithFullContent() throws Exception {
        return loadJsonAsString("GoogleShopGeoCodeFinderTestInput");
    }

    private String loadStringWithNoResult() throws Exception {
        return loadJsonAsString("GoogleShopGeoCodeFinderTestInput2");
    }

    private String loadJsonAsString(String fileName) throws Exception {
        Path filePath = Paths.get(this.getClass().getResource(fileName).toURI());
        return new String(Files.readAllBytes(filePath));
    }

}