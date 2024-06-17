package com.practice.chalnatest.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseUserLocationDTO {
    private String userUUID;
    private Double distance;
}
