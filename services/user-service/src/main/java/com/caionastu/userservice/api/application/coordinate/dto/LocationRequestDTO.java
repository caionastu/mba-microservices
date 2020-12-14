package com.caionastu.userservice.api.application.coordinate.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LocationRequestDTO {

    private double lat;
    private double lng;
    private BigDecimal distance;
}
