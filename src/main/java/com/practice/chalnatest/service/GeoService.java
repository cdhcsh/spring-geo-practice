package com.practice.chalnatest.service;

import com.practice.chalnatest.dto.RequestUserGeoDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.GeoOperations;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class GeoService {

    private final GeoOperations<String,String> geoOperations;
    private final String GEO_KEY = "userLocations";

    public void saveUserLocation(RequestUserGeoDTO userGeoDTO){
        Point point = new Point(userGeoDTO.getLongitude(),userGeoDTO.getLatitude());
        geoOperations.add(GEO_KEY,point,userGeoDTO.getUserUUID());
    }
    public Integer getUserLocationInRadius(String userUUID,Integer distance){
        Distance radius = new Distance(distance, RedisGeoCommands.DistanceUnit.METERS);
        GeoResults<RedisGeoCommands.GeoLocation<String>> results = geoOperations.radius(GEO_KEY, userUUID, radius);
        return results.getContent().size();
    }

//    public Double getUserDistance(RequestUserDistDTO userDistDTO){
//
//        Distance distance =geoOperations
//                .distance(GEO_KEY, userDistDTO.getUser1(), userDistDTO.getUser2());
//        return distance.getValue();
//    }
}
