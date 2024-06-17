package com.practice.chalnatest.service;

import com.practice.chalnatest.dto.RequestUserGeoDTO;
import com.practice.chalnatest.dto.ResponseUserLocationDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.GeoOperations;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

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
    public List<ResponseUserLocationDTO> getUserLocationInRadius(String userUUID,Integer distance){
        Distance radius = new Distance(distance, RedisGeoCommands.DistanceUnit.METERS);
        GeoResults<RedisGeoCommands.GeoLocation<String>> results = geoOperations.radius(GEO_KEY, userUUID, radius);

        List<ResponseUserLocationDTO> list = new ArrayList<>();
        results.iterator().forEachRemaining(
                (res)->{
                    log.info(res.toString());
                    String tmp = res.getContent().getName();
                    if(!userUUID.equals(tmp))
                        list.add(ResponseUserLocationDTO.builder()
                                .userUUID(tmp)
                                .distance(geoOperations.distance(GEO_KEY, userUUID, tmp).getValue())
                                .build());
                }
        );
        return list;
    }

//    public Double getUserDistance(RequestUserDistDTO userDistDTO){
//
//        Distance distance =geoOperations
//                .distance(GEO_KEY, userDistDTO.getUser1(), userDistDTO.getUser2());
//        return distance.getValue();
//    }
}
