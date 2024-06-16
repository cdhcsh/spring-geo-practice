package com.practice.chalnatest.dto;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RequestUserGeoDTO{
    private String userUUID;
    private Float longitude;
    private Float latitude;
    private Integer distance;
}
