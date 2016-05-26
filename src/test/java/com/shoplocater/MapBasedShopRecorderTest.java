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

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by Ray on 25/05/2016.
 */
public class MapBasedShopRecorderTest {

    @InjectMocks
    private MapBasedShopRecorder testShopRecorder;

    @Mock
    private ShopGeoCodeFinder mockShopGeoCodeFinder;
    @Mock
    private NearestShopFinder mockNearestShopFinder;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddShopSuccessfully() throws Exception {
        ShopIdentity stubShopIdentity = new ShopIdentity("test", "1", "test");
        ShopGeoCode stubShopGeoCode = new ShopGeoCode(1, 1);
        when(mockShopGeoCodeFinder.findShopGeoCode(stubShopIdentity)).
                thenReturn(stubShopGeoCode);
        testShopRecorder.addShop(stubShopIdentity);
        assertEquals(stubShopGeoCode, testShopRecorder.getShop(stubShopIdentity).getShopGeoCode());
    }

    @Test
    public void testAddShopFailedWithGeoCodeNotFoundException() throws Exception {
        ShopIdentity stubShopIdentity = new ShopIdentity("test", "1", "test");
        when(mockShopGeoCodeFinder.findShopGeoCode(stubShopIdentity)).
                thenThrow(ShopGeoCodeNotFoundException.class);
        expectedException.expect(ShopGeoCodeNotFoundException.class);
        testShopRecorder.addShop(stubShopIdentity);
    }

    @Test
    public void testCanNotFindNearestShop() throws Exception {
        ShopGeoCode stubShopGeoCode = new ShopGeoCode(1, 1);
        when(mockNearestShopFinder.findNearestShop(testShopRecorder.getAllShops(), stubShopGeoCode)).
                thenReturn(null);
        assertEquals(null, testShopRecorder.findNearestShop(stubShopGeoCode));
    }

    @Test
    public void testCheckShopExistReturnTrue() throws Exception {
        ShopIdentity stubShopIdentity = new ShopIdentity("test", "1", "test");
        ShopGeoCode stubShopGeoCode = new ShopGeoCode(1, 1);
        when(mockShopGeoCodeFinder.findShopGeoCode(stubShopIdentity)).
                thenReturn(stubShopGeoCode);
        testShopRecorder.addShop(stubShopIdentity);
        assertTrue(testShopRecorder.isShopExist(stubShopIdentity));
    }

}