package com.practice.chalnatest.controller;

import com.practice.chalnatest.dto.RequestUserGeoDTO;
import com.practice.chalnatest.service.GeoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class GeoController {
    private final GeoService redisUtils;

    @PostMapping
    public String saveLocation(@RequestBody RequestUserGeoDTO userGeoDTO){
        redisUtils.saveUserLocation(userGeoDTO);
        return redisUtils.getUserLocationInRadius(userGeoDTO.getUserUUID(), userGeoDTO.getDistance()).toString();
    }
}
