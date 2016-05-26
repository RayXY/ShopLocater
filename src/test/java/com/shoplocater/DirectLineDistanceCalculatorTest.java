package com.shoplocater;

import com.shoplocater.model.ShopGeoCode;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Ray on 25/05/2016.
 */
public class DirectLineDistanceCalculatorTest {
    private static final double EPSILON = 1e-10;
    private DirectLineDistanceCalculator testCalculator;

    @Before
    public void setup() {
        testCalculator = new DirectLineDistanceCalculator();
    }

    @Test
    public void testDistanceZero() throws Exception {
        ShopGeoCode geoCode1 = new ShopGeoCode(1.0, 1.0);
        ShopGeoCode geoCode2 = new ShopGeoCode(1.0, 1.0);

        assertEquals(0.0, testCalculator.calculateDistance(geoCode1, geoCode2), EPSILON);
    }

    @Test
    public void testDistanceReturnCorrectValue() throws Exception {
        ShopGeoCode geoCode1 = new ShopGeoCode(3.0, 4.0);
        ShopGeoCode geoCode2 = new ShopGeoCode(0.0, 0.0);

        assertEquals(5.0, testCalculator.calculateDistance(geoCode1, geoCode2), EPSILON);
    }

}