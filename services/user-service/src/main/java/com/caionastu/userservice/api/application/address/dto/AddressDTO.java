package com.caionastu.userservice.api.application.address.dto;

import com.caionastu.userservice.api.application.coordinate.dto.CoordinateDTO;
import lombok.*;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AddressDTO {

    @NotBlank(message = "Street must not be blank.")
    private String street;

    private String district;

    @NotNull(message = "Number must not be null.")
    private BigDecimal number;

    @NotBlank(message = "City must not be blank.")
    private String city;

    @NotBlank(message = "Country must not be blank.")
    private String country;

    @NotBlank(message = "CEP must not be blank.")
    private String cep;

    @NotNull(message = "Coordinate must not be null.")
    private CoordinateDTO location;

}
