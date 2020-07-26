package com.caionastu.userservice.api.domain.address;


import lombok.*;
import org.springframework.data.annotation.Transient;
import org.springframework.data.geo.Distance;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Address {

    private String street;
    private String district;
    private BigDecimal number;
    private String city;
    private String country;
    private String cep;

    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private GeoJsonPoint location;

    @Transient
    private Distance distance;

}
