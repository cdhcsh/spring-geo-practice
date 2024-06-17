package com.practice.chalnatest.controller;

import com.practice.chalnatest.dto.RequestUserGeoDTO;
import com.practice.chalnatest.dto.ResponseUserLocationDTO;
import com.practice.chalnatest.service.GeoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class GeoController {
    private final GeoService redisUtils;

    @PostMapping
    public List<ResponseUserLocationDTO> saveLocation(@RequestBody RequestUserGeoDTO userGeoDTO){
        redisUtils.saveUserLocation(userGeoDTO);
        return redisUtils.getUserLocationInRadius(userGeoDTO.getUserUUID(), userGeoDTO.getDistance());
    }
}
