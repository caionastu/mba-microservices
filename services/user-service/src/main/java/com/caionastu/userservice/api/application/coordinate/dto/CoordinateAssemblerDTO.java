package com.caionastu.userservice.api.application.coordinate.dto;

import com.caionastu.core.interfaces.IAssemblerDTO;
import org.springframework.data.geo.Distance;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Component;

@Component
public class CoordinateAssemblerDTO implements IAssemblerDTO<CoordinateDTO, GeoJsonPoint> {

    @Override
    public CoordinateDTO toDTO(GeoJsonPoint coordinate) {
        return CoordinateDTO.builder()
                .lat(coordinate.getY())
                .lng(coordinate.getX())
                .build();
    }

    @Override
    public GeoJsonPoint toEntity(CoordinateDTO coordinateDTO) {
        return new GeoJsonPoint(coordinateDTO.getLng(), coordinateDTO.getLat());
    }

    public CoordinateDTO toDTO(GeoJsonPoint coordinate, Distance distance) {
        return CoordinateDTO.builder()
                .lat(coordinate.getY())
                .lng(coordinate.getX())
                .distance(distance)
                .build();
    }
}
