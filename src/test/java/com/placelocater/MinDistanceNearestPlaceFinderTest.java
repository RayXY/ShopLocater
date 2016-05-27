package com.placelocater;

import com.placelocater.model.Place;
import com.placelocater.model.PlaceGeoCode;
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
public class MinDistanceNearestPlaceFinderTest {

    @InjectMocks
    private MinDistanceNearestPlaceFinder testNearestPlaceFinder;

    @Mock
    private DistanceCalculator mockDistanceCalculator;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindNearestPlaceReturnPlaceWithShortestDistance() throws Exception {
        Place nearerPlace = mock(Place.class);
        PlaceGeoCode nearerGeoCode = mock(PlaceGeoCode.class);
        when(nearerPlace.getPlaceGeoCode()).thenReturn(nearerGeoCode);

        Place furtherPlace = mock(Place.class);
        PlaceGeoCode futherGeoCode = mock(PlaceGeoCode.class);
        when(furtherPlace.getPlaceGeoCode()).thenReturn(futherGeoCode);

        List<Place> stubPlaces = new ArrayList<>();
        stubPlaces.add(nearerPlace);
        stubPlaces.add(furtherPlace);

        PlaceGeoCode targetGeoCode = mock(PlaceGeoCode.class);
        when(mockDistanceCalculator.calculateDistance(nearerGeoCode, targetGeoCode)).
                thenReturn(1.0);
        when(mockDistanceCalculator.calculateDistance(futherGeoCode, targetGeoCode)).
                thenReturn(2.0);

        assertEquals(nearerPlace, testNearestPlaceFinder.findNearestPlace(stubPlaces, targetGeoCode));
    }

}