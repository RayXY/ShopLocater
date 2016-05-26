package com.shoplocater;

import com.shoplocater.model.Shop;
import com.shoplocater.model.ShopGeoCode;
import com.shoplocater.model.ShopIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Spring REST service controller where resource accessors are defined
 * Created by Ray on 25/05/2016.
 */
@RestController
public class ShopController {

    @Autowired
    @Qualifier("MapBased")
    ShopRecorder shopRecorder;

    @RequestMapping(value = "/addshop", method = RequestMethod.POST)
    public ResponseEntity<Void> addShop(@RequestBody ShopIdentity shopIdentity) {
        System.out.println("Adding shop " + shopIdentity.getShopName());

        if (shopRecorder.isShopExist(shopIdentity)) {
            System.out.println("Shop already exists");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        try {
            shopRecorder.addShop(shopIdentity);
            System.out.println("Shop successfully added");
        }
        catch (ShopGeoCodeNotFoundException e) {
            System.out.println("Shop geocode cannot be found");
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/findnearest", method = RequestMethod.GET)
    public ResponseEntity<Shop> findNearestShop(@RequestParam double longitude,
                                                @RequestParam double latitude) {
        ShopGeoCode geoCode = new ShopGeoCode(longitude, latitude);
        System.out.println("Finding nearest shop to " + geoCode.toString());

        Shop nearestShop = shopRecorder.findNearestShop(geoCode);
        if (nearestShop == null) {
            System.out.println("Cannot find the nearest shop");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        System.out.println("Found nearest shop " + nearestShop.getShopIdentity().toString());
        return new ResponseEntity<>(nearestShop, HttpStatus.OK);
    }
}
