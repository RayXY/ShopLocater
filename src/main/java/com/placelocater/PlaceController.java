package com.placelocater;

import com.placelocater.model.PlaceGeoCode;
import com.placelocater.model.Place;
import com.placelocater.model.PlaceIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Spring HTTP API service controller where resource accessors are defined
 *
 * Note: this is not a RESTful API
 * Created by Ray on 25/05/2016.
 */
@Controller
public class PlaceController {

    @Autowired
    @Qualifier("MapBased")
    PlaceRecorder placeRecorder;

    @RequestMapping(value = "/addplace", method = RequestMethod.POST)
    public ResponseEntity<Void> addPlace(@RequestBody PlaceIdentity placeIdentity) {
        System.out.println("Adding place " + placeIdentity.getName());

        if (placeRecorder.isPlaceExist(placeIdentity)) {
            System.out.println("Place already exists");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        try {
            placeRecorder.addPlace(placeIdentity);
            System.out.println("Place successfully added");
        }
        catch (PlaceGeoCodeNotFoundException e) {
            System.out.println("Place geocode cannot be found");
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/findnearest", method = RequestMethod.GET)
    public ResponseEntity<Place> findNearestPlace(@RequestParam double longitude,
                                                  @RequestParam double latitude) {
        PlaceGeoCode geoCode = new PlaceGeoCode(longitude, latitude);
        System.out.println("Finding nearest place to " + geoCode.toString());

        Place nearestPlace = placeRecorder.findNearestPlace(geoCode);
        if (nearestPlace == null) {
            System.out.println("Cannot find the nearest place");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        System.out.println("Found nearest place " + nearestPlace.getPlaceIdentity().toString());
        return new ResponseEntity<>(nearestPlace, HttpStatus.OK);
    }
}
