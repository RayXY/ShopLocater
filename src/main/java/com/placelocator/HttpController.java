package com.placelocator;

import com.placelocator.control.UserPlacesManager;
import com.placelocator.model.*;
import com.placelocator.search.PlaceExporer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Spring HTTP API service control where resource accessors are defined
 *
 * Note: while the first a few methods follows RESTful principle, the rest are RPC style
 * Created by Ray on 25/05/2016.
 */
@Controller
public class HttpController {

    @Autowired
    private UserPlacesManager placeManager;

    @Autowired
    private PlaceExporer placeExporer;

    /*
    Those first a few calls are RESTful API which manages the resources of the places
    */
    @RequestMapping(value = "/rest/userplace", method = RequestMethod.PUT)
    public ResponseEntity<Void> addPlace(@RequestBody PlaceIdentity placeIdentity) {
        System.out.println("Adding place " + placeIdentity.getName());

        try {
            placeManager.addPlace(placeIdentity);
            System.out.println("Place successfully added");
        }
        catch (PlaceGeoCodeNotFoundException | RemoteCallException e) {
            System.out.println("Place cannot be added: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/rest/userplace", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletePlace(@RequestBody PlaceIdentity placeIdentity) {
        System.out.println("Deleting place " + placeIdentity.getName());

        try {
            placeManager.deletePlace(placeIdentity);
        } catch (RemoteCallException e) {
            System.out.println("Place cannot be deleted: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        System.out.println("Place successfully deleted");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/rest/userplace", method = RequestMethod.GET)
    public ResponseEntity<Place> getPlace(@RequestParam String name,
                                         @RequestParam String addressNumber,
                                         @RequestParam String postcode) {
        System.out.println("Getting place " + name);
        Place result = placeManager.getPlace(new PlaceIdentity(name, addressNumber, postcode));
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
        Collection<Place> result = placeManager.getAllPlaces();
        System.out.println("Place successfully Got");

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /*
    Below are RPC style HTTP calls
     */
    @RequestMapping(value = "/rpc/findnearest", method = RequestMethod.POST)
    public ResponseEntity<Place> findNearestPlace(@RequestBody PlaceGeoCode geoCode) {
        System.out.println("Finding nearest place to " + geoCode.toString());

        Place nearestPlace = placeExporer.findNearestPlace(geoCode);
        if (nearestPlace == null) {
            System.out.println("Cannot find the nearest place");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        System.out.println("Found nearest place " + nearestPlace.getPlaceIdentity().toString());
        return new ResponseEntity<>(nearestPlace, HttpStatus.OK);
    }

    @RequestMapping(value = "rpc/findmeetingpoint", method = RequestMethod.POST)
    public ResponseEntity<Place> findNearestPlace(@RequestBody MeetingPointRequest meetingPointRequest) {
        System.out.println("Finding meeting place of type " + meetingPointRequest.getPlaceType() +
                           " for " + meetingPointRequest.getOrigins());
        String placeType = null;
        try {
            placeType = PlaceType.valueOf(meetingPointRequest.getPlaceType()).name();
        } catch (IllegalArgumentException e) {
            System.out.println("Place type not supported");
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        Place meetingPoint = null;
        try {
            meetingPoint = placeExporer.findMeetingPoint(placeType, meetingPointRequest.getOrigins());
        }
        catch (PlaceGeoCodeNotFoundException e) {
            System.out.println("One of the post code does not have matching geocode");
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        System.out.println("Found meeting point" + meetingPoint.getPlaceIdentity().toString());
        return new ResponseEntity<>(meetingPoint, HttpStatus.OK);
    }
}
