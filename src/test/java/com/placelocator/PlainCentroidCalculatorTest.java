package com.placelocator;

import com.placelocator.model.PlaceGeoCode;
import com.placelocator.search.PlainCentroidCalculator;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created by Ray on 27/05/2016.
 */
public class PlainCentroidCalculatorTest {
    private static final double EPSILON = 1e-10;

    private PlainCentroidCalculator testCalculator;
    private Collection<PlaceGeoCode> testInput;

    @Before
    public void setup() {
        testCalculator = new PlainCentroidCalculator();
        testInput = new ArrayList<>();
    }

    @Test
    public void testCalculateTwoPoints() throws Exception {
        testInput.add(new PlaceGeoCode(0.0, 0.0));
        testInput.add(new PlaceGeoCode(2.0, 2.0));
        PlaceGeoCode testResult = testCalculator.findCentroid(testInput);
        assertEquals(1.0, testResult.getLatitude(), EPSILON);
        assertEquals(1.0, testResult.getLongitude(), EPSILON);
    }

    @Test
    public void testCalculateThreePoints() throws Exception {
        testInput.add(new PlaceGeoCode(0.0, 0.0));
        testInput.add(new PlaceGeoCode(2.0, 0.0));
        testInput.add(new PlaceGeoCode(1.0, 1.0));
        PlaceGeoCode testResult = testCalculator.findCentroid(testInput);
        assertEquals(1.0/3, testResult.getLatitude(), EPSILON);
        assertEquals(1.0, testResult.getLongitude(), EPSILON);
    }

    @Test
    public void testCalculateFourPoints() throws Exception {
        testInput.add(new PlaceGeoCode(0.0, 0.0));
        testInput.add(new PlaceGeoCode(2.0, 2.0));
        testInput.add(new PlaceGeoCode(0.0, 2.0));
        testInput.add(new PlaceGeoCode(2.0, 0.0));
        PlaceGeoCode testResult = testCalculator.findCentroid(testInput);
        assertEquals(1.0, testResult.getLatitude(), EPSILON);
        assertEquals(1.0, testResult.getLongitude(), EPSILON);
    }

}