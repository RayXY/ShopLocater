package com.placelocator.search;

import com.placelocator.common.CentroidCalculator;
import com.placelocator.search.GooglePlaceExplorer;
import com.placelocator.search.NearbySearcher;
import com.placelocator.search.PlaceGeoCodeFinder;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

/**
 * Created by Ray on 30/05/2016.
 */
public class GooglePlaceExplorerTest {
    @InjectMocks
    private GooglePlaceExplorer testExplorer;

    @Mock
    private CentroidCalculator centroidCalculator;
    @Mock
    private PlaceGeoCodeFinder placeGeoCodeFinder;
    @Mock
    private NearbySearcher nearbySearcher;

    @Test
    public void testCanFindTheMeetingPoint() {

    }


}