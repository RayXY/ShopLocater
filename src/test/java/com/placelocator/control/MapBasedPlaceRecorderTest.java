package com.placelocator.control;

import com.placelocator.PlaceGeoCodeNotFoundException;
import com.placelocator.model.Place;
import com.placelocator.model.PlaceGeoCode;
import com.placelocator.model.PlaceIdentity;
import com.placelocator.model.PlaceType;
import com.placelocator.search.NearbySearcher;
import com.placelocator.search.PlaceGeoCodeFinder;
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
public class MapBasedPlaceRecorderTest {

    @InjectMocks
    private MapBasedPlaceRecorder testPlaceRecorder;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddPlaceSuccessfully() throws Exception {
        PlaceIdentity stubPlaceIdentity = new PlaceIdentity("test", "1", "test", PlaceType.Undefined.name());
        PlaceGeoCode stubPlaceGeoCode = new PlaceGeoCode(1, 1);
        testPlaceRecorder.addPlace(new Place(stubPlaceIdentity, stubPlaceGeoCode));
        assertEquals(stubPlaceGeoCode, testPlaceRecorder.getPlace(stubPlaceIdentity).getPlaceGeoCode());
    }

    @Test
    public void testCheckPlaceExistReturnTrue() throws Exception {
        PlaceIdentity stubPlaceIdentity = new PlaceIdentity("test", "1", "test", PlaceType.Undefined.name());
        PlaceGeoCode stubPlaceGeoCode = new PlaceGeoCode(1, 1);
        testPlaceRecorder.addPlace(new Place(stubPlaceIdentity, stubPlaceGeoCode));
        assertTrue(testPlaceRecorder.isPlaceExist(stubPlaceIdentity));
    }

}