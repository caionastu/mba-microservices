package com.caionastu.userservice.api.application.coordinate.dto;

import lombok.*;
import org.springframework.data.geo.Distance;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class CoordinateDTO {

    @NotNull(message = "Latitude must not be null")
    private double lat;

    @NotNull(message = "Longitude must not be null")
    private double lng;

    private Distance distance;
}
