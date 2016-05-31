package com.placelocator;

import com.placelocator.model.PlaceGeoCode;
import com.placelocator.search.DirectLineDistanceCalculator;
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
        PlaceGeoCode geoCode1 = new PlaceGeoCode(1.0, 1.0);
        PlaceGeoCode geoCode2 = new PlaceGeoCode(1.0, 1.0);

        assertEquals(0.0, testCalculator.calculateDistance(geoCode1, geoCode2), EPSILON);
    }

    @Test
    public void testDistanceReturnCorrectValue() throws Exception {
        PlaceGeoCode geoCode1 = new PlaceGeoCode(3.0, 4.0);
        PlaceGeoCode geoCode2 = new PlaceGeoCode(0.0, 0.0);

        assertEquals(5.0, testCalculator.calculateDistance(geoCode1, geoCode2), EPSILON);
    }

}