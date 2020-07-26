package com.caionastu.userservice.api.application.coordinate.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LocationRequestDTO {

    private double lat;
    private double lng;
    private BigDecimal distance;
}
