package com.placelocater;

import com.placelocater.model.MeetingPointRequest;
import com.placelocater.model.PlaceGeoCode;
import com.placelocater.model.Place;
import com.placelocater.model.PlaceIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

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
    private PlaceRecorder placeRecorder;

    @Autowired
    @Qualifier("Google")
    private PlaceExporer placeExporer;

    /*
    Those first a few calls are RESTful API which manages the resources of the places
    */
    @RequestMapping(value = "/rest/userplaces", method = RequestMethod.PUT)
    public ResponseEntity<Void> addPlace(@RequestBody PlaceIdentity placeIdentity) {
        System.out.println("Adding place " + placeIdentity.getName());

        try {
            placeRecorder.addPlace(placeIdentity);
            System.out.println("Place successfully added");
        }
        catch (PlaceGeoCodeNotFoundException e) {
            System.out.println("Place geocode cannot be found");
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/rest/userplaces", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletePlace(@RequestBody PlaceIdentity placeIdentity) {
        System.out.println("Deleting place " + placeIdentity.getName());

        placeRecorder.deletePlace(placeIdentity);
        System.out.println("Place successfully deleted");

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/rest/userplaces", method = RequestMethod.GET)
    public ResponseEntity<Place> getPlace(@RequestParam String name,
                                         @RequestParam String addressNumber,
                                         @RequestParam String postcode) {
        System.out.println("Getting place " + name);
        Place result = placeRecorder.getPlace(new PlaceIdentity(name, addressNumber, postcode));
        if (result == null) {
            System.out.println("Cannot find this place");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        System.out.println("Place successfully Got");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/rest/userplaces", method = RequestMethod.GET)
    public ResponseEntity<Collection<Place>> getPlaces() {
        System.out.println("Getting all user places");
        Collection<Place> result = placeRecorder.getAllPlaces();
        System.out.println("Place successfully Got");

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    /*
    Below are RPC style HTTP calls
     */
    @RequestMapping(value = "/rpc/findnearest", method = RequestMethod.GET)
    public ResponseEntity<Place> findNearestPlace(@RequestParam double longitude,
                                                  @RequestParam double latitude) {
        PlaceGeoCode geoCode = new PlaceGeoCode(longitude, latitude);
        System.out.println("Finding nearest place to " + geoCode.toString());

        Place nearestPlace = placeExporer.findNearestPlace(geoCode);
        if (nearestPlace == null) {
            System.out.println("Cannot find the nearest place");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        System.out.println("Found nearest place " + nearestPlace.getPlaceIdentity().toString());
        return new ResponseEntity<>(nearestPlace, HttpStatus.OK);
    }

    @RequestMapping(value = "rpc/findmeetingpoint", method = RequestMethod.GET)
    public ResponseEntity<Place> findNearestPlace(@RequestBody MeetingPointRequest meetingPointRequest) {
        System.out.println("Finding meeting place of type " + meetingPointRequest.getPlaceType() +
                           " for " + meetingPointRequest.getOrigins());
        Place meetingPoint = null;
        try {
            meetingPoint = placeExporer.findMeetingPoint(meetingPointRequest.getPlaceType(), meetingPointRequest.getOrigins());
        }
        catch (PlaceGeoCodeNotFoundException e) {
            System.out.println("One of the post code does not have matching geocode");
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        System.out.println("Found meeting point" + meetingPoint.getPlaceIdentity().toString());
        return new ResponseEntity<>(meetingPoint, HttpStatus.OK);
    }
}
