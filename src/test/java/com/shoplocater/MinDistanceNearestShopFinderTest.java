package com.shoplocater;

import com.shoplocater.model.Shop;
import com.shoplocater.model.ShopGeoCode;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Ray on 25/05/2016.
 */
public class MinDistanceNearestShopFinderTest {

    @InjectMocks
    private MinDistanceNearestShopFinder testNearestShopFinder;

    @Mock
    private DistanceCalculator mockDistanceCalculator;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindNearestShopReturnShopWithShortestDistance() throws Exception {
        Shop nearerShop = mock(Shop.class);
        ShopGeoCode nearerGeoCode = mock(ShopGeoCode.class);
        when(nearerShop.getShopGeoCode()).thenReturn(nearerGeoCode);

        Shop furtherShop = mock(Shop.class);
        ShopGeoCode futherGeoCode = mock(ShopGeoCode.class);
        when(furtherShop.getShopGeoCode()).thenReturn(futherGeoCode);

        List<Shop> stubShops = new ArrayList<>();
        stubShops.add(nearerShop);
        stubShops.add(furtherShop);

        ShopGeoCode targetGeoCode = mock(ShopGeoCode.class);
        when(mockDistanceCalculator.calculateDistance(nearerGeoCode, targetGeoCode)).
                thenReturn(1.0);
        when(mockDistanceCalculator.calculateDistance(futherGeoCode, targetGeoCode)).
                thenReturn(2.0);

        assertEquals(nearerShop, testNearestShopFinder.findNearestShop(stubShops, targetGeoCode));
    }

}