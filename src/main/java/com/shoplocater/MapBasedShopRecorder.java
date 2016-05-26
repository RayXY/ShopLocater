package com.shoplocater;

import com.shoplocater.model.Shop;
import com.shoplocater.model.ShopGeoCode;
import com.shoplocater.model.ShopIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Ray on 25/05/2016.
 */
@Service(value="MapBased")
public class MapBasedShopRecorder implements ShopRecorder {

    @Autowired
    @Qualifier("Google")
    private ShopGeoCodeFinder shopGeoCodeFinder;
    @Autowired
    @Qualifier("MinDistance")
    private NearestShopFinder nearestShopFinder;

    private Map<ShopIdentity, Shop> recordMap;

    public MapBasedShopRecorder() {
        this.recordMap = new ConcurrentHashMap<>();
    }

    @Override
    public void addShop(ShopIdentity shopIdentity) throws ShopGeoCodeNotFoundException {
        ShopGeoCode shopGeoCode = shopGeoCodeFinder.findShopGeoCode(shopIdentity);
        recordMap.put(shopIdentity, new Shop(shopIdentity, shopGeoCode));
    }

    @Override
    public Shop findNearestShop(ShopGeoCode shopGeoCode) {
        return nearestShopFinder.findNearestShop(recordMap.values(), shopGeoCode);
    }

    @Override
    public boolean isShopExist(ShopIdentity shopIdentity) {
        return recordMap.containsKey(shopIdentity);
    }

    protected Shop getShop(ShopIdentity shopIdentity) {
        return recordMap.get(shopIdentity);
    }

    protected Collection<Shop> getAllShops() {
        return recordMap.values();
    }
}
